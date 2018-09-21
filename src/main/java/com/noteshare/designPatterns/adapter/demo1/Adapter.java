package com.noteshare.designPatterns.adapter.demo1;

/**
 * 适配器
 * @author Administrator
 */
public class Adapter extends Adaptee implements Target {

	@Override
	public void method1() {
		this.method2();    
	}
}
