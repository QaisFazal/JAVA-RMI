package stress.access;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jfree.data.jdbc.JDBCCategoryDataset;

import stress.rmiiinterface.loginInterface;
import stress.rmiiinterface.submitAndSolutions;

public class submitSolutionAccess {

	submitAndSolutions sub;
	
	public submitSolutionAccess() {
		super();
		// Placing the connectivity with the server for the submitAndSolutions Interface functions
		try {
//		sub=(submitAndSolutions)Naming.lookup("rmi://172.20.10.2/loginServer1912");
		sub=(submitAndSolutions)Naming.lookup("rmi://localhost/loginServer1912");
		}
		catch(Exception e) {
			System.out.println("Error in Submit and Solution Access");
			e.printStackTrace();
		}
	}
	public boolean submitAnsExt(int userId, int result) throws SQLException {
		try {
			// Set the answer by passing the userId and result, and return the success status in boolean form the server 
			return sub.submitAns(userId, result);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}
	public String getSolutionsExt(String occupation, String id) throws SQLException {
		try {
			// Get the solutions by passing the occupation and id to the server and then return it
			return sub.getSolutions(occupation, id);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
//	public JDBCCategoryDataset pastResultsExt(String userId) throws SQLException {
//		try {
//			return sub.pastResults(userId);
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//			return null;
//			// TODO: handle exception
//		}
//	}
}
