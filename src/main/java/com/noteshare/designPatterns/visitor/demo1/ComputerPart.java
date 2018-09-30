package com.noteshare.designPatterns.visitor.demo1;

public interface ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor);
}
