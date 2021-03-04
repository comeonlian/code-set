package com.leolian.distributed.architecture.chapter01.rpc.framework.service;

public class HelloServiceImpl implements HelloService {

    public String sayHello(String content) {
        return "Hello he : " + content;
    }

}