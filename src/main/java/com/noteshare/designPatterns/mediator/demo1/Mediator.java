package com.noteshare.designPatterns.mediator.demo1;

public abstract class Mediator {
	//同事A
	protected Colleague colleagueA;
    //同事B
	protected Colleague colleagueB;

    public Mediator(Colleague colleagueA, Colleague colleagueB) {
        this.colleagueA = colleagueA;
        this.colleagueB = colleagueB;
    }

    public abstract void notifyColleagueA();
    public abstract void notifyColleagueB();
}
