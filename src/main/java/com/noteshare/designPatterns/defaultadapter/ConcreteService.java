package com.noteshare.designPatterns.defaultadapter;

/**
 * 缺省的适配器模式，在AWT和SWING中运用的较多。
 * @author Administrator
 */
public class ConcreteService extends ServiceAdapter{
	@Override
	public void service1() {
		System.out.println("执行业务方法");
	}
}
