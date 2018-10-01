package com.noteshare.designPatterns.businessDelegate;

/**
 * 客户端类
 */
public class Client {
	BusinessDelegate businessDelegate;

	public Client(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	public void doTask() {
		businessDelegate.doTask();
	}
}
