package com.noteshare.designPatterns.decorator.demo1;

/**
 * 装饰类
 */
public class Decorator implements Component {
	public Component component;

	public Decorator(Component component) {
		this.component = component;
	}

	public void biu() {
		this.component.biu();
	}
}
