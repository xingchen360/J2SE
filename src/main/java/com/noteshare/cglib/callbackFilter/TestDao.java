package com.noteshare.cglib.callbackFilter;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class TestDao {
	public static void main(String[] args) {
		DaoProxy daoProxy = new DaoProxy();
		DaoAnotherProxy daoAnotherProxy =  new DaoAnotherProxy();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Dao.class);
		enhancer.setCallbacks(new Callback[]{daoProxy,daoAnotherProxy,NoOp.INSTANCE});
		enhancer.setCallbackFilter(new DaoFilter());
		//设置在构造函数中调用的方法不进行拦截
		enhancer.setInterceptDuringConstruction(false);
		Dao dao = (Dao) enhancer.create();
		dao.update();
		System.out.println();
		dao.select();
		System.out.println();
		dao.selectById(1);
	}
}
