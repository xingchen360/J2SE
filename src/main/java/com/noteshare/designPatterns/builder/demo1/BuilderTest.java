package com.noteshare.designPatterns.builder.demo1;

/**
 * createHumanByDirecotr这个方法带的参数就是我们高智商人的那个类。那我们想造一个运动员，就可以像高智商人那样建好类，然后传进来就可以了！
 */
public class BuilderTest {
	public static void main(String[] args) {
		Director director = new Director();
		Human human = director.createHumanByDirecotr(new SmartManBuilder());
		System.out.println(human.getHead());
		System.out.println(human.getBody());
		System.out.println(human.getHand());
		System.out.println(human.getFoot());
		/*
		输出：
		智商180的头脑
		新的身体
		新的手
		新的脚
		 看，createH
		 */
	}
}
