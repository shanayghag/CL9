import org.apache.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.*;
import javax.jms.*;


public class Publisher {
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
			
			// Producer
			MessageProducer producer = ses.createProducer(topic);
			
			// Text message
			TextMessage txtMessage = ses.createTextMessage("IPL UPDATE : MI Lost against KKR");
			
			// Producer sends the message
			producer.send(txtMessage);
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
