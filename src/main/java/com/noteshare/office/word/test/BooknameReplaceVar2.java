/******************************************************************************
* Copyright (C) 2019 NoteShare 
* 网站：itnoteshare.com
*****************************************************************************/
package com.noteshare.office.word.test;

import java.util.HashMap;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

/**
 * @Title:BooknameReplaceVar2.java
 * @Description:替換指定变量
 * @author:NoteShare
 * @date:2019年11月27日 下午8:04:02
 */
public class BooknameReplaceVar2 {
	public static void main(String[] args) throws Exception {
		String template = "D:/temp/日报.docx";
		boolean save = true;
		String outputfilepath = "D:/temp/日报_var2.docx";
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(template));
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		// 需要替换的map
		HashMap<String, String> mappings = new HashMap<String, String>();
		mappings.put("station", "某某站点名称");
		long start = System.currentTimeMillis();
		documentPart.variableReplace(mappings);
		long end = System.currentTimeMillis();
		long total = end - start;
		System.out.println("Time: " + total);
		// Save it
		if (save) {
			SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
			saver.save(outputfilepath);
		} else {
			System.out.println(XmlUtils.marshaltoString(documentPart.getJaxbElement(), true, true));
		}
	}
}
