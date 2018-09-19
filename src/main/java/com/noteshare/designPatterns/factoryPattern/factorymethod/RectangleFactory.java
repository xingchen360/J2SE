package com.noteshare.designPatterns.factoryPattern.factorymethod;

public class RectangleFactory implements ShapeFactory {

	@Override
	public Shape getShape() {
		return new Rectangle();
	}
}
