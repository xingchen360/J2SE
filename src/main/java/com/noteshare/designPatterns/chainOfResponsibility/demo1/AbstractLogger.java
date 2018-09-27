package com.noteshare.designPatterns.chainOfResponsibility.demo1;

/**
 * 抽象的记录器类，设置日志级别和定义了责任链一层层传递的逻辑方法，并预留了各个责任链节点的业务抽象方法
 */
public abstract class AbstractLogger {
	public static int INFO = 1;
	public static int DEBUG = 2;
	public static int ERROR = 3;

	protected int level;

	// 责任链中的下一个元素
	protected AbstractLogger nextLogger;

	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void logMessage(int level, String message) {
		if (this.level <= level) {
			write(message);
		}
		if (nextLogger != null) {
			nextLogger.logMessage(level, message);
		}
	}

	abstract protected void write(String message);
}
