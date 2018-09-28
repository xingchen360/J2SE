package com.noteshare.designPatterns.command;

/**
 * 接收者，真正执行命令的对象。任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
 */
public class Receiver {
	public void doAction(){
		System.out.println("执行操作");
	}
}
