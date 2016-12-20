package com.noteshare.thread.join;
/**
 * ProjectName : J2SE
 * ClassName   : JoinTest
 * Description : 方法**join()**则是主线程等待子线程完成
 * @author     : NoteShare
 * @version    : v1.0
 * Create Date : 2016年12月20日 下午12:42:48
 */
public class JoinTest {
	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new ThreadTesterA());
		Thread t2 = new Thread(new ThreadTesterB());
		t1.start();
		t1.join(); //等t1执行完再往下执行
		t2.start();
		t2.join(); //在虚拟机执行中，这句可能被忽略 
	}
}

class ThreadTesterA implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("===ThreadTesterA====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ThreadTesterB implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("===ThreadTesterB====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
