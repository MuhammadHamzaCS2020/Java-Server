
import java.io.*;
import java.net.*;
//Muhammad Hamza
//2016-UET-NML-CS-28

//           Protocol Syntax
//           For Signin: i//username//password
// 			 For signup: u//username//password//DoB//department
import java.util.Scanner;
public class Client {
	private Users client=new Users();
	final int PORT=2222;
	final String IP="localhost";
	public Client() {
		try
		{
			Socket client =new Socket(IP,PORT);
			PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
			
			String request;
			Scanner input=new Scanner(System.in);
			
			System.out.println("For SignIn press I or i");
			System.out.println("For SignUp press U or u");
			System.out.println("For Check Resources R1,R2,R3");
			System.out.print("Enter Option: ");
			request = input.next();
			if(request.equals("Signup") || request.equals("signup")) {
				this.getUserNamePass();
				this.getDoBDepart();
				this.getResources();
				pw.println(request+"//"+this.client.getName()+"//"+this.client.getPassword()+"//"
								+this.client.getDoB()+"//"+this.client.getDepartment()
								+"//"+this.client.getResource1()+"//"+this.client.getResource2()
								+"//"+this.client.getResource3());
			}
			else if(request.equals("Signin") || request.equals("signin")) {
				this.getUserNamePass();
				pw.println(request+"//"+this.client.getName()+"//"+this.client.getPassword());	
			}
			else if(request.equals("R1") || request.equals("r1") ||
					request.equals("R2") || request.equals("r2") ||
					request.equals("R3") || request.equals("r3")){
				this.getUserNamePass();
				pw.println(request+"//"+this.client.getName()+"//"+this.client.getPassword());
			}
			
			//pw.println(this.name+","+this.pass);
			//pw.println("u//khan@namal.edu.pk//khan1257");
			BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(br.readLine());
			//client.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

	private void getDoBDepart() {
		Scanner input=new Scanner(System.in);
		String dob = null,Depart=null,UT=null;
		System.out.print("Enter Date of Birth:");
		dob=input.next();
		System.out.print("Enter Department:");
		Depart=input.next();
		client.setDoB(dob);
		client.setDepartment(Depart);

	}
	private void getResources() {
		Scanner input=new Scanner(System.in);
		String UT=null;
		System.out.print("Enter Resource 1:");
		UT=input.next();
		client.setResource1(UT);
		System.out.print("Enter Resource 2:");
		UT=input.next();
		client.setResource2(UT);
		System.out.print("Enter Resource 3:");
		UT=input.next();
		client.setResource3(UT);
	}
	 // Get User Name and Password from the User with specific requirements
	private void getUserNamePass() {
		Scanner input=new Scanner(System.in);
		String name = null,password=null;
		int flage=1;
		while(flage!=0) {
			System.out.print("Enter the User Name:");
			name=input.nextLine();
			if(usernameCorrection(name)) {
				flage=0;
			}
		}
		flage=1;
		while(flage!=0) {
			System.out.print("Enter Password:");
			password=input.nextLine();
			if(passwordCorrection(password)) {
				flage=0;
			}
		}
		client.setName(name);
		client.setPassword(password);
	}
	// Password must be at least 8 charactors
	private boolean passwordCorrection(String password) {
		if(password.length()<8) {
			System.out.println("Password minimum 8 digits again");
			return false;
		}		
		return true;
	}
	// User name must be contain @ charactor  
	private boolean usernameCorrection(String str) {
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='@') {
				return true;
			}			
		}
		System.out.println("Wrong please again ");
		return false;
	}
	public static void main(String args[]){
		Client obj=new Client();
	}
}