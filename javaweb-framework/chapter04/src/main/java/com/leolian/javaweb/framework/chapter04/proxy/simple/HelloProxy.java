package com.leolian.javaweb.framework.chapter04.proxy.simple;

import com.leolian.javaweb.framework.chapter04.proxy.Hello;
import com.leolian.javaweb.framework.chapter04.proxy.HelloImpl;

/**
 * 代理类
 */
public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy() {
        hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

}