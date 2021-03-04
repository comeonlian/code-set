package com.leolian.javaweb.framework.chapter06;

import javax.jws.WebService;

@WebService
public interface HelloService {

    String say(String name);

}