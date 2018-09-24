package com.noteshare.designPatterns.filter.interceptingFilter;

/**
 * 使用 Client 来演示拦截过滤器设计模式
 */
public class InterceptingFilterDemo {
	public static void main(String[] args) {
		FilterManager filterManager = new FilterManager(new Target());
		filterManager.setFilter(new AuthenticationFilter());
		filterManager.setFilter(new DebugFilter());

		Client client = new Client();
		client.setFilterManager(filterManager);
		client.sendRequest("HOME");
	}
}
