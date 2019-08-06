package com.noteshare.designPatterns.proxy.cglib.callbackFilter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Description:Dao的另外一个拦截策略
 * @date:2019年4月11日 下午3:58:23
 */
public class DaoAnotherProxy implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("DaoAnotherProxy:before invoke " + method.getName() + " method !");
		proxy.invokeSuper(obj, args);
		System.out.println("DaoAnotherProxy:after invoke " + method.getName() + " method !");
		return obj;
	}
}
