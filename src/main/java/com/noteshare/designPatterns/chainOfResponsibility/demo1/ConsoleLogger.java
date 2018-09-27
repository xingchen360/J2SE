package com.noteshare.designPatterns.chainOfResponsibility.demo1;

/**
 * 扩展了该记录器类的实体类--打印日志记录器
 */
public class ConsoleLogger extends AbstractLogger {

	public ConsoleLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}
}
