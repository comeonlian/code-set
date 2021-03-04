package com.leolian.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherRegister {
	private ZooKeeper zk = null;
	
	public WatcherRegister(String connString, Watcher watcher){
		try{
			zk = new ZooKeeper(connString, 10000, watcher);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testWatcherdiabled(String path) throws KeeperException, InterruptedException{
		WatcherDemo2 wd = new WatcherDemo2(zk);
		zk.getData(path, wd, null);
	}
	
	public static void main(String[] args) {
		WatcherDemo1 wd = new WatcherDemo1();
		WatcherRegister wr = new WatcherRegister("localhost:2181", wd);
		try{
			wr.testWatcherdiabled("/node_root");
			Thread.sleep(300000);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
