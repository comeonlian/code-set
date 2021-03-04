package com.leolian.distributed.architecture.chapter01.thrift02;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

/**
 * @description: 
 * @author lianliang
 * @date 2019/1/28 15:49
 */
@ThriftService("HelloService")
public interface HelloService {
    @ThriftMethod
    String sayHello(@ThriftField(name = "user") User user);
}
