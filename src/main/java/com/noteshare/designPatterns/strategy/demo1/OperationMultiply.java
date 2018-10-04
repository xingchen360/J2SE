package com.noteshare.designPatterns.strategy.demo1;

/**
 * 实现除的策略
 */
public class OperationMultiply implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 / num2;
	}
}
