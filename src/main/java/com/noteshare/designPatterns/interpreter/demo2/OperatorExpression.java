package com.noteshare.designPatterns.interpreter.demo2;

/**
 * 非终结符表达式
 */
public abstract class OperatorExpression extends Expression {
	// 左操作数
	private Expression left;
	// 右操作数
	private Expression right;
	
	public OperatorExpression(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}
}
