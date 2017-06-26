package com.noteshare.array;

import java.util.Arrays;

public class ArrayDefined{

	/**
	 * 定义数组的三种方式
	 * 数组的长度一旦确定就无法更改 public final int
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args){
		byte[] array1 = new byte[10];
		
		byte array2[] = new byte[10];
		
		byte[] array11 = {1,2,3,4};
		System.out.println(array11.toString());
		
		byte[] array3 = new byte[]{1,2,3,4};
		
		System.out.println(Arrays.toString(array3));
		
		String[] strs1 = {"1","2","3"};
		String[] strs2 = new String[]{"1","2","3","4"};
		String[] strs3 = new String[10];
	}
}
