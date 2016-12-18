package com.noteshare.thread.concurrency;

public class PersonThread2 extends Thread{
	private  Bank2 bank;
	
	public PersonThread2(Bank2 bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		//向银行借钱
		while(bank.getTotalMoney() > 0){
			try {
				bank.loan(this.getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
