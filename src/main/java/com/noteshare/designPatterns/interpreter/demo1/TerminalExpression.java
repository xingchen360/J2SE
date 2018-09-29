package com.noteshare.designPatterns.interpreter.demo1;

/**
 * 终结符表达式，实现了抽象表达式所要求的接口；文法中的每一个终结符都有一个具体终结表达式与之相对应。
 * 比如公式R=R1+R2，R1和R2就是终结符，对应的解析R1和R2的解释器就是终结符表达式。
 */
public class TerminalExpression implements Expression {

	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	@Override
	public boolean interpret(String context) {
		if (context.contains(data)) {
			return true;
		}
		return false;
	}
}
