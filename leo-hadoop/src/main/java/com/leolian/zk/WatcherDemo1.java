package com.leolian.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * zk-watcher案例
 * @author Lian
 */
public class WatcherDemo1 implements Watcher{
	private String connString = "localhost:2181";
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("watcher="+this.getClass().getName());
		System.out.println("path="+event.getPath());
		System.out.println("eventType="+event.getType().name());
	}
	
	public String getConnString() {
		return connString;
	}
	
	
	public static void main(String[] args) {
		WatcherDemo1 wd = new WatcherDemo1();
		try{
			ZooKeeper zk = new ZooKeeper(wd.getConnString(), 1000, wd);
			zk.getChildren("/node_root", true);
			Thread.sleep(300000);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
