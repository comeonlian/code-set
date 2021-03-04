package com.leolian.rpc;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

/**
 * RPC客户端
 * @author Lian
 */
public class RpcClient {

	public static void main(String[] args) throws Exception {
		RpcInterface proxy = RPC.getProxy(RpcInterface.class, 10010, 
				new InetSocketAddress("192.168.1.66", 9527), new Configuration());
		String result = proxy.sayHi("Tom");
		System.out.println(result);
	}

}
