package com.noteshare.thread.threadpool.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参考文章：https://www.cnblogs.com/dolphin0520/p/3932921.html
 * @Title:ThreadPollTest.java
 * @Description:线程池测试类
 * @author:NoteShare
 * @date:2019年4月23日 上午11:59:47
 */
public class ThreadPollTest {
	public static void main(String[] args) {
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
		for (int i = 0; i < 20; i++) {
			MyTask myTask = new MyTask(i);
			tpe.execute(myTask);
			System.out.println("线程池中正在运行的任务数：" + tpe.getActiveCount() + "；线程池中线程数：" + tpe.getPoolSize() + "；已经执行完成的任务数："
					+ tpe.getCompletedTaskCount() + "；阻塞队列中的任务数：" + tpe.getQueue().size() + "；线程池中最大任务数："
					+ tpe.getLargestPoolSize());

		}
		tpe.shutdown();
	}
}
