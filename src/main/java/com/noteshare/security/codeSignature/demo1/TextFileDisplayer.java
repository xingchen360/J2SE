package com.noteshare.security.codeSignature.demo1;

import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFileDisplayer implements Doer{
	
	private String fileName;
	
	public TextFileDisplayer(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void doYourThing() {
		try {
			FileReader fr = new FileReader(fileName);
			CharArrayWriter caw = new CharArrayWriter();
			int c;
			try {
				while ((c = fr.read()) != -1) {
					caw.write(c);
					
				}
				System.out.println(caw.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
