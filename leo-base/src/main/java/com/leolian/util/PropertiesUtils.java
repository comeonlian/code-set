package com.leolian.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.kafka.BytesKafkaProducer;

/**
 * 加载配置文件
 * @author Lian
 */
public class PropertiesUtils {
	private static final Logger LOG = LoggerFactory.getLogger(BytesKafkaProducer.class);
	
	/**
	 * 接收路径参数，返回properties
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		Properties prop = new Properties();
		try{
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
			prop.load(in);
		}catch (Exception e) {
			LOG.error("加载配置文件{}发生异常:{} ", path, e);
		}
		return prop;
	}
	
	public static void main(String[] args) {
		Properties prop = getProperties("consumer.properties");
		System.out.println(prop);
	}
}
