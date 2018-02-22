package com.noteshare.chm.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateHHP {
	/**
	 * @param destinationPath hhp文件存放路径
	 * @param hhpFileName hhp文件名称
	 * @param createChmRootItemFiles 要转换的文件夹对应的ItemFiles对象
	 * @param indexPath hhc文件的base首页
	 * @param chmPath chm文件存放路径
	 * @param showProgress 是否显示生成chm文件的进度条
	 * @param title chm文件的标题
	 * @param topicPath chm文件首页
	 * @return String 返回hhp文件路径
	 * @throws IOException
	 */
	public String createHHPFile(String destinationPath,String hhpFileName,CreateChmRootItemFiles createChmRootItemFiles
			,String indexPath,String chmPath,String showProgress,String title,String topicPath) throws IOException{
		new CreateHHC().createHHCFile(destinationPath,indexPath,hhpFileName,createChmRootItemFiles);
		new CreateHHK().createHHKFile(destinationPath,createChmRootItemFiles,hhpFileName);
		File hhpFile = new File(destinationPath + File.separator + hhpFileName + ".hhp");
		String hhpStr = getHHPStr(hhpFileName,chmPath,showProgress,title,topicPath);
		FileWriter fw = new FileWriter(hhpFile);
		fw.write(hhpStr);
		fw.close();
		return hhpFile.getAbsolutePath();
	}
	/**
	 * @param hhpFileName hhp 文件名称
	 * @param chmName 生成的chm文件的绝对路径
	 * @param showProgress 是否显示进度条 Yes / NO
	 * @param title 标题
	 * @param topicPath 开始页，即刚打开CHM显示的页面
	 * @return 返回hhp文件的路径
	 */
	private String getHHPStr(String hhpFileName,String chmPath,String showProgress,String title,String topicPath){
		StringBuffer sb = new StringBuffer();
		sb.append("[OPTIONS]\n\n");
		sb.append("Compatibility=1.1 or later\n\n");
		sb.append("Compiled file=" + chmPath + "\n\n");
		sb.append("Contents file=" + hhpFileName + ".hhc\n\n");
		//是否显示进度条
		sb.append("Display compile progress=" + showProgress + "\n\n");
		sb.append("Index file=" + hhpFileName + ".hhk\n\n");
		sb.append("Language=0x804\n\n");
		sb.append("title=" + title + "\n\n");
		sb.append("Default topic=" + topicPath + "\n\n");
		sb.append("ImageType=Folder\n\n");
		sb.append("[INFOTYPES]");
		return sb.toString();
	}
}
