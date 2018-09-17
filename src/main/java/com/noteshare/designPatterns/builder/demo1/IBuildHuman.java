package com.noteshare.designPatterns.builder.demo1;

/**
 * 定义了造人标准的接口
 */
public interface IBuildHuman {
	public void buildHead();

	public void buildBody();

	public void buildHand();

	public void buildFoot();

	public Human createHuman();

}
