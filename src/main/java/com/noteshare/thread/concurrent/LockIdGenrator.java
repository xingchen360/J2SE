package com.noteshare.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockIdGenrator {
	// new ReentrantLock(true)是重载，使用更加公平的加锁机制，在锁被释放后，会优先给等待时间最长的线程，避免一些线程长期无法获得锁
	private ReentrantLock lock = new ReentrantLock();
	
	private static int value = 0;
	public int getNext() {
		lock.lock(); //进来就加锁，没有锁会等待
		try {
			value++;// 实际操作
			System.out.println(value);
			return value;
		} finally {
			lock.unlock();// 释放锁
		}
	}
	
	public static void main(String[] args) {
		LockIdGenrator obj = new LockIdGenrator();
		for(int i=0;i < 20;i++){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int num = obj.getNext();
					System.out.println("num" + num);
				}
			});
			thread.start();
		}
	}
}
