package stress.data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import stress.db.dbConnection;
import stress.rmiiinterface.submitAndSolutions;



/**
 * Implementation of the submitAndSolutions interface methods.
 *
 */
public class submitAndSolutionsServer extends UnicastRemoteObject implements submitAndSolutions {

	private static final long serialVersionUID = -6447131323043146191L;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	
	public submitAndSolutionsServer() throws RemoteException {
		super();
		con=dbConnection.db();
	}
	

	/**
	 * Implementation of the submitAns interface method.
	 *
	 */
	@Override
	public boolean submitAns(int userId, int result) throws RemoteException, SQLException {
		
		boolean success=false;
		try {
			String sql="INSERT into pastResults ('userId','date','result') VALUES (?,?,?)";
			
			DateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			String today=dateformat.format(date).toString();
			
			
			pst=con.prepareStatement(sql);
			pst.setString(1, Integer.toString(userId));
			pst.setString(2, today);
			pst.setString(3, Integer.toString(result));
//			rs=pst.executeQuery();.
			
				if(pst.executeUpdate()>0) {
					return success=true;
				}
				else {
					return success=false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				//try { rs.close(); } catch (Exception e) { e.printStackTrace();}
			    try { pst.close(); } catch (Exception e) { e.printStackTrace(); }
			    //try { con.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			
			
			
			return success;
	}
	

	/**
	 * Implementation of the getSolutions interface method.
	 *
	 */
	@Override
	public String getSolutions(String occupation, String id) throws RemoteException, SQLException {
	
		String Answer = "No Solutions";
		try {
			String sql="SELECT solution from solutions where (occupation = ? and id = ?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, occupation);
			pst.setString(2, id);
			
			rs=pst.executeQuery();
			
			if( rs.next()) {
				System.out.println("the Solutions "+rs.getString(1));
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
	 * Implementation of the pastResults interface method.
	 *
	 */
	@Override
	public String pastResults(String userId) throws RemoteException {
		try {
		   return "select date,result from pastResults where userId="+userId;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
