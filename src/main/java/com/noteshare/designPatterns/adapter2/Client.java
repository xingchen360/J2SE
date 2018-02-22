package com.noteshare.designPatterns.adapter2;

/**
 * 对象适配器（采取对象组合的方式）推荐使用
 * @author Administrator
 */
public class Client {
	public static void main(String[] args) {
		Target target = new Adapter(new Adaptee());
		target.method1();
	}
}
