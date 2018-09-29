package com.noteshare.designPatterns.interpreter.demo2;

/**
 * 变量，终结符表达式-获取计算变量
 */
public class VariableExpression extends Expression {

	private String key;

	public VariableExpression(String key) {
		this.key = key;
	}

	@Override
	public double Interpret(Context context) {
		return context.getValue(this.key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
