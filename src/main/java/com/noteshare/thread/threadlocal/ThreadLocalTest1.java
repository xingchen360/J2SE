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

	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalTest1 test = new ThreadLocalTest1();
		System.out.println(test.getLong());
		System.out.println(test.getString());

		Thread thread1 = new Thread() {
			public void run() {
				test.set();
				System.out.println(test.getLong() + "===");
				System.out.println(test.getString() + "===");
			};
		};
		thread1.start();
		thread1.join();
		test.set();
		System.out.println(test.getLong() + "---");
		System.out.println(test.getString() + "---");
	}
}
