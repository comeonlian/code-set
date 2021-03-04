package com.leolian.kafka.common;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface ConsumerHandler<K, V> {
	
	void handler(ConsumerRecords<K, V> records);
	
}
