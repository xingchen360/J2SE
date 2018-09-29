package com.noteshare.designPatterns.serviceLocatorPattern.demo1;

/**
 * 实体服务2
 */
public class Service2 implements Service {
	public void execute() {
		System.out.println("Executing Service2");
	}

	@Override
	public String getName() {
		return "Service2";
	}
}
