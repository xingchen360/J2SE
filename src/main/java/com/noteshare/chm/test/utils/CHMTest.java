package com.noteshare.chm.test.utils;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.noteshare.chm.services.CHM;
import com.noteshare.chm.services.Constant;
import com.noteshare.chm.services.CreateChmRootItemFiles;
import com.noteshare.chm.services.CreateHHP;

public class CHMTest {
	@Ignore
	@Test
	public void chmTest() throws IOException{
		CHM chm = new CHM();
		CreateHHP hhp = new CreateHHP();
		CreateChmRootItemFiles createChmRootItemFiles = new CreateChmRootItemFiles("d:\\a\\author",Constant.ROORDEEP,"author");
		String hhpPath = hhp.createHHPFile("d:\\a", "chmTest1", createChmRootItemFiles, "ie3.css.html", "d:\\a\\chm_chx.chm", "Yes", "chm_chx", "ie3.css.html");
		chm.createCHM(hhpPath);
	}
	//@Ignore
	@Test
	public void chmTest2() throws IOException{
		CHM chm = new CHM();
		CreateHHP hhp = new CreateHHP();
		CreateChmRootItemFiles createChmRootItemFiles = new CreateChmRootItemFiles("D:\\chmc\\DHTMLref",Constant.ROORDEEP,"DHTMLref");
		String hhpPath = hhp.createHHPFile("D:\\chmc", "test1", createChmRootItemFiles, "", "D:\\chmc\\test.chm", "Yes", "test", "ie3.css.html");
		chm.createCHM(hhpPath);
	}
}
