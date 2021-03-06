package com.hlj.five.staticnetworkConnectors;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QR1 {
	public static void main(String[] args) throws Exception {
		
		String linuxIp = "myLinuxQj";

		ConnectionFactory cf = new ActiveMQConnectionFactory(
				"tcp://"+linuxIp+":61616");
		
		Connection connection = cf.createConnection();
		connection.start();
		
		final Session session = connection.createSession(Boolean.TRUE,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("QR");//"my-queue");

		for(int i=0;i<1;i++){
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				
				public void onMessage(Message m) {
					TextMessage msg = (TextMessage)m;
					try {
						System.out.println("QR1111111==="+msg.getText());
						session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
	}

}
