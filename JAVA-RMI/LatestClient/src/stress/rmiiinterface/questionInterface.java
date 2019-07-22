package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


public interface questionInterface extends Remote {
	
	public String getQuestions(String index, String occupation) throws RemoteException, SQLException;
	
	public String getUserDetails(int id) throws RemoteException, SQLException;
	
	public String getUserOccupation(int id) throws RemoteException;

}