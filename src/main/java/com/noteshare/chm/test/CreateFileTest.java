package com.noteshare.chm.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class CreateFileTest {
	@Test
	public void createFileTest() throws IOException{
		File file = new File("D:\\aa\\bb\\aa.txt");
		if(!file.exists()){
			file.mkdirs();
		}
	}
}
