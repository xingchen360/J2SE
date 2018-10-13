package com.noteshare.mongo.gridfs.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.noteshare.mongo.gridfs.exceptions.MongoDBException;

/**
 * @Title: mongodb基础操作
 * @author itnoteshare
 * @since JDK1.6
 * @history 2017年4月1日 itnoteshare 新建
 */
public interface IGridFSDAO {

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param input 文件流 save内部关闭
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @return
     */
    void save(InputStream input, String id, String fileName) throws MongoDBException;

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param file 文件
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @throws MongoDBException
     */
    void save(File file, String id, String fileName) throws MongoDBException;

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param data 二进制
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @throws IOException
     */
    void save(byte[] data, String id, String fileName) throws MongoDBException;

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param input 文件流 save内部关闭
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @return
     */
    void save(InputStream input, String id, String fileName, Document metadata)
            throws MongoDBException;

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param file 文件
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @throws MongoDBException
     */
    void save(File file, String id, String fileName, Document metadata) throws MongoDBException;

    /**
     * 保存文件,如果存在相同id，则删除后保存
     * 
     * @param data 二进制
     * @param id 文件唯一标识
     * @param fileName 文件名称
     * @param metadata 文件描述信息
     * @throws IOException
     */
    void save(byte[] data, String id, String fileName, Document metadata) throws MongoDBException;

    /**
     * 下载文件
     * 
     * @param id 文件唯一标识
     * @return 文件流 调用者关闭
     */
    GridFSDownloadStream download(String id) throws MongoDBException;
    
    
    /**
     * 根据metadata查询文件信息
     * @param condition
     * @return 文件信息
     */
    GridFSFindIterable queryByMetadataEq(Map<String, String> condition) throws MongoDBException;
    
    
    /**
     * 查询文件信息
     * @param filter 条件
     */
    GridFSFindIterable find(Bson filter) throws MongoDBException;

    /**
     * 下载文件到指定地址,如果有文件则覆盖写,没有则自动创建
     * 
     * @param id 文件唯一标识
     * @param filePath 文件地址
     */
    void downloadToLocalFile(String id, String filePath) throws MongoDBException;

    /**
     * 删除文件
     * 
     * @param id 文件唯一标识
     * @return 删除文件个数
     */
    int delete(String id) throws MongoDBException;

}