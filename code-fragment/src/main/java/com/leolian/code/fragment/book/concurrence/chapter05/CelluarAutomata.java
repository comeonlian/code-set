package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description: 细胞自动衍生系统的计算
 * @author lianliang
 * @date 2018年1月15日 上午11:02:53
 */
public class CelluarAutomata {
	private final Board mainBoard;
	private final CyclicBarrier barrier;
	private final Worker[] workers;

	public CelluarAutomata(Board board) {
		this.mainBoard = board;
		int count = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(count, new Runnable() {
			public void run() {
				mainBoard.commitNewValues();
			}
		});
		this.workers = new Worker[count];
		for (int i = 0; i < count; i++) {
			workers[i] = new Worker(mainBoard.getSubBoard(count, i));
		}
	}

	private class Worker implements Runnable {
		private final Board board;

		public Worker(Board board) {
			this.board = board;
		}

		public void run() {
			while (!board.hasConverged()) {
				for (int x = 0; x < board.getMaxX(); x++) {
					for (int y = 0; y < board.getMaxY(); y++) {
						board.setNewValue(x, y, computeValue(x, y));
					}
				}
				try {
					barrier.await();
				} catch (InterruptedException e) {
					return;
				} catch (BrokenBarrierException e) {
					return;
				}
			}
		}

		/**
		 * @param x
		 * @param y
		 * @return
		 */
		private Object computeValue(int x, int y) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public void start() {
		for (int i = 0; i < workers.length; i++) {
			new Thread(workers[i]).start();
		}
		mainBoard.waitForConvergence();
	}
}
class Board {

	/**
	 * 
	 */
	public void waitForConvergence() {
		
	}

	/**
	 * @param count
	 * @param i
	 * @return
	 */
	public Board getSubBoard(int count, int i) {
		return null;
	}

	/**
	 * 
	 */
	public void commitNewValues() {
		
	}

	/**
	 * @param x
	 * @param y
	 * @param computeValue
	 */
	public void setNewValue(int x, int y, Object computeValue) {
		
	}

	/**
	 * @return
	 */
	public boolean hasConverged() {
		return false;
	}

	/**
	 * @return
	 */
	public int getMaxX() {
		return 0;
	}

	/**
	 * @return
	 */
	public int getMaxY() {
		return 0;
	}
	
}
