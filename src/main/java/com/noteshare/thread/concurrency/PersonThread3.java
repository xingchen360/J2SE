package com.noteshare.thread.concurrency;

public class PersonThread3 extends Thread{
	private  Bank3 bank;
	
	public PersonThread3(Bank3 bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		//向银行借钱
		while(bank.getTotalMoney() >0){
			try {
				synchronized (bank) {
					bank.loan(this.getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
