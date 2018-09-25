package com.noteshare.designPatterns.filter.interceptingFilter;

public class Target {
	public void execute(String request) {
		System.out.println("Executing request: " + request);
	}
}
