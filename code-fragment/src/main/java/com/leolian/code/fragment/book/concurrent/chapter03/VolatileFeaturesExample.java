/**
 * 
 */
package com.leolian.code.fragment.book.concurrent.chapter03;

import java.util.concurrent.CountDownLatch;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月3日 下午8:07:45
 */
public class VolatileFeaturesExample {
	private volatile long val = 0l;
	
	public void set(long param) {
		this.val = param;
	}
	
	public long get() {
		return val;
	}
	
	public void getAndIncrement() {
		val++;
	}
	
	public static void main(String[] args) throws Exception {
		final VolatileFeaturesExample example = new VolatileFeaturesExample();
		/*ExecutorService pool = Executors.newFixedThreadPool(3);
		for(int i=0; i<3; i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for(int j=0; j<100; j++) {
						example.set(example.get()+1);
						//example.getAndIncrement();
					}
				}
			});
		}
		System.out.println("result: "+example.get());
		pool.shutdown();*/
		CountDownLatch latch = new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				long l = example.get();
				System.out.println(Thread.currentThread().getName()+" val: "+l);
				for(int j=0; j<100; j++) {
					example.set(l+j);
				}
				latch.countDown();
			}
		}, "thread1").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				long l = example.get();
				System.out.println(Thread.currentThread().getName()+" val: "+l);
				for(int j=0; j<100; j++) {
					example.set(l+j);
				}
				latch.countDown();
			}
		}, "thread2").start();
		
		latch.await();
		
		System.out.println("result: "+example.get());
	}
	
}
