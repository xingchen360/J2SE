package com.noteshare.chm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CmdTest {
	public static void main(String[] args) {
		String cmdStr = "cmd /c ipconfig";
		try {
			Process pro = Runtime.getRuntime().exec(cmdStr);
			InputStream is = pro.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while (null != br.readLine()) {
				System.out.println(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
