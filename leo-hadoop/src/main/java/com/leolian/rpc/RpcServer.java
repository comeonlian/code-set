package com.leolian.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

/**
 * RPC服务端
 * @author Lian
 *
 */
public class RpcServer implements RpcInterface{
	
	@Override
	public String sayHi(String name) {
		System.err.println("Execute rpc ...");
		return "Hello , "+name;
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new RPC.Builder(new Configuration())
		.setInstance(new RpcServer())
		.setBindAddress("192.168.1.66")
		.setPort(9527)
		.setProtocol(RpcInterface.class)
		.build();
		
		server.start();
	}
	

}
