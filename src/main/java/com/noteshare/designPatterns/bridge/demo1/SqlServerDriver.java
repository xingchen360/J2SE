package com.noteshare.designPatterns.bridge.demo1;

public class SqlServerDriver implements Driver {
	@Override
	public void connect() {
		System.out.println("连接SQLServer数据库");
	}
}
