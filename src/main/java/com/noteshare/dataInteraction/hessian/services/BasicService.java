package com.noteshare.dataInteraction.hessian.services;

import com.noteshare.dataInteraction.hessian.entity.User;

public interface BasicService {
	public void setGreeting(String greeting);

	public String hello();

	public User getUser();
}
