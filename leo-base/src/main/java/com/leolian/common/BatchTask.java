package com.leolian.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 批量任务执行基础类
 * @author Lian
 * @param <T>
 */
public abstract class BatchTask<T> {
	private BlockingQueue<T> blockingQueue = new ArrayBlockingQueue<T>(10000);
	private Executor executors = null;
	private int threadNum = 1;
	
	public void start(){
		start(1);
	}
	
	public void start(int threadNum){
		if(this.threadNum<=0)
			this.threadNum = 1;
		executors = Executors.newFixedThreadPool(threadNum);
		for(int i=0; i<threadNum; i++){
			executors.execute(new Runnable() {
				public void run() {
					service();
				}
			});
		}
	}
	
	public abstract void service();
	
	
	public void put(T obj) throws InterruptedException{
		this.blockingQueue.put(obj);
	}
	
	public T poll() throws InterruptedException{
		return this.blockingQueue.take();
	}
	
	
	public List<T> poll(int size){
		List<T> list = new ArrayList<T>();
		this.blockingQueue.drainTo(list, size);
		return list;
	}
	
	
	public int size(){
		return this.blockingQueue.size();
	}
	
}
