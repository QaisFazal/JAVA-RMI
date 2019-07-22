package stress.rmiiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jfree.data.jdbc.JDBCCategoryDataset;

public interface submitAndSolutions extends Remote {
	
	public boolean submitAns(int userId, int result) throws RemoteException, SQLException;
	
	public String getSolutions(String occupation, String id) throws RemoteException, SQLException;
	
//	public JDBCCategoryDataset pastResults(String userId) throws RemoteException, SQLException;
}