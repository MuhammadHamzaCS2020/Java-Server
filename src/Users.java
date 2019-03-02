//Muhammad Hamza
//2016-UET-NML-CS-28

//           Protocol Syntax
//           For Signin: i//username//password
// 			 For signup: u//username//password//DoB//department
public class Users {
	private String Name,Password,DoB,Department;
	private String Resource1,Resource2,Resource3;

	public String getResource1() {
		return Resource1;
	}
	public void setResource1(String resource1) {
		Resource1 = resource1;
	}
	public String getResource2() {
		return Resource2;
	}
	public void setResource2(String resource2) {
		Resource2 = resource2;
	}
	public String getResource3() {
		return Resource3;
	}
	public void setResource3(String resource3) {
		Resource3 = resource3;
	}
	public Users() {
		
	}
	public Users(String name, String password, String doB, String department) {
		super();
		Name = name;
		Password = password;
		DoB = doB;
		Department = department;
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDoB() {
		return DoB;
	}

	public void setDoB(String doB) {
		DoB = doB;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
	public String completeRecord() {
		String str=this.getName()+"//"+this.getPassword()+"//"+this.getDoB()+"//"+this.getDepartment()
								 +"//"+this.getResource1()+"//"+this.getResource2()+"//"+this.getResource3();
		return str;
	}
	
}
