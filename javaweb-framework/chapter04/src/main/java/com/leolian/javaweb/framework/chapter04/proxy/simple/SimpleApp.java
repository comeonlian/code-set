package com.leolian.javaweb.framework.chapter04.proxy.simple;

import com.leolian.javaweb.framework.chapter04.proxy.Hello;

public class SimpleApp {

    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Rose");
    }

}