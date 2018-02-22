package com.noteshare.chm.services;

import java.io.IOException;


public class CHM {
	/**
	 * @param hhpPath hhp文件路径
	 */
	public void createCHM(String hhpPath){
//		List<String> chmList = new ArrayList<String>();
//		String pan = hhpPath.substring(0,hhpPath.indexOf(":"));
//		chmList.add("cmd /c" + pan + ":");
//		chmList.add("cd " + hhpPath.substring(0,hhpPath.lastIndexOf("\\")));
//		chmList.add("hhc.exe " + hhpPath.substring(hhpPath.lastIndexOf("\\") + 1));
//		String[] chmStr = (String[]) chmList.toArray(new String[0]);
		String chmStr = "cmd /c hhc.exe " + hhpPath;
		try {
			Runtime.getRuntime().exec(chmStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//TODO:反编译chm
	public void decodeCHM(String chmPath){
		
	}
	
	
	
	
}
