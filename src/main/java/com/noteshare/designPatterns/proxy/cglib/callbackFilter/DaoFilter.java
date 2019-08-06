package com.noteshare.designPatterns.proxy.cglib.callbackFilter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Description:过滤器，决策调用那个代理方法
 * @author:陈海新
 * @date:2019年4月11日 下午4:01:30
 */
public class DaoFilter implements CallbackFilter {

	/**
	 * the index into the array of callbacks (as specified by {@link Enhancer#setCallbacks}) to use for the method
	 * 意思是返回值是对应Enhancer.setCallbacks设置的代理对象数组的索引值
	 */
	@Override
	public int accept(Method method) {
		if(method.getName().equals("select")){
			return 0;
		}else if(method.getName().equals("selectById")){
			return 2;
		}
		return 1;
	}
}
