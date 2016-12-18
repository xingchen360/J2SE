package com.noteshare.thread.concurrency;

public class PersonThread1 extends Thread{
	private  Bank1 bank;
	
	public PersonThread1(Bank1 bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		//向银行借钱
		while(bank.getTotalMoney() >0){
			try {
				bank.loan(this.getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
