package com.noteshare.designPatterns.templatemethod;

public class ConcreteClass extends AbstractClass{

	@Override
	public void method1() {
		System.out.println("step1");
	}

	@Override
	public void method2() {
		System.out.println("step2");
	}

	@Override
	public void method3() {
		System.out.println("step3");
	}

}
