package com.leolian.distributed.architecture.chapter01.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-12-13T15:55:30.827+08:00
 * Generated source version: 3.1.7
 * 
 */
@WebService(targetNamespace = "http://cxf.chapter01.architecture.distributed.leolian.com/", name = "HelloService")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloService {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://cxf.chapter01.architecture.distributed.leolian.com/", className = "com.leolian.distributed.architecture.chapter01.cxf.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://cxf.chapter01.architecture.distributed.leolian.com/", className = "com.leolian.distributed.architecture.chapter01.cxf.SayHelloResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
