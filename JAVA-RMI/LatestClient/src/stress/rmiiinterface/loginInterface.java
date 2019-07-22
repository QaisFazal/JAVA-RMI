package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface loginInterface extends Remote {
	
	public int initLogin(String user,String pass,String access) throws RemoteException;
	
	public boolean initSignUp(String user,String email,String pass,String access) throws RemoteException;

}
