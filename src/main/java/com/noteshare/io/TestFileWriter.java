package com.noteshare.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @Title: TestFileWriter.java 
 */
public class TestFileWriter {

	public static void main(String[] args) throws IOException {
		FileWriter fw = null;
		try {
			fw =new FileWriter("src/main/resources/filewriter.txt");
			fw.write("hello");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		    fw.close();
		}
	}
	
}