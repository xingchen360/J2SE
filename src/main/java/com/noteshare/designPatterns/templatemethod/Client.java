package com.noteshare.designPatterns.templatemethod;

public class Client {
	public static void main(String[] args) {
		AbstractClass ac = new ConcreteClass();
		ac.template();
	}
}
