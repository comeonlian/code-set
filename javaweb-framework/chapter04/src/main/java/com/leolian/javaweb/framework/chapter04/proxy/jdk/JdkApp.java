package com.leolian.javaweb.framework.chapter04.proxy.jdk;

import com.leolian.javaweb.framework.chapter04.proxy.Hello;
import com.leolian.javaweb.framework.chapter04.proxy.HelloImpl;

import java.lang.reflect.Proxy;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/27 15:27
 */
public class JdkApp {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), 
                dynamicProxy);
        helloProxy.say("Jack");
    }
}
