/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter12;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年3月8日 上午10:42:39
 */
public class BoundedBufferTest{
	
	private static final long LOCKUP_DETECT_TIMEOUT = 3000;

	@Test
	public void testIsEmptyWhenConstructed() {
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	@Test
	public void testIsFullAfterPuts() throws Exception {
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		for (int i = 0; i < 10; i++) {
			bb.put(i);
		}
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	@Test
	public void testTakeBlockWhenEmpty() {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();
					// fail();
					throw new RuntimeException();
				} catch( InterruptedException success) {
					
				}
			}
		};
		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive());
		} catch (Exception e) {
			// fail();
			e.printStackTrace();
		}
	}
	
	
}
