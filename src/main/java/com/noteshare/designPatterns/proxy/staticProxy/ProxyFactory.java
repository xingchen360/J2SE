package com.noteshare.designPatterns.proxy.staticProxy;

/**
 * @Title:ProxyFactory.java
 * @Description:代理工厂类
 * @author:NoteShare
 * @date:2019年4月30日 上午11:30:44
 */
public class ProxyFactory {
	public static ILawSuit getProxy(ILawSuit ilawsuit) {
		return new ProxyLawyer(ilawsuit);
	}
}
