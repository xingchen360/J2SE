package com.noteshare.thread;
/**
 * @ClassName			: ThreadTest 
 * @Description			: 继承Thread的线程实现方式
 * @author 				： NoteShare 
 * @date 				： 2016年12月18日 下午9:15:06
 */
public class ThreadTest extends Thread{
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getName());
		}
	}

	public static void main(String[] args) {
		ThreadTest thread1 = new ThreadTest();
		thread1.setName("线程===");
		ThreadTest thread2 = new ThreadTest();
		thread2.setName("线程---");
		thread1.start();
		thread2.start();
	}
}
