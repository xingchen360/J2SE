package com.noteshare.designPatterns.interpreter.demo2;

public class SubExpression  extends OperatorExpression{

	public SubExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	double Interpret(Context context) {
		return this.getLeft().Interpret(context) - this.getRight().Interpret(context);
	}
}
