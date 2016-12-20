package com.noteshare.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * ProjectName : J2SE
 * ClassName   : AtomicIdGeneratorTest
 * Description : 【ps暂时没弄明白，有时间再研究下，先记录下。】目前CPU本身实现 将这三步 合起来 形成一个原子操作，无需线程锁机制干预，常见的指令是“比较和替换”（compare and swap,CAS），这个指令会先比较某个内存地址的当前值是不是指定的旧指，如果是，就用新值替换，否则什么也不做，指令返回的结果是内存地址的当前值。通过CAS指令可以实现不依赖锁机制的非阻塞算法。一般做法是把CAS指令的调用放在一个无限循环中，不断尝试，知道CAS指令成功完成修改。
 * java.util.concurrent.atomic包中提供了CAS指令。（不是所有CPU都支持CAS，在某些平台，java.util.concurrent.atomic的实现仍然是锁机制）
 * @author     : NoteShare
 * @version    : v1.0
 * Create Date : 2016年12月20日 下午2:48:20
 */
public class AtomicIdGeneratorTest {
	private final AtomicInteger counter = new AtomicInteger(1);

	public int getNext() {
		return counter.getAndIncrement();
	}
	
	//getAndIncrement方法的内部实现方式，这也是CAS方法的一般模式，CAS方法不一定成功，所以包装在一个无限循环中，直到成功
	public final int getAndIncrement() {
		for (;;) {
			int current = counter.get();
			int next = current + 1;
			if (counter.compareAndSet(current, next))
				return current;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new AtomicIdGeneratorTest().getNext());
	}
}
