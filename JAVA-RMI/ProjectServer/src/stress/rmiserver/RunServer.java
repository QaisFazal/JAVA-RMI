package stress.rmiserver;
import java.net.MalformedURLException;
import java.rmi.*;

import stress.data.loginServer;
import stress.data.questionServer;
import stress.data.submitAndSolutionsServer;
import stress.rmiiinterface.loginInterface;
import stress.rmiiinterface.questionInterface;
import stress.rmiiinterface.submitAndSolutions;



/**
 * Run the server here.
 * @author qaisf
 *
 */
public class RunServer {

	public static void main(String[] args)  {
		try {
			
			loginInterface login=new loginServer();
			questionInterface questionOut=new questionServer();
			submitAndSolutions submitandsolutions=new submitAndSolutionsServer();
			
			System.out.println("Starting the server.........\n");
			//System.setProperty("java.rmi.server.hostname","172.20.10.2");
			
			
			Naming.rebind("rmi://localhost/loginServer1910",login);
			System.out.println("Login Server Ready!");
			Naming.rebind("rmi://localhost/loginServer1911", questionOut);
			System.out.println("Questionnaire Server Ready!");
			Naming.rebind("rmi://localhost/loginServer1912", submitandsolutions);
			System.out.println("Submit and Solutions Server Ready!\n");
			
			System.out.println("All Servers ready! Let's run the client!");
			

		} catch (RemoteException e) {
			System.out.println("An error occured: "+e.toString()); 
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		

	}

}
