package com.noteshare.designPatterns.simpleFactory;

import com.noteshare.designPatterns.factoryPattern.factorymethod.Circle;
import com.noteshare.designPatterns.factoryPattern.factorymethod.Rectangle;
import com.noteshare.designPatterns.factoryPattern.factorymethod.Shape;
import com.noteshare.designPatterns.factoryPattern.factorymethod.Square;

public class ShapeFactory {
	public Shape getShape(String type) {
		if ("circle".equals(type)) {
			return new Circle();
		} else if ("rectangle".equals(type)) {
			return new Rectangle();
		} else if ("square".equals(type)) {
			return new Square();
		} else {
			return null;
		}
	}
}
