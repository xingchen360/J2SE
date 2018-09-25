package com.noteshare.designPatterns.bridge.demo1;

public class BridgeTest {
	public static void main(String[] args) {
		Bridge myBridge = new MyBridge();

		Driver mysqlDriver = new MysqlDriver();
		myBridge.setDriver(mysqlDriver);
		myBridge.connect();

		Driver sqlServerDriver = new SqlServerDriver();
		myBridge.setDriver(sqlServerDriver);
		myBridge.connect();
	}
}
