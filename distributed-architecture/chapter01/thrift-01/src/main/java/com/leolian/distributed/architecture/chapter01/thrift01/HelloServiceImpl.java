package com.leolian.distributed.architecture.chapter01.thrift01;

import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {

    @Override
    public String sayHello(User user) throws TException {
        return "Hello, " + user.getName() + " !";
    }
    
}