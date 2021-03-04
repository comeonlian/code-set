package com.leolian.code.fragment.blog.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 *  多线程同步通信，使用Lock实现
 * @author lianliang
 * @date 2019/2/15 23:01
 */
public class MultiThreadCommunicateNew {

    public static void main(String[] args) {
        BusinessCommunicateNew businessCommunicateNew = new BusinessCommunicateNew();
        
        // 
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                businessCommunicateNew.printThread1();
            }
        }).start();
        
        // 
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                businessCommunicateNew.printThread2();
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            businessCommunicateNew.printThread3();
        }
    }

    static class BusinessCommunicateNew {
        private volatile int flag = 1;

        private ReentrantLock lock = new ReentrantLock();

        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();

        public void printThread1() {
            lock.lock();
            try {
                if (flag != 1) {
                    condition1.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("print thread111111111");
                }
                flag = 2;
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printThread2() {
            lock.lock();
            try {
                if (flag != 2) {
                    condition2.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("print thread222222222");
                }
                flag = 3;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printThread3() {
            lock.lock();
            try {
                if (flag != 3) {
                    condition3.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("print thread333333333");
                }
                flag = 1;
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
