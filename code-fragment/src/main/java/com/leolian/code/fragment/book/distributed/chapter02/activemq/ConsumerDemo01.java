package com.leolian.code.fragment.book.distributed.chapter02.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerDemo01 {

	public static void main(String[] args) throws Exception{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				ActiveMQConnection.DEFAULT_BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("TestQueue");
		MessageConsumer consumer = session.createConsumer(destination);
		ObjectMessage message = null;
		while (true) {
			message = (ObjectMessage) consumer.receive();
			if (null != message) {
				String messageContent = message.getObject().toString();
				System.out.println(messageContent);
			} else {
				break;
			}
		}
	}

}
