package com.noteshare.designPatterns.strategy.demo1;

/**
 * 实现减的策略
 */
public class OperationSubstract implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}
