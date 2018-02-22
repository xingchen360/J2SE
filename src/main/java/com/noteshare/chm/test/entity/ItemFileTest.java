package com.noteshare.chm.test.entity;

import java.io.File;

import org.junit.Test;

import com.noteshare.chm.entity.ItemFile;
import com.noteshare.chm.entity.ItemLI;

public class ItemFileTest {
	@Test
	public void parseFileToStr(){
		File file = new File("D:\\chmtest\\DHTMLref\\behavior.html");
		String liName = file.getName();
		String liType = "text/sitemap";
		String liLocal = "DHTMLref/" + file.getName();
		ItemLI li = new ItemLI();
		li.setName(liName);
		li.setType(liType);
		li.setLocal(liLocal);
		ItemFile itemFile = new ItemFile(1, li);
		String str = itemFile.getFileHHCString();
		System.out.println(str);
	}
}
