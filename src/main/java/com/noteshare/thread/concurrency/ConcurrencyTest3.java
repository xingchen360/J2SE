package com.noteshare.thread.concurrency;
/**
 * @ClassName			: ConcurrencyTest2 
 * @Description			: 由于PersonThread3的run方法中对共享对象bank3进行了同步操作，所以同一时刻只能有一个线程可以操作bank3对象，所以该银行的总金额不会出现
 * 						  小于0的情况，且测试方法输出时间也是比较长的。
 * @author 				： NoteShare 
 * @date 				： 2016年12月18日 下午10:08:10
 */
public class ConcurrencyTest3 {
	public static void main(String[] args) {
		Bank3 bank3 = new Bank3();
		for (int i = 0; i < 10; i++) {
			PersonThread3 person = new PersonThread3(bank3);
			person.setName("线程" + i);
			person.start();
		}
	}
}
