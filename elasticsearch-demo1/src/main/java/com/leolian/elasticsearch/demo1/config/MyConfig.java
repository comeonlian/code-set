/**
 * 
 */
package com.leolian.elasticsearch.demo1.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * @author lianliang
 * @date 2017年12月2日 下午11:25:31
 */
@Configuration
public class MyConfig {
	
	@Bean
	public TransportClient client() throws Exception {
		InetSocketTransportAddress node1 = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
		//InetSocketTransportAddress node2 = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9301);
		Settings settings = Settings.builder().put("cluster.name", "lee").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(node1);
		//client.addTransportAddress(node2);
		return client;
	}
	
}
