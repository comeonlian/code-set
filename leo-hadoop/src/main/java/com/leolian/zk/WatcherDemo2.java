package com.leolian.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherDemo2 implements Watcher{
	
	private ZooKeeper zk = null;
	
	public WatcherDemo2(ZooKeeper zk) {
		this.zk = zk;
	}
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("watcher="+this.getClass().getName());
		System.out.println("path="+event.getPath());
		System.out.println("eventType="+event.getType().name());
		try{
			WatcherDemo2 wd = new WatcherDemo2(zk);
			zk.getData(event.getPath(), wd, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
