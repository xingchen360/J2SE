package com.noteshare.designPatterns.strategy.demo1;

/**
 * 抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
 */
public interface Strategy {
	public int doOperation(int num1, int num2);
}
