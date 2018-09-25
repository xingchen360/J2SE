package com.noteshare.designPatterns.filter.interceptingFilter;

/**
 * 创建过滤器接口
 */
public interface Filter {
	public void execute(String request);
}
