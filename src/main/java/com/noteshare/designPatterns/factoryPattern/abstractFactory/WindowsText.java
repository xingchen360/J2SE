package com.noteshare.designPatterns.factoryPattern.abstractFactory;

public class WindowsText implements Text {

	@Override
	public void getWholeText() {
		System.out.println("Inside WindowsText::getWholeText() method.");
	}
}
