package com.noteshare.designPatterns.proxy.cglib.demo1;

import net.sf.cglib.proxy.Enhancer;

public class TestDao {
	public static void main(String[] args) {
		DaoProxy daoProxy = new DaoProxy();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Dao.class);
		enhancer.setCallback(daoProxy);
		Dao dao = (Dao) enhancer.create();
		dao.update();
		System.out.println("=================================");
		dao.select();
		System.out.println("------------");
		System.out.println(dao.updateById(1));
		/*System.out.println(i);  如果添加输出则会在不加的基础上 多出以下报错，前面的输出仍然政策
		Exception in thread "main" java.lang.ClassCastException: com.noteshare.cglib.demo1.Dao$$EnhancerByCGLIB$$6a4f2d82 cannot be cast to java.lang.Integer
		at com.noteshare.cglib.demo1.Dao$$EnhancerByCGLIB$$6a4f2d82.updateById(<generated>)
		at com.noteshare.cglib.demo1.TestDao.main(TestDao.java:16)*/
	}
}
