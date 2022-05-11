import java.rmi.*;
import java.rmi.registry.*;

public class Server {
	public static void main(String[] args) throws Exception {
		Implementer obj = new Implementer();
		Naming.rebind("CONVERTER", obj);
		System.out.println("--SERVER STARTED");
	}
}