/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月17日 下午8:30:14
 */
public class ThreadInterrupt {
	
	public static void main(String[] args) throws Exception {
		BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(1000);
		PrimeProducer producer = new PrimeProducer(queue);
		producer.start();
		try {
			TimeUnit.SECONDS.sleep(2); //休眠2s后中断
			System.out.println("sleep 2s over.");
		} finally {
			producer.cancel();
		}
	}
	
}
/**
 * Description: 生产者
 * @author lianliang
 * @date 2018年1月17日 下午8:30:26
 */
class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	
	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while(!Thread.currentThread().isInterrupted()) {
				p = p.nextProbablePrime();
				// 库方法put能够响应中断
				// 利用库方法的中断来实现任务取消
				queue.put(p);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel() {
		interrupt();
	}
}