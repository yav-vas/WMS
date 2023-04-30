package models;

public class UserViewModel {
	
	private String username;
	private UserRole userRole;
	private String realName;
	
	public UserViewModel() {
		
	}
	
	public UserViewModel(User user) {
		setUsername(user.getUsername());
		setUserRole(user.getUserRole());
		setRealName(user.getRealName());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
