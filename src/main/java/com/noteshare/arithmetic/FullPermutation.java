package com.noteshare.arithmetic;

import java.util.ArrayList;

/**
 * @ClassName : FullPermutation
 * @Description : 全排列问题：输入特定正整数n输出从1-n的数字的全排列
 * @author ： NoteShare
 * @date ： 2017年10月8日 上午12:06:16
 */
public class FullPermutation {
	/**
	 * 数组长度
	 */
	private int length;
	//需要进行排列组合的数组
	private int[] array;
	//记录每次的排列组合结果
	private int[] resultTemp;
	//记录哪些元素已经被使用过
	private int[] hold;

	public FullPermutation(int[] array) {
		this.array = array;
		this.length = array.length;
		this.hold = new int[this.length];
		this.resultTemp = new int[this.length];
	}
	/**
	 * @Title			: permute 
	 * @Description		: 打印数列的排列组合
	 * @param step 		：代表数列的中的位置
	 * @author 			： NoteShare
	 * @date 			： 2017年10月8日 上午9:13:47 
	 * @throws
	 */
	public void permute(int step) {
		//列举第一个位置可以放置的元素的情况数
		for (int i = 0; i < array.length; i++) {
			//判断该元素是否已经被使用过,未被使用过才继续执行逻辑，否则结束本次循环
			if(this.hold[i] == 0){
				//放置该位置的元素
				this.resultTemp[step -1] = array[i];
				//如果最后一个位置也填充了元素则可以进行打印该排列了
				if(step == this.resultTemp.length){
					for (int j = 0; j < this.resultTemp.length; j++) {
						System.out.print(this.resultTemp[j] + " ");
					}
					System.out.println(); 
				}else{
					//记录该元素已经被用过，后续只需对排除该元素的子集合进行排列组合即可，子集排列组合完后再释放该元素，进入上一个位置的继续循环
					this.hold[i] = 1;
					//放置下一个位置的元素
					permute(step + 1);
					//递归完后需要返回来释放该元素的使用情况，方便该位置进入下一循环时该元素可以被使用到
					this.hold[i] = 0;
				}
			}
		}
	}
	/**
	 * @Title			: permute2 
	 * @Description		: 通过借助集合来实现
	 * @param step		： 当前在进行第一个位置的填充
	 * @param sunList 	： 需要进行排列组合的子集合
	 * @author 			： NoteShare
	 * @date 			： 2017年10月8日 上午10:18:40 
	 * @throws
	 */
	public void permute2(int step,ArrayList<Integer> sunList){
		//列举第一个位置可以放置的元素的情况数
		for (int i = 0; i < sunList.size(); i++) {
			//获取子集合，并记录此次移出的元素
			int removetemp = sunList.remove(0);
			//放置该位置的元素
			this.resultTemp[step -1] = removetemp;
			//如果该位置是最后一个位置则可以直接打印排列组合
			if(step == this.resultTemp.length){
				for (int j = 0; j < this.resultTemp.length; j++) {
					System.out.print(this.resultTemp[j] + " ");
				}
				System.out.println(); 
			}else{
				//对子集合进行排列组合
				permute2(step + 1,sunList);
			}
			//子集合排序完后归还该元素
			sunList.add(removetemp);
		}
	}
	public static void main(String[] args) {
		int[] array = new int[12];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
			list.add(i + 1);
		}
		FullPermutation fa = new FullPermutation(array);
		long startTime = System.currentTimeMillis();
		fa.permute(1);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//56541
		//第二种方案
		long startTime2 = System.currentTimeMillis();
		fa.permute2(1,list);
		long endTime2 = System.currentTimeMillis();
		System.out.println(endTime2 - startTime2);
		//56924
	}
}
