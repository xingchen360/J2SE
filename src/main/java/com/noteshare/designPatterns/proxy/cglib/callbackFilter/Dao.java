package com.noteshare.designPatterns.proxy.cglib.callbackFilter;

/**
 * @Description:使用cglib的CallbackFilter 定义不同的拦截策略，我们希望对Dao中的不同方法采用不同的拦截策略
 * @date:2019年4月11日 下午3:55:31
 */
public class Dao {
	
	public Dao(){
		testConstructor();
	}
	
	public void update() {
		System.out.println("invoke update method !");
	}

	public void select() {
		System.out.println("invoke select method !");
	}
	
	public void selectById(int i){
		System.out.println("invoke selectById method !");
	}
	
	/**
	 * 测试如何让在构造函数中调用的方法不拦截
	 */
	public void testConstructor(){
		System.out.println("invoke testConstructor method !");
	}
}
