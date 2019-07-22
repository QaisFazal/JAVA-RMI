package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


/**
 * RMI interface to submit user stress results to the server and retrieve solutions from the server database.
 */

public interface submitAndSolutions extends Remote {
	
	/**
	 * Submits the stress results to the database after the questionnaire. 
	 * @param id=the userid retrieved from initLogin. 
	 * @param result=the final stress result after answering the questionnaire
	 * @return boolean value of status of the submission. 
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public boolean submitAns(int userId, int result) throws RemoteException, SQLException;
	
	/**
	 * Retrieves the solutions for a user from the database depending on occupation.
	 * @param occupation=user occupation retrieved from questionServer.
	 * @param id=userid retrieved from initLogin. 
	 * @return boolean value of status of the submission. 
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public String getSolutions(String occupation, String id) throws RemoteException, SQLException;

	/**
	 * Retrieves the past results from the user to plot the the graph.
	 * @param id=userid retrieved from initLogin. 
	 * @return string value of the results. 
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public String pastResults(String userId) throws RemoteException, SQLException;
}