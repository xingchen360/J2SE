package com.noteshare.swing.selectfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
	public static void copyFile(File sourceFile,File desFile){
		BufferedReader bReader = null;
		PrintWriter pw = null;
		try {
			if(!desFile.getParentFile().exists()){
				desFile.getParentFile().mkdirs();
			}
			bReader = new BufferedReader(new FileReader(sourceFile));
			pw = new PrintWriter(new FileOutputStream(desFile));
			String line = null;
			while((line = bReader.readLine()) != null){
				pw.write(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != bReader){
					bReader.close();
				}
				if(null != pw){
					pw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
