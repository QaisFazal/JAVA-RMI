package stress.db;
import java.sql.*;


public class dbConnection {
	
	Connection con = null;
	
	
	public static Connection db() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:database/stressInfo.sqlite");
			return con;
		}
		catch(Exception e) {
			System.out.println("Problem while connecting to the database! :" + e);
			e.printStackTrace();
			return null;
		}
		
		
	}
}