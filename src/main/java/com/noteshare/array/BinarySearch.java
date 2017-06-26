package com.noteshare.array;

import java.util.Arrays;

/**
 * 二分查找法
 * @author Compaq
 */
public class BinarySearch{
	public static int binarySearch(int[] array, int value){
		int low = 0;
		int high = array.length-1;
		int middle = 0;
		while(low <= high){
			middle = (low+high)/2;//0 6    4 6    6 6
			for(int i = 0;i < array.length;i++){
				System.out.print(array[i]+" ");
				if(i == middle)//3 5 6 紧随最中间的指向 后面 打印分隔符
				{
					System.out.print("$$  ");
				}
			}
			if(array[middle] == value){
				return middle;
			}
			if(value < array[middle]){
				high = middle - 1;
			}
			if(value > array[middle]){
				low = middle + 1;
			}
			
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		int[] array = {7,3,9,5,6,8,1};
		int[] array1 = BubbleSort.bubbleSort(array);
		System.out.println("=============");
		//modify-noteshare:可以直接使用Arrays的二分查找方法
		System.out.println(Arrays.binarySearch(array1, 3));
		System.out.println("=============");
		int index = binarySearch(array1,1);
		System.out.println("所在的位置:"+index);
	}

}
