package com.leolian.code.fragment.blog.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 *  简易实现阻塞队列，使用Lock实现
 * @author lianliang
 * @date 2019/2/15 22:58
 */
public class MyBlockingQueueNew {
    private Object[] data;

    private int length;
    private int putIndex;
    private int takeIndex;
    private int count;

    private ReentrantLock lock = new ReentrantLock();
    private Condition consumerCondition = lock.newCondition();
    private Condition providerCondition = lock.newCondition();

    public MyBlockingQueueNew(int length) {
        this.length = length;
        this.data = new Object[length];
        this.putIndex = 0;
        this.takeIndex = 0;
        this.count = 0;
    }

    public void put(Object object) {
        lock.lock();
        try {
            if (count == length) {
                providerCondition.await();
            }
            data[putIndex] = object;
            putIndex++;
            if (putIndex >= length) {
                putIndex = 0;
            }
            count++;
            consumerCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            if (count == 0) {
                consumerCondition.await();
            }
            Object object = data[takeIndex];
            data[takeIndex] = null;
            takeIndex++;
            if (takeIndex >= length) {
                takeIndex = 0;
            }
            count--;
            providerCondition.signal();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
