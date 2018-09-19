package com.noteshare.designPatterns.factoryPattern.abstractFactory;

public class LinuxButton implements Button {

	@Override
	public void processEvent() {
		System.out.println("Inside LinuxButton::processEvent() method.");
	}
}
