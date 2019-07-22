package stress.access;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;

import stress.rmiiinterface.questionInterface;
public class questionsAccess {
	
	questionInterface question;
	
	
	public questionsAccess() {
		super();
		// Placing the connectivity with the server for the questionInterface functions
		try {
//			String url = new String("rmi://172.20.10.2/loginServer1911");
			String url = new String("rmi://localhost/loginServer1911");
			question = (questionInterface) Naming.lookup(url);
		}
		catch(Exception e) {
			System.out.println("Error in geting Questions");
			e.printStackTrace();
		}
	}
	public String getQuestionsExt(String index, String occupation) throws SQLException {
		try {
			System.out.println("getQuestionsExt  "+index);
			// Get the questions from the server by passing the index and the occupation and then return it
			return question.getQuestions(index, occupation);
			
		}
		catch (RemoteException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	public String getUserDetailsExt(int id) throws SQLException {
		try {
			System.out.println("getUserID  "+id);
			// Get the User Details from the server by passing the id and then return it
			return question.getUserDetails(id);
			
		}
		catch (RemoteException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public String getUserOccupationExt(int id) {
		try {
			System.out.println("getUserID  "+id);
			// Get the users occupation from the server by passing the id and then return it
			return question.getUserOccupation(id);
			
		}
		catch (RemoteException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
