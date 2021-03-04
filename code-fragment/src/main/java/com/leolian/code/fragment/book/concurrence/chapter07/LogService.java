package com.leolian.code.fragment.book.concurrence.chapter07;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

import javax.annotation.concurrent.GuardedBy;

public class LogService {
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private final PrintWriter writer;
	@GuardedBy("this")
	private boolean isShutdown;
	@GuardedBy("this")
	private int reservations;
	
	/**
	 * @param queue
	 * @param loggerThread
	 * @param writer
	 */
	public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
		this.queue = queue;
		this.loggerThread = loggerThread;
		this.writer = writer;
	}

	public void start() {
		loggerThread.start();
	}

	public void stop() {
		synchronized (this) {
			isShutdown = true;
		}
		loggerThread.interrupt();
	}

	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutdown)
				throw new IllegalArgumentException("logger is shutdown");
			++reservations;
		}
		queue.put(msg);
	}

	private class LoggerThread extends Thread {

		public void run() {
			try {
				while(true) {
					try {
						synchronized(LogService.this) {
							if(isShutdown && reservations==0)
								break;
						}
						String msg = queue.take();
						synchronized(LogService.this) {
							--reservations;
						}
						writer.println(msg);
					} catch(InterruptedException e) {
						// retry
					}
				}
			} finally {
				
			}
			
		}

	}

}