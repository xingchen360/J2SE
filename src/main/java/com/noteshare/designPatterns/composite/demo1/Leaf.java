package com.noteshare.designPatterns.composite.demo1;

public class Leaf implements Component{
	@Override
	public void doSomething() {
		System.out.println("执行方法");
	}
}
