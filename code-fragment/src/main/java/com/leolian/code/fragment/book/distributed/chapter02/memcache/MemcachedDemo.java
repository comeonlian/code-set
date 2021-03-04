package com.leolian.code.fragment.book.distributed.chapter02.memcache;

import java.util.Map;

import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedDemo {
	
	public static void init() {
		String[] servers = {
			"127.0.0.1:11211"
		};
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setFailover(true); //容错
		pool.setInitConn(10); //设置初始连接数
		pool.setMinConn(5); //设置最小连接数
		pool.setMaxConn(25); //设置最大连接数
		pool.setMaintSleep(30); //设置连接池维护线程的睡眠时间
		pool.setNagle(false); //设置是否使用Nagle算法
		pool.setSocketTO(3000); //设置socket的读取等待超时时间
		pool.setAliveCheck(true); //设置连接心跳检测开关
		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH); //设置hash算法
		pool.initialize();
	}
	
	public static void main(String[] args) {
		init();
		
		MemCachedClient client = new MemCachedClient();
		client.set("key01", "1");
		client.set("key02", "2");
		client.set("key03", "val3");
		
		String[] keys = {"key02", "key03"};
		Map<String, Object> multi = client.getMulti(keys);
		System.out.println(multi);
		
		String key = "key01";
		MemcachedItem item = client.gets(key);
		client.cas(key, Integer.valueOf(item.getValue().toString()) + 10, item.getCasUnique());
		
		System.out.println(client.get(key));
	}

}
