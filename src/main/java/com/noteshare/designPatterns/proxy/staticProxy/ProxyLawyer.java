package com.noteshare.designPatterns.proxy.staticProxy;

/**
 * @Title:ProxyLawyer.java
 * @Description:代理律师诉讼类
 * @author:NoteShare
 * @date:2019年4月30日 上午11:28:58
 */
public class ProxyLawyer implements ILawSuit {
	/**
	 * 持有要代理的那个对象
	 */
	ILawSuit plaintiff;

	public ProxyLawyer(ILawSuit plaintiff) {
		this.plaintiff = plaintiff;
	}

	@Override
	public boolean submit(String proof) {
		// 在这之前可以干自己的其他事情
		System.out.println("1：律师向诉讼了了解情况！");
		plaintiff.submit(proof);
		System.out.println("2：律师帮诉讼人进行辩护！");
		return true;
	}
}
