package Railway;

public class UserAccount {
	
	private String email;
	private String password;
	private String pid;
	
	public UserAccount(String email, String password, String pid) {
		this.email = email;
		this.password = password;
		this.pid = pid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	

}
