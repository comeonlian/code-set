package com.leolian.distributed.architecture.chapter01.thrift02;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * @description: 
 * @author lianliang
 * @date 2019/1/28 15:46
 */
@ThriftStruct
public final class User {
    private String name;
    private String email;
    
    @ThriftField(1)
    public String getName() {
        return name;
    }
    
    @ThriftField
    public void setName(String name) {
        this.name = name;
    }
    
    @ThriftField(2)
    public String getEmail() {
        return email;
    }
    
    @ThriftField
    public void setEmail(String email) {
        this.email = email;
    }
}
