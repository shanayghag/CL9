import java.rmi.*;
import java.rmi.server.*;

public class Implementer extends UnicastRemoteObject implements ConversionInterface {
	public Implementer() throws Exception {
		super();
	}
	
	public double convert(double n){
		double n_mts = n / 100;
		return n_mts;
	}
}