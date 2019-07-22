package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;



/**
 * RMI interface to retrieve questions from the server database depending on user's occupation.
 */


public interface questionInterface extends Remote {
	
	
	/**
	 * Retrieve specific questions from the database. 
	 * @param index = number of the question,occupation =users occupation. 
	 * @return question from the database. 
	 * @throws RemoteException
	 */
	public String getQuestions(String index,String occupation) throws RemoteException, SQLException;
	
	
	/**
	 * Retrieve the username after login from available userid. 
	 * @param id=the userid returned from initLogin. 
	 * @return the username of the logged in user. 
	 * @throws RemoteException
	 */
	public String getUserDetails(int id) throws RemoteException, SQLException;
	
	/**
	 * Retrieve the user occupation from the database to get questions relevant to a particular user's occupation. 
	 * @param id=the userid returned from initLogin 
	 * @return the occupation of logged in user. 
	 * @throws RemoteException
	 */
	public String getUserOccupation(int id) throws RemoteException;

}