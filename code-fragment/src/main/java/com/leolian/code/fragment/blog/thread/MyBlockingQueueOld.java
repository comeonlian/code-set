package com.leolian.code.fragment.blog.thread;
/**
 * @description: 
 *  简易实现阻塞队列，使用wait和notify实现
 * @author lianliang
 * @date 2019/2/15 22:58
 */
public class MyBlockingQueueOld {
    
    private Object[] data;
    
    private int length;
    private int takeIndex;
    private int putIndex; 
    private int count;

    public MyBlockingQueueOld(int length) {
        this.data = new Object[length];
        this.length = length;
        this.putIndex = 0;
        this.takeIndex = 0;
        this.count = 0;
    }

    public synchronized void put(Object object) {
        if (count == length) { //数组已满
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data[putIndex] = object;
        putIndex++;
        if (putIndex >= length) {
            putIndex = 0;
        }
        count++;
        notify();
    }

    public synchronized  Object take() {
        if (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object object = data[takeIndex];
        data[takeIndex] = null;
        takeIndex++;
        if (takeIndex >= length) {
            takeIndex = 0;
        }
        count--;
        notify();
        return object;
    }
    
}
