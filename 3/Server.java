import org.omg.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import AdditionApp.Addition;
import AdditionApp.AdditionHelper;

import java.io.*;
import java.util.*;


public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
		// TODO Auto-generated method stub
		
		ORB orbObj = ORB.init(args, null);
		AddObj addObj = new AddObj();
		addObj.setOrb(orbObj);
		
		POA rootPoa = POAHelper.narrow(orbObj.resolve_initial_references("RootPOA"));
		rootPoa.the_POAManager().activate();
		
		Addition add= AdditionHelper.narrow(rootPoa.servant_to_reference(addObj));
		
		NamingContextExt namingService = NamingContextExtHelper.narrow(orbObj.resolve_initial_references("NameService"));
		NameComponent[] addComponent = namingService.to_name("ADDITION_SERVER");
		
		namingService.rebind(addComponent, add);
		
		while(true){
			orbObj.run();
		}
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
