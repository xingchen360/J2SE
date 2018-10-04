package com.noteshare.designPatterns.strategy.demo1;

/**
 * 加的策略
 */
public class OperationAdd implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}
