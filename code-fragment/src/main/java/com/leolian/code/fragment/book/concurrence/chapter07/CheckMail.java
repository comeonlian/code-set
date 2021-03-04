/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter07;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年1月30日 上午10:34:06
 */
public class CheckMail {

	public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final AtomicBoolean hasNewMail = new AtomicBoolean(false);
		try {
			for (final String host : hosts) {
				exec.execute(new Runnable() {
					public void run() {
						if (checkMail(host)) {
							hasNewMail.set(true);
						}
					}
				});
			}
		} finally {
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}
		return hasNewMail.get();
	}

	/**
	 * @param host
	 * @return
	 */
	protected boolean checkMail(String host) {
		return false;
	}

}
