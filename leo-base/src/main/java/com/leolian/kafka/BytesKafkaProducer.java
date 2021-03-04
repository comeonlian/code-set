package com.leolian.kafka;

import java.util.Properties;
import java.util.concurrent.atomic.LongAdder;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BytesKafkaProducer {
	private static final Logger LOG = LoggerFactory.getLogger(BytesKafkaProducer.class);
	
	private final KafkaProducer<byte[], Long> producer;
	private final String topic;
	private LongAdder serial = new LongAdder();
	
	public BytesKafkaProducer(Properties prop, String topic){
		if(null==prop){
			LOG.debug("Properties为空，请设置配置参数");
		}
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.LongSerializer");
		this.topic = topic;
		this.producer = new KafkaProducer<byte[], Long>(prop);
	}
	
	/**
	 * 发送字节数组
	 * @param bytes
	 */
	public void send(byte[] bytes){
		try{
			LOG.error("PRODUCER推入数据到KAFKA队列");
			producer.send(new ProducerRecord<byte[], Long>(topic, bytes, getSerial()));
		}catch(Exception e){
			LOG.error("发送字节数组到KAFKA发生异常  {}", e);
		}
	}
	
	/**
	 * 发送字节数组
	 * @param bytes
	 * @param callBack
	 */
	public void send(byte[] bytes, Callback callBack){
		try{
			producer.send(new ProducerRecord<byte[], Long>(topic, bytes, getSerial()), callBack);
		}catch (Exception e) {
			LOG.error("发送字节数组到KAFKA发生异常  {}", e);
		}
	}
	
	
	public String getTopic() {
		return topic;
	}

	public long getSerial() {
		serial.increment();
		return serial.longValue();
	}
	
	public void close(){
		this.producer.close();
	}
}
