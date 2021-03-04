package com.leolian.distributed.architecture.chapter01.thrift02;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author lianliang
 * @date 2019/1/28 15:53
 */
public class ClientMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost", 8899));

        User user = new User();
        user.setName("Jack");
        user.setEmail("jack@126.com");

        HelloService helloService = clientManager.createClient(connector, HelloService.class).get();
        String hi = helloService.sayHello(user);
        System.out.println(hi);
    }

}
