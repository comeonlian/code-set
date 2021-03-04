package com.leolian.code.fragment.blog.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 *  多线程同步通信，使用wait和notify实现
 * @author lianliang
 * @date 2019/2/15 23:01
 */
public class MultiThreadCommunicateOld {

    public static void main(String[] args) {
        BusinessCommunicateOld businessCommunicateOld = new BusinessCommunicateOld();
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                businessCommunicateOld.printThread1();
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            businessCommunicateOld.printThread2();
        }
    }

    static class BusinessCommunicateOld {
        private volatile boolean flag = true;

        public synchronized void printThread1() {
            if (!flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("print thread1: " + i);
            }
            flag = false;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }

        public synchronized void printThread2() {
            if (flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 20; i++) {
                System.out.println("print thread2: " + i);
            }
            flag = true;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
        
    }

}
