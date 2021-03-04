package com.leolian.common;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.kafka.BytesKafkaProducer;
/**
 * 需要手动设置Properties
 * @author Lian
 */
public class Data2Kafka extends BatchTask<Map<String, byte[]>> {
	private Logger log = LoggerFactory.getLogger(Data2Kafka.class);
	private static Data2Kafka instance;
	private ConcurrentHashMap<String, BytesKafkaProducer> producers = new ConcurrentHashMap<>(10);
	private Properties prop = null;
	private boolean flag = false;
	
	private Data2Kafka() { }
	
	public static synchronized Data2Kafka getInstance(){
		if(null==instance)
			instance = new Data2Kafka();
		return instance;
	}
	
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	@Override
	public void service() {
		try{
			while (!flag) {
				Map<String, byte[]> map = this.poll();
				if(null==map){
					continue ;
				}
				String topic;
				byte[] bytes;
				BytesKafkaProducer producer;
				for (Map.Entry<String, byte[]> entry : map.entrySet()) {
					topic = entry.getKey();
					bytes = entry.getValue();
					producer = producers.get(topic);
					if(null==producer){
						producer = new BytesKafkaProducer(prop, topic);
					}
					producer.send(bytes);
				}
			}
		}catch (Exception e) {
			log.error("PUSH到KAFKA的服务方法发生异常  {}", e);
		}finally {
			//关闭消费者
			for (Map.Entry<String, BytesKafkaProducer> entry: producers.entrySet()) {
				entry.getValue().close();
			}
		}
	}
	
	public void stop(){
		this.flag = true;
	}
}
