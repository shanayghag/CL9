import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import AdditionApp.Addition;
import AdditionApp.AdditionHelper;

import java.io.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		// TODO Auto-generated method stub
		ORB orbObj = ORB.init(args, null);
		
		NamingContextExt namingService = NamingContextExtHelper.narrow(orbObj.resolve_initial_references("NameService"));
		Addition add = AdditionHelper.narrow(namingService.resolve_str("ADDITION_SERVER"));
		
		int a = 20;
		int b = 100;
		
		int result = add.addition(a, b);
		System.out.println(result);
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
