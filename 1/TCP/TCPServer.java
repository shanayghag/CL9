import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {
	public static void main(String[] args) throws Exception {
		try{
			ServerSocket server = new ServerSocket(8888);
			int counter = 0;
			System.out.println("Server Started ...");
			
			while(true) {
				counter++;
				// Server accepts the client connection request
				Socket serverClient = server.accept();
				System.out.println("-- Client #" + counter + " started!");
				// Sending request separate thread
				ServerClientThread sct = new ServerClientThread(serverClient, counter);
				sct.start();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
