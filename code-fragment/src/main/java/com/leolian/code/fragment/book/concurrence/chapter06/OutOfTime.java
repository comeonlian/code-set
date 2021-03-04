package com.leolian.code.fragment.book.concurrence.chapter06;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class OutOfTime {
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(1);
		System.out.println("The first job");
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("The second job");
	}

	static class ThrowTask extends TimerTask {
		public void run() {
			throw new RuntimeException();
		}
	}
}