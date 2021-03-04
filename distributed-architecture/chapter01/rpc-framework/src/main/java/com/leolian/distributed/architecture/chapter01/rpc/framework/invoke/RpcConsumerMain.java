package com.leolian.distributed.architecture.chapter01.rpc.framework.invoke;

import com.leolian.distributed.architecture.chapter01.rpc.framework.ConsumerProxy;
import com.leolian.distributed.architecture.chapter01.rpc.framework.service.HelloService;

public class RpcConsumerMain {

    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.consume(HelloService.class, "127.0.0.1", 8083);
        for (int i = 0; i < 5; i++) {
            String hello = service.sayHello("Xiaoming_" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }

}