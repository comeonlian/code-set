package com.leolian.distributed.architecture.chapter01.axis2.client;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * 动态调用，不需要预生成代码
 *
 */
public final class Axis2ClientSecond {

    private static final QName SERVICE_NAME = new QName("http://axis2.chapter01.architecture.distributed.leolian.com", "HelloService");

    public static void main(String args[]) throws Exception {
        URL wsdlURL = new URL("http://localhost:8802/services/HelloService?wsdl");
        
        // http://localhost:8802/services/HelloService?wsdl
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8802/services/HelloService");
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setManageSession(true);
        options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
        options.setTo(targetEPR);
        QName opQName = new QName("http://axis2.chapter01.architecture.distributed.leolian.com", "sayHello");
        Object[] paramArgs = new Object[]{"Rose"};

        //处理返回数据
        Class[] returnTypes = new Class[]{String.class};
        Object[] response = serviceClient.invokeBlocking(opQName, paramArgs, returnTypes);
        serviceClient.cleanupTransport();
        String result = (String) response[0];
        if (result == null) {
            System.out.println("HelloService didn't initialize!");
        } else {
            System.out.println(result);
        }
    }

}
