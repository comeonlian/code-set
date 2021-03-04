/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter07;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.leolian.code.fragment.book.concurrence.chapter05.Preloader;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月18日 下午5:08:32
 */
public class TimedRun {
	private ScheduledExecutorService exec;
	
	/**
	 * @param exec
	 */
	public TimedRun(ScheduledExecutorService exec) {
		this.exec = exec;
	}
	
	public void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		class RethrowableTask implements Runnable {
			private volatile Throwable t;
			
			public void run() {
				try {
					r.run();
				} catch(Throwable t) {
					this.t = t;
				}
			}
			
			void rethrow() {
				if(t!=null)
					throw Preloader.launderThrowable(t);
			}
		}
		
		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		exec.schedule(new Runnable() {
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));
		task.rethrow();
	}
	
}
