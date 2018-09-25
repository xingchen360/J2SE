package com.noteshare.designPatterns.decorator.demo1;

/**
 * 这样一个基本的装饰器体系就出来了，当我们想让Component在打印之前都有一个ready？go！的提示时，
 * 就可以使用ConcreteDecorator类了。具体方式如下：
 */
public class Client {
	public static void main(String[] args) {
		// 使用装饰器
		Component component = new ConcreteDecorator(new ConcretComponent());
		component.biu();
	}
}
