package com.noteshare.arithmetic;

/**
 * @ClassName			: FibonacciSequence 
 * @Description			: 斐波那契数列：像这么1、1、2、3、5、8、13、21、34、……一个数列，从第三项开始每项都为前两项的和
 * @author 				： NoteShare 
 * @date 				： 2017年10月6日 下午11:15:46
 */
public class FibonacciSequence {

	public static void main(String[] args) {
		FibonacciSequence fbs = new FibonacciSequence();
		//1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181
		for (int i = 1; i < 20; i++) {
			int n = fbs.getNResult(i);
			System.out.print(n + " ");
		}
	}
	
	/**
	 * @Title			: getNResult 
	 * @Description		: 求斐波那契数列第n项的值
	 * @return 			： int 返回斐波那契数列第n项的值,如果返回-1则代表输入非法
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午11:17:29 
	 * @throws
	 */
	private int getNResult(int n){
		if(n < 0){
			return -1;
		}else if(n < 3){
			return 1;
		}else{
			return getNResult(n-1) + getNResult(n - 2);
		}
	}
}
