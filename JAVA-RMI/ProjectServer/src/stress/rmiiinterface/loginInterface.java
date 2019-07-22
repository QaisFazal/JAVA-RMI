package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * RMI interface to allow retrieve user information from the server and to submit data
 * to the server. 
 * @author qaisf
 *
 */
public interface loginInterface extends Remote {

	/**
	 * checks the database for available user. 
	 * @param username,password,occupation. 
	 * @return the unique userid if available. 
	 * @throws RemoteException
	 */
	public int initLogin(String user,String pass,String access) throws RemoteException;
	
	/**
	 * Signs a user up to the system if he does not have an account. 
	 * @param username,email,password,occupation 
	 * @return the boolean value if user is added or not. 
	 * @throws RemoteException
	 */
	public boolean initSignUp(String user,String email,String pass,String access) throws RemoteException;

}
