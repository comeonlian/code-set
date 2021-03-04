package com.leolian.code.fragment.book.concurrence.chapter07;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.GuardedBy;

public abstract class WebCrawler {
	private static final long TIMEOUT = 5000;
	
	private volatile TrackingExecutor exec;
	@GuardedBy("this")
	private final Set<URL> urlsToCrawl = new HashSet<URL>();

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for (URL url : urlsToCrawl) {
			submitCrawlTask(url);
		}
		urlsToCrawl.clear();
	}

	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	protected abstract List<URL> processPage(URL url);

	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable task : uncrawled) {
			urlsToCrawl.add(((CrawlTask)task).getPage());
		}
	}

	private void submitCrawlTask(URL url) {
		exec.execute(new CrawlTask(url));
	}

	private class CrawlTask implements Runnable {
		private final URL url;
		
		/**
		 * @param url
		 */
		public CrawlTask(URL url) {
			this.url = url;
		}

		public void run() {
			for (URL link : processPage(url)) {
				if (Thread.currentThread().isInterrupted()) {
					return;
				}
				submitCrawlTask(link);
			}
		}

		public URL getPage() {
			return url;
		}
	}

}