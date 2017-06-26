/**
 * 
 */
package com.noteshare.array;

import java.util.Arrays;

public class ArrayCopy {
	public static void main(String[] args) {
        int[] ids1 = { 1, 2, 3, 4, 5 };
        int[] ids2 = new int[10];
        /** 源数组,源数组中的起始位置,目标数组,目标数据中的起始位置,要复制的源数组元素的数量*/
        System.arraycopy(ids1, 1, ids2, 3, 3);
        /*[0, 0, 0, 2, 3, 4, 0, 0, 0, 0]*/
        System.out.println(Arrays.toString(ids2));
        
        //数组扩容1
        int[] array1 = new int[10];
        for(int i = 0;i < 100;i++){
        	if(i > array1.length-1){
        		int[] arrayTemp = new int[array1.length + 10];
        		System.arraycopy(array1, 0, arrayTemp, 0, array1.length);
        		array1 = arrayTemp;
        	}
        	array1[i] = i;
        }
        System.out.println(Arrays.toString(array1));
        //数组扩容2
        System.out.println("==================Arrays.copyOf数组扩容===============================");
        int[] array3 = new int[10];
        for(int i = 0;i < 100;i++){
        	if(array3.length-1 < i){
        		array3 = Arrays.copyOf(array3, array3.length + 10);
        	}
        	array3[i] = i;
        }
        System.out.println(Arrays.toString(array3));
        
        
        System.out.println("============Arrays.copyOfRange=====================");
        int[] arrayTemp = Arrays.copyOfRange(ids1, 1, 3);
        System.out.println(Arrays.toString(arrayTemp));
        
        
        
        int[] idsa = { 1, 2, 3, 4, 5 };
        int[] idsb = new int[10];
        /*[0, 1, 2, 3, 0, 0]*/
        System.arraycopy(idsa, 0, idsb, 1, 3);
        System.out.println(Arrays.toString(idsb));
    }
}
