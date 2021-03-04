package com.leolian.code.fragment.book.concurrence.chapter08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPoolTest {
	
	public static void main(String[] args) {
		int corePoolSize = 3;
		int maximumPoolSize = 5;
		long keepAliveTime = 10;
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(20);
		
		TimingThreadPool pool = new TimingThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
		for(int i=0; i<6; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("execute task.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		pool.shutdown();
	}
	
}
/**
 * 
 * Description: 自定义线程池，添加了统计信息
 * @author lianliang
 * @date 2018年2月9日 上午10:42:51
 */
class TimingThreadPool extends ThreadPoolExecutor {

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger log = Logger.getLogger("TimingThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();
	
	/**
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			System.out.println(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
		} finally {
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try {
			System.out.println(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
		} finally {
			super.terminated();
		}
	}
	
}