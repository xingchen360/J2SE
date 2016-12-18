package com.noteshare.thread;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @ClassName			: DaemonThreadTest2 
 * @Description			: 后台线程运行情况测试类：
 * @author 				： NoteShare
 * @date 				： 2016年12月18日 下午12:10:38
 */
public class DaemonThreadTest implements Runnable {
	/**
	 * 线程名字
	 */
	private String name;
	/**
	 * 延迟时间
	 */
	private long delay;
	/**
	 * <p>Title				: 无参构造方法</p> 
	 * <p>Description		: 无参构造方法</p>
	 */
	public DaemonThreadTest() {
	}
	
	public void run() {
		try {
			while (true) {
				Thread.sleep(this.delay);
				System.out.println(this.name);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	/**
	 * @Title			: testDeamonThread 
	 * @Description		: 后台线程执行情况测试 ======junit不支持多线程测试请不要使用此方法进行测试，此处只是做个记录，以防后来人继续踩坑			
	 * @author 			： NoteShare
	 * @date 			： 2016年12月18日 下午12:18:52 
	 * @throws
	 */
	@Ignore
	@Deprecated
	public void testDaemonThread(){
		DaemonThreadTest r = new DaemonThreadTest();
		r.setDelay(2000);
		r.setName("后台线程");
		Thread t = new Thread(r);
		t.setDaemon(true); // 后台线程
		t.start();
		try {
			System.out.println("====主线程等待输入：输入完之后主线程结束，观察后台线程运行情况！===");
			System.in.read();
		} catch (Exception e) {
			System.out.println("end main");
		}
	}
	
	/**
	 * @Title			: main 
	 * @Description		: main方法和测试方法的测试结果是不一样的，注意：junit不支持多线程测试
	 * @author 			： NoteShare
	 * @date 			： 2016年12月18日 下午12:46:15 
	 * @throws
	 */
	public static void main(String[] args) {
		DaemonThreadTest r = new DaemonThreadTest();
		r.setDelay(2000);
		//r.setName("用户线程");
		r.setName("后台线程");
		Thread t = new Thread(r);
		//t.setDaemon(false); // 前台线程===用户线程===普通线程
		t.setDaemon(true); // 后台线程
		t.start();
		try {
			System.out.println("====主线程等待输入：输入完之后主线程结束，观察用户线程运行情况！===");
			System.in.read();
		} catch (Exception e) {
			System.out.println("end main");
		}
	}
}
