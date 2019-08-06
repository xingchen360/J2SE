package com.noteshare.designPatterns.proxy.cglib.callbackFilter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DaoProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("DaoProxy: before invoke " + arg1.getName() + "!");
		arg3.invokeSuper(arg0, arg2);
		System.out.println("DaoProxy: after invoke " + arg1.getName() + "!");
		return arg0;
	}
}
