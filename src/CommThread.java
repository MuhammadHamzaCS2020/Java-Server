import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class CommThread extends Thread {
	private Socket client;
	private static ArrayList<Users> _users = new ArrayList<>();
	public CommThread(Socket s){
		this.client = s;
	}

	public void run()
	{
		try {
			InputStreamReader isr=new InputStreamReader(client.getInputStream());
			BufferedReader br =new BufferedReader(isr);
			String str=br.readLine();

			//			boolean flage=this.authentication(str);
			//			System.out.println(this.authentication(str));

			PrintStream ps=new PrintStream(client.getOutputStream());
			ps.println(this.clientRequestParsing(str));
			System.out.println();
			//ps.print(flage);	
			//cs.close();	
		}
		catch (Exception e)
		{}
	}


	// ******** Some Old Functions ************* //
	// ******** 
	public boolean clientRequestParsing(String clientRequst) {
		String[] clientData=clientRequst.split("//");
		//System.out.println(clientData[0]+" "+clientData[1]+" "+clientData[2]);
		if(clientData[0].equals("Signup") || clientData[0].equals("signup")) {
			return SigneUp(clientData);
		}
		else if(clientData[0].equals("Signin") || clientData[0].equals("signin")) {
			return SigneIn(clientData);
		}// Resources Request
		else if(clientData[0].equals("R1") || clientData[0].equals("r1") ||
				clientData[0].equals("R2") || clientData[0].equals("r2") ||
				clientData[0].equals("R3") || clientData[0].equals("r3")) {
			//System.out.println(clientData[0]+" "+clientData[1]+" "+clientData[2]);
			boolean flage=Resources(clientData);
			System.out.println(flage+"\n");
			return flage;
		}
		else {
			return false;
		}
	}
	/*
	 *	This Function Validate or Verify the Specific User Resources
	 */
	private boolean Resources(String[] namepass) {
		for(int i=0; i<_users.size(); i++) {
			if(namepass[1].equals(_users.get(i).getName()) && namepass[2].equals(_users.get(i).getPassword())) {	
				System.out.println("User is Exist: "+namepass[0]);
				if(namepass[0].charAt(1)==49) {
					System.out.println(namepass[0].charAt(1));
					if(_users.get(i).getResource1().equals("yes")) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(namepass[0].charAt(1)==50) {
					if(_users.get(i).getResource2().equals("yes")) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(namepass[0].charAt(1)==51) {
					if(_users.get(i).getResource3().equals("yes")) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	/* 
	 *	This Function Enter the Record of the New Client in the _user Array 
	 *	and Also store New client record in the Database or File...
	 */
	private boolean SigneUp(String[] Spliter) {
		Users obj=new Users();
		obj.setName(Spliter[1]);
		obj.setPassword(Spliter[2]);
		obj.setDoB(Spliter[3]);
		obj.setDepartment(Spliter[4]);
		obj.setResource1(Spliter[5]);
		obj.setResource2(Spliter[6]);
		obj.setResource3(Spliter[7]);
		_users.add(obj);
		System.out.println("New User is Succefully Registered...");
		this.NewUser();
		return true;
	}
	/* 
	 *	This Validate and Verify the User Name and Password of the client... 
	 */
	private boolean SigneIn(String[] namepass) {
		System.out.println(namepass[0]+"//"+namepass[1]);
		for(int i=0; i<_users.size(); i++) {
			if(namepass[1].equals(_users.get(i).getName()) && namepass[2].equals(_users.get(i).getPassword())) {
				return true;
			}
		}
		return false;
	}
	/*
	 *	This Function Store the Record in file after the arriving the New client...
	 */
	private void NewUser() {
		try {
			String str =null;
			File TextFile = new File("usersData.txt");	
			FileWriter fw = new FileWriter(TextFile);
			for(int i=0; i<_users.size(); i++) {
				str=_users.get(i).completeRecord();
				fw.write(str);
				fw.write("\n");
			}
			fw.close();

		} catch (IOException iox) {
			//do stuff with exception
			iox.printStackTrace();
		}

	}
	/*
	 * This Function Load the User Data from file to Array List...
	 */
	public static void LoadUsers() {
		try {
			File TextFile = new File("usersData.txt");
			BufferedReader fw = new BufferedReader(new FileReader(TextFile));
			String str=null;
			String[] Spliter=null;
			while ((str = fw.readLine()) != null) {
				System.out.println(str);
				Spliter=str.split("//");
				Users obj=new Users();
				obj.setName(Spliter[0]);
				obj.setPassword(Spliter[1]);
				obj.setDoB(Spliter[2]);
				obj.setDepartment(Spliter[3]);
				obj.setResource1(Spliter[4]);
				obj.setResource2(Spliter[5]);
				obj.setResource3(Spliter[6]);
				_users.add(obj);
			}
			fw.close();
		} catch (IOException iox) {
			//do stuff with exception
			iox.printStackTrace();
		}
	}	
}
