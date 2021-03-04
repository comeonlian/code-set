package com.leolian.code.fragment.book.distributed.chapter01.zookeeper;

import java.net.Inet4Address;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;

public class ServiceB {
	
	public static final String SERVICENAME = "/server-b";
	
	public static void main(String[] args) throws Exception{
		CuratorFramework client = CuratorFrameworkFactory.newClient(Constant.HOST, new RetryNTimes(10, 5000));
		client.start();
		System.err.println("provider start successfully.");
		
		// 校验根路径是否存在
		Stat stat = client.checkExists().forPath(Constant.PATH);
		if(null!=stat) { 
			// 根节点存在
			String ip = Inet4Address.getLocalHost().getHostAddress();
			String provider = Constant.PATH + SERVICENAME + Constant.PROVIDER + "/" + ip;
			client.create().creatingParentsIfNeeded().forPath(provider, ip.getBytes());
			System.out.println("create path: "+provider);
		} else {
			// 根节点不存在
			throw new RuntimeException("根节点不存在："+Constant.PATH);
		}
	}
	
}
