package models;

public class User {
	
	private String username;
	private String password;
	private UserRole userRole;
	private String realName;
	
	public User() {
		
	}
	
	public User(String username, String password, String userRole, String realName) {
		setUsername(username);
		setPassword(password);
		setUserRole(UserRole.valueOf(userRole));
		setRealName(realName);
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(username + " - ");
		result.append(userRole.toString() + " - ");
		result.append(realName);
		
		return result.toString().trim();
	}
	
}
