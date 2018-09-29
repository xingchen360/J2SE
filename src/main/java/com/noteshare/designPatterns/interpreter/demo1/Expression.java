package com.noteshare.designPatterns.interpreter.demo1;

/**
 * 抽象表达式，声明一个所有的具体表达式都需要实现的抽象接口；这个接口主要是一个interpret()方法，称做解释操作
 */
public interface Expression {
	/**
	 * 解释器核心方法
	 * 
	 * @param context
	 * @return
	 */
	public boolean interpret(String context);
}
