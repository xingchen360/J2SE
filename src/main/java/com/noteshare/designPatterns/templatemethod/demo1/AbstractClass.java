package com.noteshare.designPatterns.templatemethod.demo1;
/**
 * 模板方法模式：定义了一个操作中算法的基本骨架，而将一些步骤延伸到子类中去，使得子类可以
 * 不改变一个算法的结构，即可重新定义该算法的某些特定步骤。这里需要复用的是算法结构，也就是
 * 步骤，而步骤的实现可以在子类中完成。如junit3.8中的setUp  test  tearDown和junit4中的
 * before  test after就使用了该模式。
 */
public abstract class AbstractClass {
	public void template() {
		this.method1();
		this.method2();
		this.method3();
	}

	public abstract void method1();

	public abstract void method2();

	public abstract void method3();
}
