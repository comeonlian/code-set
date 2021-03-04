package com.leolian.code.fragment.book.netty.chapter20;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 测试指令重排序
 * @author lianliang
 * @date 2017年8月17日 上午10:54:04
 */
public class OrderResort {
	
	private static boolean stop ;
	
	public static void main(String[] args) throws InterruptedException {
		Thread workThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				while(!stop) {
					i++;
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("i 的值: "+i);
				}
			}
			
		});
		
		workThread.start();
		TimeUnit.SECONDS.sleep(3);
		stop = true;
	}

}
