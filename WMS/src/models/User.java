package models;

import java.io.FileNotFoundException;

public class User {
	
	private String username;
	private String password;
	private UserRole userRole;
	private String realName;
	
	public User() {
		
	}
	
	public User(String username, String password, String userRole, String realName) throws FileNotFoundException {
		setUsername(username);
		setPassword(password);
		setUserRole(UserRole.valueOf(userRole));
		setRealName(realName);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws FileNotFoundException {
		if (username.length() == 0)
			throw new IllegalArgumentException("Username field cannot be empty!");
		
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password.length() == 0)
			throw new IllegalArgumentException("Password field cannot be empty!");
		
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
		if (realName.length() == 0)
			throw new IllegalArgumentException("Real name field cannot be empty!");
		
		this.realName = realName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return ((User)obj).getUsername().equals(this.username)
					&& ((User)obj).getPassword().equals(this.password)
					&& ((User)obj).getUserRole().equals(this.userRole)
					&& ((User)obj).getRealName().equals(this.realName);
		} else {
			return false;
		}
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
