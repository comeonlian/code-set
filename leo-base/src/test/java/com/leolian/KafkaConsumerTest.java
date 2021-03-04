package com.leolian;

import java.util.Properties;

import com.leolian.kafka.BytesKafkaConsumer;
import com.leolian.kafka.ConsumerHandlerImpl;
import com.leolian.util.PropertiesUtils;

public class KafkaConsumerTest {

	public static void main(String[] args) {
		Properties props = PropertiesUtils.getProperties("consumer.properties");
		String topic = PropertiesUtils.getProperties("server.properties").getProperty("topic.test");
		
		BytesKafkaConsumer consumer = new BytesKafkaConsumer(props, topic, new ConsumerHandlerImpl());
		new Thread(consumer).start();
	}

}
