import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversionInterface extends Remote {
	public double convert(double n) throws RemoteException;
}