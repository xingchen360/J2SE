package com.noteshare.dataInteraction.hessian.services.impl;

import com.noteshare.dataInteraction.hessian.entity.User;
import com.noteshare.dataInteraction.hessian.services.BasicService;

public class BasicServiceImpl implements BasicService {
	private String _greeting = "Hello, world";

	public void setGreeting(String greeting) {
		_greeting = greeting;
		System.out.println("set greeting success:" + _greeting);
	}

	public String hello() {
		return _greeting;
	}

	public User getUser() {
		return new User("prance", "meshow");
	}
}
