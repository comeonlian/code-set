package com.leolian.distributed.architecture.chapter01.thrift01;

import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;

/**
 * 
 */
public class AsynInvokerCallback implements AsyncMethodCallback<HelloService.AsyncClient.sayHello_call> {

    private CountDownLatch latch;

    public AsynInvokerCallback(CountDownLatch latch) {
        this.latch = latch;
    }


    /**
     * 异步调用完成,回调该方法
     *
     * @param response
     */
    public void onComplete(HelloService.AsyncClient.sayHello_call response) {
        try {
            System.out.println("AsynInvokerCallback response: " + response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    /**
     * 异步调用出错回调方法
     *
     * @param exception
     */
    public void onError(Exception exception) {
        latch.countDown();
    }
}
