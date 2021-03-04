package com.leolian.distributed.architecture.chapter01.cxf.client;

import javax.jws.WebService;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:30
 */
@WebService
public interface HelloService {

    String sayHello(String content) ;

}
