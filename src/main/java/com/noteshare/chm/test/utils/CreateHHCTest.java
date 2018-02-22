package com.noteshare.chm.test.utils;

import java.io.IOException;

import org.junit.Test;

import com.noteshare.chm.services.Constant;
import com.noteshare.chm.services.CreateChmRootItemFiles;
import com.noteshare.chm.services.CreateHHC;

public class CreateHHCTest {
	@Test
	public void getFilesStrTest(){
		String filesPath = "D:\\chmtest\\DHTMLref";
		String rootName = filesPath.substring(filesPath.lastIndexOf("\\") + 1);
		CreateChmRootItemFiles createChmRootItemFiles = new CreateChmRootItemFiles(filesPath,Constant.ROORDEEP,rootName);
		try {
			new CreateHHC().createHHCFile("D:\\chm_chx_test1","index.html","chmTest1",createChmRootItemFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
