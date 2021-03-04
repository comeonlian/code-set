package com.leolian.code.fragment.book.distributed.chapter01.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

public class Consumer {
	
	private static List<String> ips = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.newClient(Constant.HOST, new RetryNTimes(10, 5000));
		client.start();
		System.err.println("consumer start successfully.");
		
		String service = Constant.PATH + ServiceA.SERVICENAME + Constant.PROVIDER;
		List<String> addressList = client.getChildren().forPath(service);
		
		ips.addAll(addressList);
		
		// 添加watcher
		PathChildrenCache watcher = new PathChildrenCache(client, service, true);
		watcher.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				Type type = event.getType();
				// 有新增或者子节点删除
				if(Type.CHILD_ADDED==type || Type.CHILD_REMOVED==type) {
					List<String> addressList = client.getChildren().forPath(service);
					ips.clear();
					ips.addAll(addressList);
				}
			}
		});
		watcher.start(StartMode.BUILD_INITIAL_CACHE);
		
		int index = 0;
		while(index<100) {
			System.out.println(ips);
			Thread.sleep(5000);
			index++;
		}
		
		watcher.close();
	}
	
}
