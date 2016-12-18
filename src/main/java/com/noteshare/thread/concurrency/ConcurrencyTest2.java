package com.noteshare.thread.concurrency;
/**
 * @ClassName			: ConcurrencyTest2 
 * @Description			: 由于对Bank2的借出方法加了synchronized 标志，此方法为线程安全的，同一时刻只能有一个线程能够进入此方法，所以该银行的总金额不会出现
 * 						  小于0的情况，且测试方法输出时间也是比较长的。
 * @author 				： NoteShare 
 * @date 				： 2016年12月18日 下午10:08:10
 */
public class ConcurrencyTest2 {
	public static void main(String[] args) {
		Bank2 bank2 = new Bank2();
		for (int i = 0; i < 10; i++) {
			PersonThread2 person = new PersonThread2(bank2);
			person.setName("线程" + i);
			person.start();
		}
	}
}
