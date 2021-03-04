package com.leolian.code.fragment.book.distributed.chapter01.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LoadBalance {
	private static Map<String, Integer> serverWeightMap = null;
	private static Integer pos = 0;
	
	static {
		serverWeightMap = new HashMap<String, Integer>();
		serverWeightMap.put("192.168.1.100", 1);
		serverWeightMap.put("192.168.1.101", 1);
		serverWeightMap.put("192.168.1.102", 4);
		serverWeightMap.put("192.168.1.103", 1);
		serverWeightMap.put("192.168.1.104", 1);
		serverWeightMap.put("192.168.1.105", 3);
		serverWeightMap.put("192.168.1.106", 1);
		serverWeightMap.put("192.168.1.107", 2);
		serverWeightMap.put("192.168.1.108", 1);
		serverWeightMap.put("192.168.1.109", 1);
		serverWeightMap.put("192.168.1.100", 1);
	}
	
	/**
	 * 轮询法
	 * @return
	 */
	public static String roundRobin(){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		String server = null;
		synchronized (pos) {
			if(pos >= keySet.size()) {
				pos = 0;
			}
			server = keyList.get(pos);
			pos++;
		}
		return server;
	}
	
	/**
	 * 随机法
	 * @return
	 */
	public static String random() {
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		Random random = new Random();
		int randomPos = random.nextInt(keyList.size());
		String server = keyList.get(randomPos);
		return server;
	}
	
	/**
	 * 源地址哈希
	 * @param remoteip
	 * @return
	 */
	public static String consumerHash(String remoteip){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		int hashCode = remoteip.hashCode();
		int serverListSize = keyList.size();
		int serverPos = hashCode % serverListSize;
		
		return keyList.get(serverPos);
	}
	
	/**
	 * 加权轮询法
	 * @return
	 */
	public static String weightRoundRobin(){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		List<String> serverList = new ArrayList<String>();
		while(it.hasNext()) {
			String server = it.next();
			Integer weight = serverMap.get(server);
			for (int i=0; i<weight; i++){
				serverList.add(server);
			}
		}
		String server = null;
		synchronized (pos) {
			if(pos >= serverList.size()){
				pos = 0;
			}
			server = serverList.get(pos);
			pos++;
		}
		return server;
	}
	
	/**
	 * 加权随机法
	 * @return
	 */
	public static String weightRandom(){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		List<String> serverList = new ArrayList<String>();
		while(it.hasNext()) {
			String server = it.next();
			Integer weight = serverMap.get(server);
			for (int i=0; i<weight; i++){
				serverList.add(server);
			}
		}
		Random random = new Random();
		int randomPos = random.nextInt(serverList.size());
		String server = serverList.get(randomPos);
		return server;
	}
	
}
