package com.leolian.javaweb.framework.chapter04.proxy.cglib;

import com.leolian.javaweb.framework.chapter04.proxy.Hello;
import com.leolian.javaweb.framework.chapter04.proxy.HelloImpl;

public class CGLibApp {

    public static void main(String[] args) {
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");
    }

}