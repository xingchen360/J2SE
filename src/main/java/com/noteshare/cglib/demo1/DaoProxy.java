package com.noteshare.cglib.demo1;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DaoProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println(arg0.getClass().getName());
		System.out.println(Arrays.toString(arg2));
		System.out.println(arg3.getSuperName());
		System.out.println(arg3.getClass().getName());
		System.out.println(" before invoke " + arg1.getName() + "!");
		arg3.invokeSuper(arg0, arg2);
		System.out.println(" after invoke " + arg1.getName() + "!");
		return arg0;
	}
}
