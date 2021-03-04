package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 闭锁
 * Description:
 * @author lianliang
 * @date 2018年1月14日 下午10:25:19
 */
public class TestHarness {

	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {

					}
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}

}