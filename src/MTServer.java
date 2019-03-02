import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
//Muhammad Hamza
//2016-UET-NML-CS-28
//           Protocol Syntax
//           For Signin: i//username//password
// 			 For signup: u//username//password//DoB//department

public class MTServer {
	static int clientRequests=0;
	public MTServer() {
		CommThread.LoadUsers();
		try
		{
			final int PORT=2222;
			ServerSocket ss=new ServerSocket(PORT);
			System.out.println("Waiting for client request");
			while(true){
				Socket client=ss.accept();
				clientRequests++;
				System.out.println("Server Accepted Request# "+clientRequests);
				CommThread ct = new CommThread(client);
				ct.start();
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}

	}

	// Main Function
	public static void main(String args[]){
		MTServer obj=new MTServer();
	}
}
