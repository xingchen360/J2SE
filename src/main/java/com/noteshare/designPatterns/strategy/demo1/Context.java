package com.noteshare.designPatterns.strategy.demo1;

/**
 * 环境(Context)角色：持有一个Strategy的引用
 */
public class Context {
	// 策略类
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}
