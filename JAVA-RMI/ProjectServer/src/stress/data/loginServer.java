package stress.data;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stress.db.dbConnection;
import stress.rmiiinterface.loginInterface;



/**
 * Implementation of the login and sign up interface methods.
 * @author qaisf
 *
 */
public class loginServer extends UnicastRemoteObject implements loginInterface {


	private static final long serialVersionUID = 3116993871029546181L;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	
	
	public loginServer() throws RemoteException {
		super();
		con=dbConnection.db();
		
		
	}
	
	
	
	/**
	 * Implementation of the initLogin interface method.
	 * @author qaisf
	 */
	@Override
	public int initLogin(String user, String pass, String access) throws RemoteException {
		
		int id=-1;
		
		try {
		String sql="SELECT id,userName,email,occupation,password from loginInfo where (userName=? and password=? and occupation=?)";
		
		
		pst=con.prepareStatement(sql);
		pst.setString(1, user);
		pst.setString(2, pass);
		pst.setString(3, access);
		
		rs=pst.executeQuery();
		
		
		
		
			if( rs.next()) {
				id=rs.getInt(1);
				return id;
			}
			else {
				return id;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try { rs.close(); } catch (Exception e) { e.printStackTrace();}
		    try { pst.close(); } catch (Exception e) { e.printStackTrace(); }
		    //try { con.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		
		
		
		return id;
	}


	/**
	 * Implementation of the initSignUp interface method.
	 * @author qaisf
	 */
	@Override
	public boolean initSignUp(String user, String email, String pass, String access) throws RemoteException {
		boolean result=false;
		String sql="INSERT into loginInfo ('userName','email','occupation','password') VALUES (?,?,?,?)";
		try {
			pst=con.prepareStatement(sql);
			
			pst.setString(1, user);
			pst.setString(2, email);
			pst.setString(3, access);
			pst.setString(4, pass);
			
			if(pst.executeUpdate()>0) {
				return result=true;
			}
			else {
				return result=false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    //try { con.close(); } catch (Exception e) { /* ignored */ }
		}
		
		
		return result;
		
	}
	

}
