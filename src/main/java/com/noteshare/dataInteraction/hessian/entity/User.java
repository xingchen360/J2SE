package com.noteshare.dataInteraction.hessian.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 2368176842478981632L;
	String userName = "snoopy";
	String password = "showme";

	public User(String user, String pwd) {
		this.userName = user;
		this.password = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
