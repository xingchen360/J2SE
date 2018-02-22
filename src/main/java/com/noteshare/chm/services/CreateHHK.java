package com.noteshare.chm.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.noteshare.chm.entity.ItemFile;

public class CreateHHK {
	/**
	 * @param destinationPath 生成的hhk文件存放路径
	 * @param rootItemFiles 需要转换的文件夹对应的createChmRootItemFiles对象
	 * @throws IOException
	 */
	public void createHHKFile(String destinationPath,CreateChmRootItemFiles createChmRootItemFiles,String fileName) throws IOException{
		//拼接hhk文件头部
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">\n");
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<meta name=\"GENERATOR\" content=\"Microsoft&reg; HTML Help Workshop 4.1\">\n");
		sb.append("</HEAD>\n");
		sb.append("<BODY>\n");
		sb.append("<OBJECT type=\"text/site properties\">\n");
		sb.append("</OBJECT>\n");
		sb.append("<UL>\n");
		//创建hhk文件存放路径及文件
		File files = new File(destinationPath);
		if(!files.exists()){
			files.mkdirs();
		}
		//创建hhc文件
		File file = new File(destinationPath + File.separator + fileName + ".hhk");
		if(file.exists()){
			file.createNewFile();
		}
		//生成hhk正文
		//拼接hhk正文
		StringBuffer hhkContent = new StringBuffer();
		List<ItemFile> itemFileList = createChmRootItemFiles.getItemFileList();
		if(null != itemFileList && itemFileList.size() !=0){
			for(ItemFile itemFile : itemFileList){
				hhkContent.append(itemFile.getFileHHKString());
			}
		}
		sb.append(hhkContent.toString());
		//拼接hhk尾部
		sb.append("</UL>\n");
		sb.append("</BODY>\n");
		sb.append("</HTML>\n");
		FileWriter fw = new FileWriter(file);
		fw.write(sb.toString());
		fw.close();
	}
}
