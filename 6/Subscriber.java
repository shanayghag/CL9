import org.apache.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.*;

import javax.jms.*;

public class Subscriber {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ConnectionFactory cf = new ActiveMQConnectionFactory(url);
			Connection conn = cf.createConnection();
			
			conn.start();
			// Make Session
			// Check the function parameters
			Session ses = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// Topic
			Topic topic = ses.createTopic("SPORTS");
			
			// Consumer
			MessageConsumer consumer = ses.createConsumer(topic);
			
			// New message Listener
			MessageListener msgListener = new MessageListener() {

				public void onMessage(Message msg) {
					// TODO Auto-generated method stub
					if(msg instanceof TextMessage) {
						TextMessage txtMsg = (TextMessage)msg;
						try {
							System.out.println(txtMsg.getText());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				
			};
			
			consumer.setMessageListener(msgListener);
			
			System.in.read();
			
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
