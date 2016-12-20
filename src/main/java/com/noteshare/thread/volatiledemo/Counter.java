package com.noteshare.thread.volatiledemo;

public class Counter {
	public volatile static int count = 0;
	//synchronized
	public static void inc() {
		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			
		}
		//count = count + 1;
		count++;
	}
	public static void main(String[] args) {
		// 同时启动1000个线程，去进行i++计算，看看实际结果
		Bank bank = new Bank();
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter.inc();
					bank.inc();
					Bank.print();
					bank.print_sync();
					// 这里每次运行的值都有可能不同,可能不为1000
					System.out.println("运行结果volatile:Counter.count=" + Counter.count);
					System.out.println("Bank-total:" + bank.getTotal());
					System.out.println("Bank-count-static-nostaticmethod:" + Bank.count);
					System.out.println("Bank-num-static-staticmethod:" + Bank.num);
					System.out.println("Bank-num-sync-block:" + bank.getNum_sync());
				}
			}).start();
		}
	}
}


class Bank{
	private int total = 0;
	public static int count = 0;
	public static int num = 0;
	private int num_sync = 0;
	//synchronized
	public synchronized void inc() {
		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			
		}
		total++;
		count++;
	}
	//synchronized
	public synchronized static void print(){
		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			
		}
		num++;
	}
	
	public void print_sync(){
		synchronized (this) {
			// 这里延迟1毫秒，使得结果明显
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				
			}
			num_sync++;
		}
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNum_sync() {
		return num_sync;
	}
	public void setNum_sync(int num_sync) {
		this.num_sync = num_sync;
	}
}
