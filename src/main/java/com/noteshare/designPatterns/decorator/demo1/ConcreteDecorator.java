package com.noteshare.designPatterns.decorator.demo1;

/**
 * 具体装饰类
 */
public class ConcreteDecorator extends Decorator {
	public ConcreteDecorator(Component component) {
		super(component);
	}

	public void biu() {
		System.out.println("ready?go!");
		this.component.biu();
	}
}
