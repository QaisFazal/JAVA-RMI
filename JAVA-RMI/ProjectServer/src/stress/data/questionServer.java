package stress.data;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import stress.db.dbConnection;
import stress.rmiiinterface.questionInterface;


/**
 * Implementation of the question interfaces.
 *
 */
public class questionServer extends UnicastRemoteObject implements questionInterface {
	
	
	private static final long serialVersionUID = -6447131323043146191L;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
		
	public questionServer() throws RemoteException{
		super();
		con=dbConnection.db();
		
	}

	/**
	 * Implementation of the getQuestions interface method.
	 *
	 */
	@Override
	public String getQuestions(String index,String occupation) throws RemoteException, SQLException {
		System.out.println("the index "+index);
		System.out.println("the index "+occupation);
		String Answer = "No questions.";
		String sql;
		try {
			if (occupation.equals("Undergraduate")) {
				sql="SELECT questions from questionsAndAnswers where (rowid = ? and occupation = ?)";
				pst=con.prepareStatement(sql);
				pst.setString(1, index);
				pst.setString(2, occupation);
				
				
			} 
			else if (occupation.equals("Student")) {
				sql="SELECT questions from questionsAndAnswers2 where (rowid = ? and occupation = ?)";
				pst=con.prepareStatement(sql);
				pst.setString(1, index);
				pst.setString(2, occupation);
				
				
			} else if (occupation.equals("Employee")) {
				sql="SELECT questions from questionsAndAnswers1 where (rowid = ? and occupation = ?)";
				pst=con.prepareStatement(sql);
				pst.setString(1, index);
				pst.setString(2, occupation);
			}
			 rs=pst.executeQuery();
			
			
			if( rs.next()) {
				System.out.println("the next "+rs.getString(1));
				return Answer=rs.getString(1);
			}
			else {
				System.out.println("Sorry!!!  "+Answer);
				return Answer;
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
		return Answer;
		
			
	}


	/**
	 * Implementation of the getUserDetails interface method.
	 *
	 */
	@Override
	public String getUserDetails(int id) throws RemoteException, SQLException {
		String Answer = "No User";
		try {
			String sql="SELECT userName from loginInfo where id = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, Integer.toString(id));
			
			rs=pst.executeQuery();
			
			if( rs.next()) {
				System.out.println("the user "+rs.getString(1));
				return Answer=rs.getString(1);
			}
			else {
				System.out.println("Sorry!!!  "+Answer);
				return Answer;
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
		return Answer;
		
	}


	/**
	 * Implementation of the getUserOccupation interface method.
	 *
	 */
	@Override
	public String getUserOccupation(int id) throws RemoteException {
		String Answer = "No Occupation";
		try {
			String sql="SELECT occupation from loginInfo where id = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, Integer.toString(id));
			
			rs=pst.executeQuery();
			
			if( rs.next()) {
				System.out.println("the occupation "+rs.getString(1));
				return Answer=rs.getString(1);
			}
			else {
				System.out.println("Sorry!!!  "+Answer);
				return Answer;
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
		return Answer;
	}

}