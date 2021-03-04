/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.leolian.code.fragment.book.concurrence.chapter05.Preloader;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月19日 上午10:21:04
 */
public class TimedRun2 {
	
	private static ExecutorService taskExec = Executors.newFixedThreadPool(2);
	
	public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		Future<?> task = taskExec.submit(r);
		try {
			task.get(timeout, unit);
		} catch(TimeoutException e) {
			// 任务将被取消
		} catch(ExecutionException e) {
			// 在任务中抛出了异常，重新抛出该异常
			throw Preloader.launderThrowable(e.getCause());
		} finally {
			// 如果任务已经结束，那么该操作不会有任何影响
			// 如果任务正在运行，那么将被中断
			task.cancel(true);
		}
	}
	
}
