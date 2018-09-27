package com.noteshare.designPatterns.chainOfResponsibility.demo1;

/**
 * 错误日志记录器
 */
public class ErrorLogger extends AbstractLogger {

	public ErrorLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Error Console::Logger: " + message);
	}
}
