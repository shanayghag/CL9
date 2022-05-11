import java.net.*;
import java.io.*;
import java.util.*;

class ServerClientThread extends Thread {
	Socket serverClient;
	int clientNo;
	int square;
	
	ServerClientThread(Socket inSocket, int counter) {
		serverClient = inSocket;
		clientNo = counter;
	}
	
	public void run() {
		try {
			// Reading from server side: in-stream
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			// Send data using out stream
        		DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
        		String clientMessage="", serverMessage="";
        		
        		while(!clientMessage.equals("bye")) {
        			clientMessage = inStream.readUTF();
        			System.out.println("From Client #" + clientNo + " : " + clientMessage);
        			square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
        			serverMessage = "From Server --> Client #" + clientNo + " : Square of " + clientMessage + " is " + square;
        			outStream.writeUTF(serverMessage);
        			outStream.flush();
        		}
        		
        		inStream.close();
        		outStream.close();
        		serverClient.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Client #"+clientNo+" exited!");
		}
	}
}
