package com.noteshare.designPatterns.filter.interceptingFilter;

/**
 * 调试过滤器
 */
public class DebugFilter implements Filter {
	public void execute(String request) {
		System.out.println("request log: " + request);
	}
}
