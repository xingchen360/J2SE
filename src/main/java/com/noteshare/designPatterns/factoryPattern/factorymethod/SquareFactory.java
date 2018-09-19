package com.noteshare.designPatterns.factoryPattern.factorymethod;

public class SquareFactory implements ShapeFactory {

	@Override
	public Shape getShape() {
		return new Square();
	}
}
