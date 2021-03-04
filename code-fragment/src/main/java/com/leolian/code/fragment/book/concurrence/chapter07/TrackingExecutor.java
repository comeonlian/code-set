package com.leolian.code.fragment.book.concurrence.chapter07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 获取正在执行时取消的任务
 * Description: 
 * @author lianliang
 * @date 2018年1月30日 上午10:51:14
 */
public class TrackingExecutor extends AbstractExecutorService {
	private final ExecutorService exec;
	private final Set<Runnable> taskCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());
	
	/**
	 * @param exec
	 */
	public TrackingExecutor(ExecutorService exec) {
		this.exec = exec;
	}

	public List<Runnable> getCancelledTasks() {
		if (!exec.isTerminated()) {
			throw new IllegalArgumentException("");
		}
		return new ArrayList<Runnable>(taskCancelledAtShutdown);
	}

	public void execute(final Runnable runnable) {
		exec.execute(new Runnable() {
			public void run() {
				try {
					runnable.run();
				} finally {
					if (isShutdown() && Thread.currentThread().isInterrupted()) {
						taskCancelledAtShutdown.add(runnable);
					}
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ExecutorService#shutdown()
	 */
	@Override
	public void shutdown() {
		exec.shutdown();
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ExecutorService#shutdownNow()
	 */
	@Override
	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ExecutorService#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return exec.isShutdown();
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ExecutorService#isTerminated()
	 */
	@Override
	public boolean isTerminated() {
		return exec.isTerminated();
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return exec.awaitTermination(timeout, unit);
	}

}