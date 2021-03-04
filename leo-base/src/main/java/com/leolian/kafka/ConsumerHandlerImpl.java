package com.leolian.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.leolian.kafka.common.ConsumerHandler;

/**
 * 消费者实现类
 * @author lianliang
 */
public class ConsumerHandlerImpl implements ConsumerHandler<byte[], Long> {

	@Override
	public void handler(ConsumerRecords<byte[], Long> records) {
		//消费kafka消息
		//业务操作 ...
		String key = null;
		Long value = null;
		for (ConsumerRecord<byte[], Long> record : records) {
			key = new String(record.key());
			value = record.value();
			System.err.println(key + " -- " + value);
		}
	}

}
