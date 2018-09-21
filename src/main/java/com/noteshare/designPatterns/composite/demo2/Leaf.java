package com.noteshare.designPatterns.composite.demo2;

import java.util.List;

public class Leaf implements Component{

	@Override
	public void add(Component component) {
		
	}

	@Override
	public void doSomething() {
		System.out.println("执行方法");
	}

	@Override
	public List<Component> getAll() {
		return null;
	}

	@Override
	public void remove(Component component) {
		
	}

}
