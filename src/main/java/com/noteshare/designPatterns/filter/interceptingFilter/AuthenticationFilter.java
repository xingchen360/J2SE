package com.noteshare.designPatterns.filter.interceptingFilter;

/**
 * 创建实体过滤器。
 */
public class AuthenticationFilter implements Filter {
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}
}
