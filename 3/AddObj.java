import org.omg.CORBA.ORB;
import AdditionApp.AdditionPOA;


public class AddObj extends AdditionPOA{
	private ORB orb = null;

	public void setOrb(ORB o) {
		orb = o;
	}
	
	public int addition(int a, int b) {
		// TODO Auto-generated method stub
		System.out.println("Adding two number...");
		return a + b;
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		orb.shutdown(false);
	}

}
