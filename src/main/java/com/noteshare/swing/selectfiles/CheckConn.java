package com.noteshare.swing.selectfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CheckConn {
	
	/**
	 * @Description: 递归扫描文件夹中的所有java文件和jsp文件。
	 * @param file ： 需要扫描的文件或文件夹
	 * @param List<String> questionFilePath ： 有问题的文件路径集合
	 * @author     : NoteShare
	 * Create Date : 2016年12月13日 下午2:24:31
	 * @throws
	 */
	public void scanFile(File file,List<String> questionFilePath){
		//记录有问题的文件路径
		if(null != file){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for (File file2 : files) {
					scanFile(file2,questionFilePath);
				}
			}else{
				//判断文件是否为java文件或jsp文件
				if(file.getName().endsWith(".java") || file.getName().endsWith(".jsp")){
					//判断文件是否存在未关闭的connection
					checkConn(file,questionFilePath);
				}
			}
		}
	}
	
	public void checkConn(File file,List<String> questionFilePath) {
		BufferedReader br = null;
		try {
			String filePath = file.getAbsolutePath();
			br = new BufferedReader(new FileReader(file));
			String line = null;
			//是否包含.getConnection
			boolean connFlag = false;
			//是否包含close
			boolean closeFlag = false;
			//记录dao.getConnection的个数
			int connNum = 0;
			//记录连接关闭的个数
			int closeNum = 0;
			while((line = br.readLine()) != null){
				line = line.toUpperCase();
				if(line.contains(".GETCONNECTION")){
					connFlag = true;
					connNum ++;
				}
				if(line.contains(".CLOSE")){
					closeFlag = true;
					closeNum ++;
				}
			}
			if(connFlag){
				if(!closeFlag){
					//说明没有任何关闭连接的方法被调用过
					questionFilePath.add(filePath);
				}else{
					//判断开启的连接个数是否大于关闭的个数
					if(connNum > closeNum){
						questionFilePath.add(filePath);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
