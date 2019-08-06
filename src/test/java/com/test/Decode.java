package com.test;

import com.szboanda.platform.common.utils.security.PasswordHelper;

/**
 * @Title:Decode.java
 * @author:陈海新
 * @date:2019年3月28日 上午9:30:18
 */
public class Decode {
	public static void main(String[] args) {
		String pass = PasswordHelper.dencryptString("e5DXZeXlzro=", "DES");
		System.out.println(pass);
	}
}
