package com.noteshare.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
/**
 * 
 * @FileName    : Transcode
 * @Description : 文件编码转换器
 * @version     : 1.0
 * Create Date  : 2014-11-12 上午11:47:10
 */
public class GBKToUTF8Transcode {
	/**
	  * @Title: transcode 
	  * @Description: 源代码乱码转换器 
	  * @param sourcePath 源代码路径
	  * @param oldCode 源代码原来的编码格式
	  * @param newCode 源代码需要转换成的编码格式
	  * @param extensions an array of extensions, ex. {"java","xml"}. If this parameter is null, all files are returned   
	  * @return：void
	 */
	public void transcode(String sourcePath,String oldCode,String newCode,String[] extensions){
		Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(sourcePath), extensions, true);
		for (File gbkFile : javaGbkFileCol) { 
		      try {
				FileUtils.writeLines(new File(gbkFile.getAbsolutePath()), newCode, FileUtils.readLines(gbkFile,oldCode));
			} catch (IOException e) {
				e.printStackTrace();
			}        
		 }
	}
	@Test
	public void test(){
		new GBKToUTF8Transcode().transcode("C:/Users/chx/Desktop/alisoft-xplatform-asf-cache-2.5.1-src资料/alisoft-xplatform-asf-cache-2.5.1-src资料/alisoft-xplatform-asf-cache-2.5.1-src","GBK","UTF-8",null);
	}
}
