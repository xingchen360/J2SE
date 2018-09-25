package com.noteshare.designPatterns.bridge.demo1;

public class MysqlDriver implements Driver {
	@Override
	public void connect() {
		System.out.println("连接Mysql数据库");
	}
}
