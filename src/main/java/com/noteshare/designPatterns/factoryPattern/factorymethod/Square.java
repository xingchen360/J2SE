package com.noteshare.designPatterns.factoryPattern.factorymethod;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}
