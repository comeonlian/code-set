package com.leolian.code.fragment.blog.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: 
 * @author lianliang
 * @date 2019/2/16 10:28
 */
public class MyBlockingQueueNewTest {
    
    static volatile boolean stop = true;
    
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueueOld myBlockingQueueOld = new MyBlockingQueueOld(10);
        
        // 线程1 消费消息
        Thread thread1 = new Thread(() -> {
            while (stop) {
                System.out.println(myBlockingQueueOld.take());
            }
        });
        thread1.setDaemon(true);
        thread1.start();
        
        // 线程2 生产消息
        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 1500; i++) {
                myBlockingQueueOld.put(i);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setDaemon(true);
        thread2.start();

        // 主线程 生产消息
        for (int i = 0; i < 500; i++) {
            myBlockingQueueOld.put(i);
            TimeUnit.MILLISECONDS.sleep(20);
        }

        TimeUnit.SECONDS.sleep(60);
        
        stop = false;
    }
    
}
