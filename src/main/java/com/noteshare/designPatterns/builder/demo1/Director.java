package com.noteshare.designPatterns.builder.demo1;

/**
 * 这个director类呢，重点在于createHumanByDirecotr的参数是我们的造人标准的接口。这样一来，
 * 只要是实现了我们的这个接口的类，就都可以作为参数，我们刚刚不是造了一个高智商的人吗？ 那我们如果想造一个身体运动能力出色的人，
 * 也就是运动员，这个director也可以启动这个造人过程，只要我们把这个运动员的类先写好
 */
public class Director {
	public Human createHumanByDirecotr(IBuildHuman bh) {
		bh.buildBody();
		bh.buildFoot();
		bh.buildHand();
		bh.buildHead();
		return bh.createHuman();
	}
}
