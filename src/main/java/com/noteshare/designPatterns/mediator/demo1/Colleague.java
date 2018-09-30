package com.noteshare.designPatterns.mediator.demo1;

public abstract class Colleague {
	// 中介
	protected Mediator mediator;

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void operation();
}
