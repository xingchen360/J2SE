package com.noteshare.thread.threadpool.demo1;

/**
 * @Title:MyTask.java
 * @Description:任务类
 * @author:NoteShare
 * @date:2019年4月23日 上午11:59:12
 */
public class MyTask implements Runnable {

	private int taskNum;

	public MyTask(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {
		System.out.println("正在执行的任务编号为：" + taskNum);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("任务编号为：" + taskNum + "的任务已经执行完毕");
	}
}
