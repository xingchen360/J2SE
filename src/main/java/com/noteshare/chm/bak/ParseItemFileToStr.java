package com.noteshare.chm.bak;

import com.noteshare.chm.entity.ItemFile;
import com.noteshare.chm.entity.ItemLI;

public class ParseItemFileToStr {
	public String getFileStr(ItemFile itemFile){
		StringBuffer fileStr = new StringBuffer("");
		//获取文件深度
		int deep = itemFile.getDeep();
		//获取文件ItemLI对象
		ItemLI li = itemFile.getLi();
		//记录制表符
		StringBuffer ttStr = new StringBuffer("");
		for(int i = 0 ;i <= deep; i++){
			fileStr.append("\t");
			ttStr.append("\t");
		}
		//文件字符串开始
		fileStr.append("<LI>");
		//文件字符串Object对象拼接开始
		fileStr.append("<OBJECT type=\"");
		//--------------------拼接Object类型开始-------------
		fileStr.append(li.getType());
		fileStr.append("\">\n");
		//---------------------参数拼接开始-------------------
		//Name参数拼接
		//制表符
		fileStr.append(ttStr.toString() + "\t");
		fileStr.append("<param name=\"Name\" value=\"");
		fileStr.append(li.getName() + "\">\n");
		//Local参数拼接
		//制表符
		fileStr.append(ttStr.toString() + "\t");
		fileStr.append("<param name=\"Local\" value=\"");
		fileStr.append(li.getLocal() + "\">\n");
		//拼接制表符
		fileStr.append(ttStr.toString());
		fileStr.append("</OBJECT>");
		//--------------------拼接Object类型结束-------------
		System.out.println(fileStr.toString());
		return fileStr.toString();
	}
}
