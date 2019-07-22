package stress.access;

import java.rmi.Naming;
import java.rmi.RemoteException;

import stress.rmiiinterface.loginInterface;



/**
 * This class shows the login and sign up methods locally. All method requests and submissions from the client will go through this class.
 * @author qaisf
 *
 */
public class loginAccess {


	loginInterface login;
	
	
	public loginAccess() {
		super();
		try {
//		login=(loginInterface)Naming.lookup("rmi://172.20.10.2/loginServer1910");
		login=(loginInterface)Naming.lookup("rmi://localhost/loginServer1910");
		}
		catch(Exception e) {
			System.out.println("Error in Login Access");
			e.printStackTrace();
		}
	}
	/**
	 * Did the user login successfully?
	 * @param username
	 * @param password
	 * @param occupation
	 * @return the userid if successful, return -1 if user doesn't exist.
	 */
	public int loginExt(String user,String pass,String access) {
		try {
			return login.initLogin(user, pass, access);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			return -1;
			// TODO: handle exception
		}
	}
	/**
	 * Did the user Sign Up successfully?
	 * @param username
	 * @param email
	 * @param password
	 * @param occupation
	 * @return the boolean status if the data was passed submitted correctly.
	 */
	public boolean signUpExt(String user,String email,String pass,String access) {
		try {
			return login.initSignUp(user, email, pass, access);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
		
		
	}
	
	

}
