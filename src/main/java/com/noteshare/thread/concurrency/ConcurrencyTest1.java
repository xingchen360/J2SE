package com.noteshare.thread.concurrency;
/**
 * @ClassName			: ConcurrencyTest1 
 * @Description			: 测试结果银行的钱出现了负数这明显是有问题的
 * @author 				： NoteShare 
 * @date 				： 2016年12月18日 下午10:02:53
 */
public class ConcurrencyTest1 {
	public static void main(String[] args) {
		Bank1 bank1 = new Bank1();
		for (int i = 0; i < 10; i++) {
			PersonThread1 person = new PersonThread1(bank1);
			person.setName("线程" + i);
			person.start();
		}
	}
}
