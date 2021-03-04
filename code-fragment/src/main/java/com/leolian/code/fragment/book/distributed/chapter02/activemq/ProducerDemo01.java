package com.leolian.code.fragment.book.distributed.chapter02.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerDemo01 {

	public static void main(String[] args) throws Exception{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, 
				ActiveMQConnection.DEFAULT_BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("TestQueue");
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		ObjectMessage message = null;
		for(int i=0; i<1000; i++) {
			message = session.createObjectMessage("hello everyone!, times: "+i);
			producer.send(message);
			
			session.commit();
			//Thread.sleep(3000);
		}
		
	}

}
