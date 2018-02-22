package com.noteshare.jvm.typelifecycle.newInstance;

/**
 * @ClassName			: NewInstance 
 * @Description			: 在Java程序中，类可以被明确或者隐含地实例化。实例化一个类有四种途径；明确地使用new操作符；
 * 						     调用class或者java.lang.Constructor对象的newInstance()方法；调用现有对象的clone()方法；
 * 						     或者通过java.io.ObjectInputStream类的getObject()方法反序列化。
 * @author 				： NoteShare 
 * @date 				： 2017年11月15日 下午10:23:28
 */
public class NewInstance implements Cloneable {
	
	NewInstance() {
		System.out.println("create by invoking newInstance");
	}

	NewInstance(String msg) {
		System.out.println(msg);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		/** 
		 * 以下结果中发现clone未调用构造方法，此处需要探究下
		 *  create with new
			-------------
			create by invoking newInstance
			===============
			create by invoking newInstance
			++++————————+++
		 */
		NewInstance instance1 = new NewInstance("create with new");
		System.out.println("-------------");
		NewInstance instance2 = NewInstance.class.newInstance();
		System.out.println("===============");
		Class<?> cls = Class.forName("com.noteshare.jvm.typelifecycle.newInstance.NewInstance");
		NewInstance instance3 = (NewInstance) cls.newInstance();
		System.out.println("++++————————+++");
		NewInstance instance4 = (NewInstance) instance3.clone();
	}
}
