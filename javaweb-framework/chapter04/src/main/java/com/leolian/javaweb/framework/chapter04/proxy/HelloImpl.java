package com.leolian.javaweb.framework.chapter04.proxy;

import com.leolian.javaweb.framework.chapter04.proxy.Hello;

/**
 * 实现类
 */
public class HelloImpl implements Hello {
    
    @Override
    public void say(String name) {
        System.out.println("Hello! " + name);
    }
    
}