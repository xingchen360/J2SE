package com.noteshare.chm.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.noteshare.chm.entity.ItemFiles;

public class CreateHHC {

	/**
	 * @param destinationPath 生成的hhc文件存放路径
	 * @param indexPath 首页
	 * @param fileName hhc文件名
	 * @param createChmRootItemFiles 需要转换的文件夹对应的ItemFiles对象
	 * @throws IOException 
	 */
	public void createHHCFile(String destinationPath,String indexPath,String fileName,CreateChmRootItemFiles createChmRootItemFiles) throws IOException{
		//拼接hhc头部
		StringBuffer sb = new StringBuffer("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">\n");
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<meta name=\"GENERATOR\" content=\"Microsoft&reg; HTML Help Workshop 4.1\">\n");
		sb.append("</HEAD>\n");
		sb.append("<BODY>\n");
		
		//拼接chm的标题对象
		sb.append("\t<OBJECT type=\"text/site properties\">\n");
		sb.append("\t <param name=\"Window Styles\" value=\"0x800025\">\n");
		sb.append("\t <param name=\"comment\" value=\"title:"+ fileName + "\">\n");
		sb.append("\t <param name=\"comment\" value=\"base:"+ indexPath + "\">\n");
		sb.append("\t</OBJECT>\n");
		sb.append("\t<UL>\n");
		
		ItemFiles itemFileResult = createChmRootItemFiles.getItemFiles();
		File files = new File(destinationPath);
		if(!files.exists()){
			files.mkdirs();
		}
		//创建hhc文件
		File file = new File(destinationPath + File.separator + fileName + ".hhc");
		if(file.exists()){
			file.createNewFile();
		}
		String hhcStr = itemFileResult.getFilesHHCStr();
		//拼接hhc正文
		sb.append(hhcStr);
		//拼接hhc尾部
		sb.append("\t</UL>\n");
		sb.append("</BODY>\n");
		sb.append("</HTML>\n");
		FileWriter fw = new FileWriter(file);
		fw.write(sb.toString());
		fw.close();
	}
}
