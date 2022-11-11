
public class Login {
	String username;
	String password;
	boolean verified;
	
	public Login(String username, String password, boolean verified) {
		this.username = username;
		this.password = password;
		this.verified = verified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
