package com.noteshare.designPatterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: DynamicProxy.java
 * @date 2015年6月3日 下午1:31:37
 * @version V1.0
 */
public class DynamicProxy implements InvocationHandler {

	private Object target;

	public DynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 方法执行前
		before();
		// 被代理对象的方法执行
		Object result = method.invoke(target, args);
		// 方法执行后
		after();
		// 被代理的方法的返回值
		return result;
	}

	/**
	 * 获取目标对象的代理对象
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	private void before() {
		System.out.println("Before");
	}

	private void after() {
		System.out.println("After");
	}
}