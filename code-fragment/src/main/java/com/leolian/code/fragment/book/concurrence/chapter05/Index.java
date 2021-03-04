package com.leolian.code.fragment.book.concurrence.chapter05;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Index {
	private static final int BOUND = 2000;
	private static final int N_CONSUMERS = 20;
	
	
	public static void startIndexing(File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
		};
		
		for(File root : roots) {
			new Thread(new FileCrawler(queue, filter, root)).start();
		}
		
		for(int i=0; i<N_CONSUMERS; i++) {
			new Thread(new Indexer(queue)).start();
		}
	}

}

/**
 * Description: 生产者
 * @author lianliang
 * @date 2018年1月14日 上午11:58:15
 */
class FileCrawler implements Runnable {
	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;
	
	public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	}

	public void run() {
		try {
			crawl(root);
		}catch(Exception s) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if(entries!=null){
			for(File entry : entries) {
				if(entry.isDirectory()) {
					crawl(entry);
				} else if(!alreadyIndexed(entry)) {
					fileQueue.put(entry);
				}
			}
		}
	}

	/**
	 * @param entry
	 * @return
	 */
	private boolean alreadyIndexed(File entry) {
		return false;
	}
	
}

/**
 * Description: 消费者
 * @author lianliang
 * @date 2018年1月14日 上午11:58:29
 */
class Indexer implements Runnable {
	private final BlockingQueue<File> queue;
	
	public Indexer(BlockingQueue<File> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while(true) {
				indexFile(queue.take());
			}
		}catch(Exception e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @param take
	 */
	private void indexFile(File take) {
		
	}
	
}