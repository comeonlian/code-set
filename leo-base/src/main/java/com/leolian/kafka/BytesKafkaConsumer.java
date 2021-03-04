package com.leolian.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.kafka.common.ConsumerHandler;

/**
 * kafka的消费者
 * @author lianliang
 */
public class BytesKafkaConsumer implements Runnable{
	private static final Logger LOG = LoggerFactory.getLogger(BytesKafkaProducer.class);
	private String topic;
	private KafkaConsumer<byte[], Long> consumer;
	private ConsumerHandler<byte[], Long> handler;
	private boolean flag = false;
	
	public BytesKafkaConsumer(Properties prop, String topic, ConsumerHandler<byte[], Long> handler) {
		if(null==prop){
			LOG.debug("Properties为空，请设置配置参数");
		}
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.consumer = new KafkaConsumer<>(prop);
		this.topic = topic;
		this.handler = handler;
	}
	
	@Override
	public void run() {
		try{
			this.consumer.subscribe(Arrays.asList(topic));
			ConsumerRecords<byte[], Long> records = null;
			List<ConsumerRecord<byte[], Long>> partitionRecords = null;
			long lastOffset = 0;
			/*while(!flag){
				records = consumer.poll(1000);
				LOG.info("消费者拉取了 {} 条记录", records.count());
				handler.handler(records);
				consumer.commitSync();
			}*/
			while(!flag) {
				records = consumer.poll(Long.MAX_VALUE);
				for (TopicPartition partition : records.partitions()) {
					partitionRecords = records.records(partition);
					handler.handler(records);
					lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
					consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				}
				//Thread.sleep(100);
			}
		}catch (Exception e) {
			LOG.error("KAFKA消费者消费时发生异常 {}", e);
			if(!flag)
				throw e;
		}finally {
			this.consumer.close();
		}
	}
	
	
	public void shutdown(){
		this.flag = true;
		this.consumer.wakeup();
	}
	
}
