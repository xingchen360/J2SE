package com.noteshare.designPatterns.bridge.demo1;

/**
 * 桥梁类(抽象类)
 */
public abstract class Bridge {
	private Driver driver;

	public void connect() {
		driver.connect();
	}

	public void setDriver(Driver driverTemp) {
		this.driver = driverTemp;
	}

	public Driver getDriver() {
		return this.driver;
	}
}
