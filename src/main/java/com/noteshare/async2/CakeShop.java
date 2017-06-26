package com.noteshare.async2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CakeShop {

	public FutureTask<Cake> request(final int count, final char c) {

		System.out.println("request(" + count + ", " + c + ") BEGIN");
		
		Callable<Cake> callable = new Callable<Cake>() {
			public Cake call() {
				CakeBaker cakeBaker = new CakeBaker(count, c);
				return cakeBaker.getCake();
			}
		};
		
		FutureTask<Cake> future = new FutureTask<Cake>(callable);
		
		System.out.println("request(" + count + ", " + c + ") END");
		
		return future;

	}
}
