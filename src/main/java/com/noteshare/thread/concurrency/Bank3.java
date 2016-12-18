package com.noteshare.thread.concurrency;

public class Bank3 {
	/**
	 * 银行拥有的钱总数
	 */
	private int totalMoney = 100000;
	/**
	 * @throws InterruptedException 
	 * @Title			: loan 
	 * @Description		: 银行把钱借出去
	 * @author 			： NoteShare
	 * @date 			： 2016年12月18日 下午9:43:46 
	 * @throws
	 */
	public void loan(String name) throws InterruptedException{
		if(totalMoney > 0){
			Thread.sleep(200);
			totalMoney = totalMoney - 1000;
			System.out.println("银行被" + name + "借走10元钱；剩余的钱为：" + totalMoney);
		}
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
}
