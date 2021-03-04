package com.leolian.code.fragment.book.distributed.chapter02.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerDemo02 {
	
	public static void main(String[] args) throws Exception{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("TestTopic");
		MessageProducer producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		TextMessage message = null;
		for(int i=0; i<10; i++) {
			message = session.createTextMessage();
			message.setText("message_hello_topic, times: "+i);
			producer.send(message);
			Thread.sleep(1000);
		}
	}
	
}
