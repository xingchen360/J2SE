package com.noteshare.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.junit.Test;

public class Zip4jUtil {
	/**
	  * @Title: compressFile
	  * @Description:  加密压缩文件夹/文件到指定路径
	  * @param desPath 压缩文件保存路径
	  * @param desName 压缩文件名
	  * @param srcPath 需要压缩的文件夹/文件路径
	  * @param isEncrypted 是否需要加密
	  * @param password 密码
	  * @return：void
	 */
	public void compressFile(String desPath,String desName,String srcPath,boolean isEncrypted,String password){
		try {
			//判断保存路径是否存在
			File file_temp = new File(desPath);
			if(!file_temp.exists()){
				file_temp.mkdirs();
			}
			//创建zip对象
			ZipFile zipFile = new ZipFile(new File(desPath + File.separator + desName + ".zip"));
			//设置压缩参数
			ZipParameters parameters = createZipParameter(isEncrypted,password);
			File file = new File(srcPath);
			//如果需要压缩的是文件夹
			if(file.isDirectory()){
				zipFile.addFolder(srcPath, parameters);
			}else{
				ArrayList<File> fileList = new ArrayList<File>();
				fileList.add(file);
				zipFile.addFiles(fileList, parameters);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	  * @Title: compressFile
	  * @Description:  压缩文件夹/文件到指定路径
	  * @param desPath 压缩文件保存路径
	  * @param desName 压缩文件名
	  * @param srcPath 需要压缩的文件夹/文件路径
	  * @return：void
	 */
	public void compressFile(String desPath,String desName,String srcPath){
		//不加密压缩
		compressFile(desPath,desName,srcPath,false,null);
	}
	/**
	  * @Title: createZipParameter 压缩参数设置
	  * @Description: 压缩参数设置
	  * @param isEncrypted 是否加密
	  * @param password 密码，不加密则设置为null
	  * @return：ZipParameters
	 */
	private ZipParameters createZipParameter(boolean isEncrypted,String password){
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if(isEncrypted){
        	parameters.setEncryptFiles(true);
        	parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        	parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        	parameters.setPassword(password);
        }
        return parameters;
	}
	/**
	  * @Title: compressFile 
	  * @Description:  压缩文件到指定路径,压缩文件的名字默认是源文件夹/源文件的名字
	  * @param desPath 压缩文件保存路径
	  * @param srcPath 需要压缩的文件夹/文件路径
	  * @return：void
	 */
	public void compressFile(String desPath,String srcPath){
		//获取压缩文件的名字
		File file = new File(srcPath);
		String desName = file.getName();
		//调用本地压缩方法
		compressFile(desPath,desName,srcPath);
	}
	/**
	  * @Title: compressFile
	  * @Description: 加密压缩文件到指定路径,压缩文件的名字默认是源文件夹/源文件的名字
	  * @param desPath 压缩文件保存路径
	  * @param srcPath 需要压缩的文件夹/文件路径
	  * @param isEncrypted 是否加密
	  * @param password 密码
	  * @return：void
	 */
	public void compressFile(String desPath,String srcPath,boolean isEncrypted,String password){
		//获取压缩文件的名字
		File file = new File(srcPath);
		String desName = file.getName();
		//调用本地压缩方法
		compressFile(desPath,desName,srcPath,isEncrypted,password);
	}
	/**
	  * @Title: addFilesToFolderInZip 
	  * @Description: 压缩文件列表到制定的目录下并保存到指定的路径 
	  * @param desPath 压缩文件保存路径
	  * @param desName 压缩文件名称
	  * @param srcPaths 要压缩的文件路径列表
	  * @param rootPath 压缩文件的目录结构
	  * @param isEncrypted 是否加密
	  * @param password 密码
	  * @return：void
	 */
	public void addFilesToFolderInZip(String desPath,String desName,ArrayList<String> srcPaths,String rootPath,boolean isEncrypted,String password){
		try {
			//创建zip对象
			ZipFile zipFile = new ZipFile(new File(desPath + File.separator + desName + ".zip"));
			ArrayList<File> filesToAdd = new ArrayList<File>();
			for(String path : srcPaths){
				File file = new File(path);
				if(file.exists() && !file.isDirectory()){
					filesToAdd.add(file);
				}
			}
			ZipParameters parameters = createZipParameter(isEncrypted,password);
			if(null != rootPath && !"".equals(rootPath)){
				parameters.setRootFolderInZip(rootPath);
			}
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	  * @Title: compressFileByStream 
	  * @Description: 加密压缩一个文件流到指定的路径
	  * @param desPath 压缩文件要保存的路径
	  * @param desName 压缩文件名称
	  * @param is 要压缩的流
	  * @param zipFileName 文件流在压缩包中的文件名称带后缀
	  * @param isEncrypted 是否加密
	  * @param password 密码
	  * @return：void
	 */
	public void compressFileByStream(String desPath,String desName,InputStream is,String zipFileName,boolean isEncrypted,String password){
		try {
			//判断保存路径是否存在
			File file_temp = new File(desPath);
			if(!file_temp.exists()){
				file_temp.mkdirs();
			}
			//创建zip对象
			File temp_file = new File(desPath + File.separator + desName + ".rar");
			ZipFile zipFile = new ZipFile(temp_file);
			//:存在一个bug，如果压缩文件已经存在会出现文件叠加到压缩包里去。做手动删除。
			if(temp_file.exists()){
				temp_file.delete();
			}
			//设置压缩参数
			ZipParameters parameters = createZipParameter(isEncrypted,password);
			parameters.setFileNameInZip(zipFileName);
			parameters.setSourceExternalStream(true);
            zipFile.addStream(is, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	  * @Title: compressFileByStream 
	  * @Description: 加密压缩一个文件流到指定的路径
	  * @param desPath 压缩文件要保存的路径
	  * @param desName 压缩文件名称
	  * @param is 要压缩的流
	  * @return：void
	 */
	public void compressFileByStream(String desPath,String desName,InputStream is,String zipFileName){
		compressFileByStream(desPath, desName, is,zipFileName,false,null);
	}
	/**
	  * @Title: deCompressFile 
	  * @Description: 解压zip文件
	  * @param desPath 要解压到的路径地址
	  * @param srcPath 要解压的压缩文件路径
	  * @return：void
	 */
	public void deCompressFile(String desPath,String srcPath){
		try {
            ZipFile zipFile = new ZipFile(srcPath);
            zipFile.extractAll(desPath);
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void testCompress(){
		//this.compressFile("d:\\cc\\","test","d:\\bwm.dmp");
		//this.compressFile("d:\\cc\\", "myZip", "d:\\bwm.dmp", true, "123456");
		/*
		ArrayList<String> filePathList = new ArrayList<String>();
		filePathList.add("d:\\bwm.dmp");
		filePathList.add("e:\\gis.txt");
		this.addFilesToFolderInZip("d:\\cc\\", "myZip",filePathList, "\\aa", true, "123456");
		*/
		///*
		FileInputStream is = null;
		try {
			is = new FileInputStream("d:\\bwm.dmp");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		compressFileByStream("d:\\cc\\", "myZip", is,"test.dmp");
		//*/
		//deCompressFile("d:\\cc\\dd","d:\\cc\\myZip.zip");
	}
}
