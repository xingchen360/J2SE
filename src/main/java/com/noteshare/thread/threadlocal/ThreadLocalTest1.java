package com.noteshare.thread.threadlocal;

/**
 * @Title:ThreadLocalTest1.java
 * @Description:ThreadLocal测试类1
 * @author:NoteShare
 * @date:2019年4月22日 下午4:40:28
 */
public class ThreadLocalTest1 {
	/**
	 * 重写initialValue方法设置默认值，如果不设置默认值，下面测试代码不调用set方法的话会报空指针异常
	 */
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return Thread.currentThread().getId() + 100;
		};
	};
	ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
		protected String initialValue() {
			return "initailValue:" + Thread.currentThread().getName();
		};
	};

	ThreadLocal<Person> personLocal = new ThreadLocal<Person>() {
		@Override
		protected Person initialValue() {
			Person p = new Person("init");
			return p;
		}
	};

	public void set(Person pp) {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
		/*Person p = personLocal.get();
		p.setName(Thread.currentThread().getName() + ":person");*/
		pp.setName(Thread.currentThread().getName() + ":person");
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public String getPersonName() {
		return personLocal.get().getName();
	}

	public static void main(String[] args) throws InterruptedException {
		Person pp = new Person("张升");
		final ThreadLocalTest1 test = new ThreadLocalTest1();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		System.out.println(test.getPersonName());
		System.out.println("======================================");
		Thread thread1 = new Thread() {
			public void run() {
				System.out.println(test.getLong() + "===");
				System.out.println(test.getString() + "===");
				System.out.println(test.getPersonName() + "====");
				System.out.println("-------------------------------------");
				test.set(pp);
				System.out.println(test.getLong() + "===");
				System.out.println(test.getString() + "===");
				System.out.println(test.getPersonName() + "====");
				System.out.println("-------------------------------------");
			};
		};
		thread1.start();
		thread1.join();
		test.set(pp);
		System.out.println(test.getLong() + "---");
		System.out.println(test.getString() + "---");
		System.out.println(test.getPersonName() + "---");
	}
}

class Person {

	public Person(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
