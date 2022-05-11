import java.net.*;
import java.util.*;
import java.io.*;

public class TCPClient{
	public static void main(String[] args) throws Exception {
		// Getting the ip address
		InetAddress ip = InetAddress.getLocalHost();
		int port = 8888;
		try {
			// Creating a socket
			Socket socket = new Socket(ip, port);
			// Reading from server side: in-stream
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			// Send data using out stream
        		DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        		
        		// Read from terminal
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		String clientMessage = "", serverMessage = "";
        		
        		while(!clientMessage.equals("bye")) {
        			System.out.println("Enter the number");
        			clientMessage = br.readLine();
        			outStream.writeUTF(clientMessage);
        			outStream.flush();
        			serverMessage = inStream.readUTF();
        			System.out.println(serverMessage);
        		}
        		
        		// Closing datastreams and socket
        		outStream.close();
        		inStream.close();
        		socket.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
