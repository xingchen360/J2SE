package com.noteshare.io;

import java.io.File;

/**
 * 遍历目录
 * @Title: FileList.java 
 */
public class FileList {

	public static void main(String[] args) {
		File file = new File("src/main/resources/");
		String[] names = file.list();
		for(String data:names){
			System.out.println(data);
		}
		
		File[] f = file.listFiles();
		for(File data:f){
			System.out.println(data.getName());
		}
	}
	
}