import java.rmi.*;
import java.util.*;

public class Client {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the distance in centimeters : ");
		double n = sc.nextDouble();
		
		ConversionInterface c_obj = (ConversionInterface)Naming.lookup("CONVERTER");
		double n_mts = c_obj.convert(n);
		
		System.out.println("Distance in meters : " + n_mts);
	}
}