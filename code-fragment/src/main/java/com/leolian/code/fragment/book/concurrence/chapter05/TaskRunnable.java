package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.concurrent.BlockingQueue;

public class TaskRunnable implements Runnable {
	BlockingQueue<Object> queue;

	public void run() {
		try {
			processTask(queue.take());
		} catch (InterruptedException e) {
			// 恢复线程的中断状态
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @param take
	 */
	private void processTask(Object take) {
		
	}
}