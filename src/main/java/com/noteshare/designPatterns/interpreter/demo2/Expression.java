package com.noteshare.designPatterns.interpreter.demo2;

/**
 * 抽象表达式
 */
public abstract class Expression {
	abstract double Interpret(Context context);
}
