package com.leolian;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.leolian.common.Data2Kafka;
import com.leolian.util.PropertiesUtils;

public class KafkaProducerTest {

	public static void main(String[] args) throws Exception {
		//通用配置
		Properties props = PropertiesUtils.getProperties("producer.properties");
		Data2Kafka data2Kafka = Data2Kafka.getInstance();
		data2Kafka.setProp(props);
		data2Kafka.start(2);
		
		String topic = PropertiesUtils.getProperties("server.properties").getProperty("topic.test");
		Map<String, byte[]> map = null;
		for(long i=0; i<100; i++) {
			map = new HashMap<>();
			map.put(topic, ("kafka-test" + i).getBytes());
			data2Kafka.put(map);
//			System.out.println("Data to kafka : "+map.toString());
			Thread.sleep(10);
		}
		
	}

}
