/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.noteshare.mongo.gridfs.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoGridFSException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import com.noteshare.mongo.gridfs.exceptions.MongoDBException;
import com.noteshare.mongo.gridfs.pool.MongoManager;

/**
 * @Title: 文件管理
 * @author TODO
 * @since JDK1.6
 * @history 2017年4月1日 TODO 新建
 */
public class GridFSDAO implements IGridFSDAO {

    /**
     * mongo管理
     */
    private static MongoManager instance = MongoManager.getInstance();

    /**
     * 数据库
     */
    private MongoDatabase database;

    /**
     * gridfs
     */
    private final GridFSBucket fsbluck;

    /**
     * 切片大小
     */
    private static final int CHUNK_SIZE = 358400;

    /**
     * 初始化数据库
     */
    private void init() {
        if (null == this.database) {
            this.database = instance.getMongoDatabase();
        }
    }

    /**
     * 默认bucket=fs的构造
     */
    public GridFSDAO() {
        init();
        fsbluck = GridFSBuckets.create(database);
    }

    /**
     * 指定bucket的构造
     * 
     * @param bucket
     */
    public GridFSDAO(final String bucket) {
        init();
        fsbluck = GridFSBuckets.create(database, bucket);
    }

    /**
     * 获取GridFSBucket
     * 
     * @return
     */
    public GridFSBucket getGridFSBucket() {
        return fsbluck;
    }

    /**
     * 保存文件
     * 
     * @param input 文件流
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @throws BigClassifyException
     */
    @Override
    public void save(InputStream input, String id, String fileName) throws MongoDBException {
        this.save(input, id, fileName, null);
    }

    /**
     * 保存文件
     * 
     * @param file
     * @param id
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public void save(File file, String id, String fileName) throws MongoDBException {
        this.save(file, id, fileName, null);
    }

    /**
     * 保存文件
     * 
     * @param data
     * @param id 文件id
     * @param fileName
     * @return
     */
    @Override
    public void save(final byte[] data, String id, String fileName) throws MongoDBException {
        this.save(data, id, fileName, null);
    }

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param input 文件流 save内部关闭
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @return
     */
    @Override
    public void save(InputStream input, String id, String fileName, Document metadata)
            throws MongoDBException {
        this.delete(id);
        GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(CHUNK_SIZE);
        if (null != metadata) {
            options.metadata(metadata);
        }
        fsbluck.uploadFromStream(this.getBsonValue(id), fileName, input, options);
        try {
            if (null != input) {
                input.close();
            }
        } catch (IOException e) {
            throw new MongoDBException("输入流关闭异常", e);
        }
    }

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param file 文件
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @throws MongoDBException
     */
    @Override
    public void save(File file, String id, String fileName, Document metadata)
            throws MongoDBException {
        try {
            InputStream streamToUploadFrom = new FileInputStream(file);
            this.save(streamToUploadFrom, id, fileName, metadata);
        } catch (FileNotFoundException e) {
            throw new MongoDBException("找不到文件" + file.getAbsolutePath(), e);
        }
    }

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param data 二进制
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @throws IOException
     */
    @Override
    public void save(byte[] data, String id, String fileName, Document metadata)
            throws MongoDBException {
        this.delete(id);
        GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(CHUNK_SIZE);
        if (null != metadata) {
            options.metadata(metadata);
        }
        GridFSUploadStream uploadStream = fsbluck.openUploadStream(this.getBsonValue(id), fileName,
                options);
        uploadStream.write(data);
        uploadStream.close();
    }
    
    /**
    * 下载文件
    * 
    * @param id 文件id
    */
   @Override
   public GridFSDownloadStream download(String id) {
       return fsbluck.openDownloadStream(this.getBsonValue(id));
   }


   /**
    * 根据metadata查询文件信息
    * @param condition
    * @return
    */
   public GridFSFindIterable queryByMetadataEq(Map<String, String> condition) {
       List<Bson> lst = new ArrayList<Bson>(condition.size());
       for (Map.Entry<String, String> entry : condition.entrySet()) {
           lst.add(Filters.eq("metadata." + entry.getKey(), entry.getValue()));
       }
       return fsbluck.find(Filters.and(lst));
   }
   
   /**
    * 查询文件信息
    * @param filter 条件
    */
   public GridFSFindIterable find(Bson filter) {
       return fsbluck.find(filter);
   }

    /**
     * 下载文件到指定地址,如果有文件则覆盖写,没有则自动创建
     * 
     * @param id 文件唯一标识
     * @param filePath 文件地址
     */
    @Override
    public void downloadToLocalFile(String id, String filePath) throws MongoDBException {
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new MongoDBException(filePath + "找不到", e);
            }
        }
        OutputStream destination = null;
        // 覆盖写
        try {
            destination = new FileOutputStream(file, false);
        } catch (FileNotFoundException e) {
            throw new MongoDBException(filePath + "找不到", e);
        }
        fsbluck.downloadToStream(this.getBsonValue(id), destination);
    }

    /**
     * 删除文件
     * 
     * @param id 文件id
     * @return 删除文件个数
     */
    @Override
    public int delete(String id) throws MongoDBException {
        try {
            fsbluck.delete(this.getBsonValue(id));
            return 1;
        } catch (MongoGridFSException e) {
            // 没有
            return 0;
        }

    }

    /**
     * 获取BsonValue
     * 
     * @param id 已有的字符串
     * @return
     */
    private BsonValue getBsonValue(String id) {
        return new BsonString(id);
    }

}
