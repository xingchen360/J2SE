package com.noteshare.mongo.gridfs.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import com.noteshare.mongo.gridfs.dao.GridFSDAO;
import com.noteshare.mongo.gridfs.dao.IGridFSDAO;

/**
 * IGridFSDAO 使用示例
 * 
 * @author Administrator
 *
 */
public class MongoGridFSDemo {

    /**
     * 测试起始
     * @param args
     * @throws UnknownHostException
     * @throws Exception
     */
    public static void main(String[] args) throws UnknownHostException, Exception {

        IGridFSDAO gridDao = new GridFSDAO();
        save(gridDao);
        
        // 据文件名从gridfs中读取到文件
       // query(gridDao, "6940726816165228544", "1.txt");
        // gridDao.delete("1");
        // query(gridDao, "130","软件质量管理130.doc");
        //save(gridDao);
        //gridDao.delete("10000");
//        find(gridDao);
        queryByMetadataEq(gridDao);

    }

    /**
     * 根据metadata信息查询文件
     * 
     * @param gridDao
     * @throws FileNotFoundException
     */
    public static void queryByMetadataEq(IGridFSDAO gridDao) throws FileNotFoundException {
        System.out.println("=======================queryByMetadataEq=============================");
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("系统类型", "xtpt");
        GridFSFindIterable fileIterable = gridDao.queryByMetadataEq(condition);
        for (GridFSFile gridFSFile : fileIterable) {
            System.out.println(gridFSFile.getMetadata().toJson());
        }
    }
    
    
    /**
     * 
     * @throws FileNotFoundException
     */
    public static void find(IGridFSDAO gridDao) throws FileNotFoundException {
        System.out.println("======================find====================");
        Bson filter = Filters.eq("filename", "请假单.docx");
        GridFSFindIterable fileIterable = gridDao.find(filter );
        for (GridFSFile gridFSFile : fileIterable) {
            System.out.println(gridFSFile.getId());
            
            System.out.println(gridFSFile.getMetadata().toJson());
        }
    }
    
    /**
     * 上传文件
     * 
     * @param gridDao
     * @throws FileNotFoundException
     */
    public static void save(IGridFSDAO gridDao) throws FileNotFoundException {
        String file = "C:/Users/Administrator/Desktop/文档/database/数据库安装规范.rar";
        if (!new File(file).exists()) {
            System.out.println("file is not exists.");
        }
        String fileName = "数据库安装规范.rar";
        Document doc = new Document("模块类型", "mysql");
        doc.append("系统类型", "xtpt");
        doc.append("大类型", "信息");
        // 把文件保存到gridfs中，并以文件的md5值为id
        InputStream in = new FileInputStream(file);
        
        gridDao.save(in, "4", fileName, doc);
    }

    /**
     * 获取
     * 
     * @param gridDao
     * @param fileId
     * @param filepath
     */
    public static void query(IGridFSDAO gridDao, String fileId, String filepath) {

        InputStream inputStream = gridDao.download(fileId);
        Document metadata = ((GridFSDownloadStream) inputStream).getGridFSFile().getMetadata();
        System.out.println("==============metadata==============");
        System.out.println(metadata.get("MKMC"));

        BufferedOutputStream bops = null;
        BufferedInputStream bips = null;
        try {
            bips = new BufferedInputStream(inputStream);
            bops = new BufferedOutputStream(new FileOutputStream(filepath));
            int byts = 0;
            while ((byts = bips.read()) != -1) {
                bops.write(byts);
            }
            bops.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bips.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}