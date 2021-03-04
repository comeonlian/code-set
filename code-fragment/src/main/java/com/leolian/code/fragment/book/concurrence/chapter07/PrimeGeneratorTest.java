package com.leolian.code.fragment.book.concurrence.chapter07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

public class PrimeGeneratorTest {
	
	public static void main(String[] args) throws Exception {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}
		System.out.println(generator.get().size());
	}
	
}

/**
 * Description: 生成素数
 * @author lianliang
 * @date 2018年1月17日 下午4:37:49
 */
@ThreadSafe
class PrimeGenerator implements Runnable {
	@GuardedBy("this")
	private final List<BigInteger> primes = new ArrayList<>();
	private volatile boolean cancelled;

	public void run() {
		BigInteger p = BigInteger.ONE;
		// 自定义的任务取消机制
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
	
}