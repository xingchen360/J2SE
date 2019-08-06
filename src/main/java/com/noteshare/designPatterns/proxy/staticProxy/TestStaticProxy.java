package com.noteshare.designPatterns.proxy.staticProxy;
/**
 * @Title:TestStaticProxy.java
 * @Description:测试类
 * @author:NoteShare
 * @date:2019年4月30日 上午11:34:30
 */
public class TestStaticProxy {
	public static void main(String[] args) {
		// 诉讼人：王二狗
		SecondDogWang secondDogWang = new SecondDogWang();
		//请个律师代理诉讼
		ProxyLawyer proxyLawyer = (ProxyLawyer) ProxyFactory.getProxy(secondDogWang);
		// 律师代替 提起诉讼
		proxyLawyer.submit("2019-02-08到2019-04-28我帮其干了如下事情，其未支付工资。");
	}
}
