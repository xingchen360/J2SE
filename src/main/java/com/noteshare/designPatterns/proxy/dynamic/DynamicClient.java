package com.noteshare.designPatterns.proxy.dynamic;

import com.noteshare.designPatterns.proxy.staticProxy.ILawSuit;
import com.noteshare.designPatterns.proxy.staticProxy.SecondDogWang;

/**
 * @Title: DynamicClient.java
 * @date 2015年6月3日 下午1:33:01
 * @version V1.0
 */
public class DynamicClient {

	public static void main(String[] args) {
		ILawSuit ilLawSuit = new SecondDogWang();
		DynamicProxy dynamicProxy = new DynamicProxy(ilLawSuit);
		ILawSuit ilawsuitProxy =  (ILawSuit) dynamicProxy.getProxy();
		ilawsuitProxy.submit("我有证据证明他签了我的钱。");
		// -------------------------------------------
		A a = new A();
		DynamicProxy dynamicProxyA = new DynamicProxy(a);
		A aProxy = (A) dynamicProxyA.getProxy();
		aProxy.test();
		/**
		 * Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy1 cannot be cast to com.noteshare.designPatterns.proxy.dynamic.A
	at com.noteshare.designPatterns.proxy.dynamic.DynamicClient.main(DynamicClient.java:21)
	
		 */
	}
}


class A{
	public void test(){
		System.out.println("invoke new A().test();");
	}
}