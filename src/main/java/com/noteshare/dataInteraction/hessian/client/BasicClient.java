package com.noteshare.dataInteraction.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.noteshare.dataInteraction.hessian.services.BasicService;

public class BasicClient {
	public static void main(String[] args) throws Exception {
		String url ="http://127.0.0.1:8080/test/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
		BasicService basic = (BasicService) factory.create(BasicService.class, url);
		System.out.println("Hello:" + basic.hello());
		System.out.println("Hello:" + basic.getUser().getUserName());
		System.out.println("Hello:" + basic.getUser().getPassword());
		basic.setGreeting("HelloGreeting");
		System.out.println("Hello:" + basic.hello());
	}
}
