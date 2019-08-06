/******************************************************************************
* Copyright (C) 2019 NoteShare Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为itnoteshare.com开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/
package com.noteshare.svn.classes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title:GetClassListByJavaPathList.java
 * @Description:根据svn导出来的更新文件夹获取对应的class文件
 * @author:NoteShare
 * @date:2019年8月6日 下午2:12:16
 */
public class GetClassListByJavaFiles {
	public static void main(String[] args) {
		// 导出来后的java文件夹路径
		String javaPath = "C:\\Users\\itnoteshare\\Desktop\\OA\\branches\\FuJian\\OA_XiaMen_JM\\src\\main\\java\\com";
		// 对应的编译文件路径
		String classPath = "D:\\data\\programs\\work01\\jmoa\\src\\main\\webapp\\WEB-INF\\classes\\com";
		// 抽取出来的class存放路径
		String desClassPath = "D:\\updateClassFile\\com";
		List<String> pathList = new ArrayList<String>();
		getAllSunFilePathList(pathList,javaPath);
		for (String path : pathList) {
			path = path.replace(javaPath, classPath).replace(".java", ".class");
			File classFile = new File(path);
			if(classFile.exists()){
				// 将class写入到目的文件夹
				String desClassFilePath = classFile.getAbsolutePath();
				desClassFilePath = desClassFilePath.replace(classPath, desClassPath);
				File desClassFile = new File(desClassFilePath);
				if(!desClassFile.getParentFile().exists()){
					desClassFile.getParentFile().mkdirs();
				}
				copyFile(classFile,desClassFile);
			}
		}
	}
	
	// 复制文件
	public static void copyFile(File sourceFile, File targetFile){
		// 新建文件输入流并对它进行缓冲
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			FileInputStream input = new FileInputStream(sourceFile);
			inBuff = new BufferedInputStream(input);
			// 新建文件输出流并对它进行缓冲
			FileOutputStream output = new FileOutputStream(targetFile);
			outBuff = new BufferedOutputStream(output);
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// 关闭流
			try {
				inBuff.close();
				outBuff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	/**
	 * 获取文件夹下所有文件的路径集合
	 * @param pathList 文件路径集合
	 * @param path 文件绝对路径
	 */
	public static void getAllSunFilePathList(List<String> pathList,String path){
		File file = new File(path);
		if(file.exists()){
			File[] files = file.listFiles();
			for (File fileT : files) {
				String pathT = fileT.getAbsolutePath();
				if(fileT.isDirectory()){
					getAllSunFilePathList(pathList,pathT);
				}else{
					pathList.add(pathT);
				}
			}
		}
	}
}
