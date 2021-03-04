package com.leolian.code.fragment.book.concurrence.chapter07;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class LogServicePool {
	private final ExecutorService exec = Executors.newSingleThreadExecutor();
	
	private static final int TIMEOUT = 10;
	
	private Writer writer;
	
	public void start() {

	}

	public void stop() throws InterruptedException {
		try {
			exec.shutdown();
			exec.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void log(String msg) {
		try {
			exec.execute(new WriteTask(msg));
		} catch (RejectedExecutionException e) {
			//
		}
	}

}

class WriteTask implements Runnable {
	
	private String msg;
	
	/**
	 * @param msg
	 */
	public WriteTask(String msg) {
		super();
		this.msg = msg;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
	}
	
}