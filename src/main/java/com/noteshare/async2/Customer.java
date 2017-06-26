package com.noteshare.async2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Customer {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		System.out.println("main BEGIN");
		
		CakeShop shop = new CakeShop();
		FutureTask<Cake> future1  = shop.request(5, 'A');
		FutureTask<Cake> future2  = shop.request(5, 'B');
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(future1);
		executor.execute(future2);
		
		System.out.println("main otherJob BEGIN");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println("main otherJob END");
		
		Cake cake1 = future1.get(10000, TimeUnit.MILLISECONDS);
		Cake cake2 = future2.get(10000, TimeUnit.MILLISECONDS);
		System.out.println("cake1 = " + cake1 + "--> cake2 = " + cake2);
		
		System.out.println("main END");
		executor.shutdown();// 任务执行完毕，关闭线程池

	}
}
