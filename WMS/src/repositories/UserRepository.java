package repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import models.User;
import models.UserRole;

public class UserRepository {
	
	public static User readUser(Scanner reader) throws FileNotFoundException {
		User user = new User(reader.next(), reader.next(), reader.next(), reader.next());
		
		return user;
	}

	public static User findUserByUsernameAndPassword(String username, String password) throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		
		while(usersFile.hasNext()) {
			User currentUser = readUser(usersFile);
			
			if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
				return currentUser;
			}
		}
		
		usersFile.close();
		
		return null;
	}

	public static User[] getAllUsers() throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		
		ArrayList<User> users = new ArrayList<User>();
		
		while(usersFile.hasNext()) {
			User currentUser = readUser(usersFile);
			users.add(currentUser);
		}
		
		usersFile.close();
		
		return users.toArray(new User[users.size()]);
	}
	
	public static User[] getAllDriverUsers() throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		
		ArrayList<User> driverUsers = new ArrayList<User>();
		
		while(usersFile.hasNext()) {
			User currentUser = readUser(usersFile);
			
			if (currentUser.getUserRole() == UserRole.DRIVER)
				driverUsers.add(currentUser);
		}
		
		usersFile.close();
		
		return driverUsers.toArray(new User[driverUsers.size()]);
	}
	
	// if the added user is null - nothing will be added
	public static void addUser(PrintWriter file, User user) {
		if (user == null) {
			return;
		}
		
		StringBuilder userString = new StringBuilder();
		
		userString.append(user.getUsername() + " ");
		userString.append(user.getPassword() + " ");
		userString.append(user.getUserRole().toString() + " ");
		userString.append(user.getRealName() + "\n");
		
		file.write(userString.toString());
	}
	
	// if newUser is null - the oldUser will simply be removed
	// if oldUser is null - the newUser will be added
	public static void editUser(User oldUser, User newUser) throws FileNotFoundException {
		if (newUser != null && oldUser == null && doesUsernameExist(newUser.getUsername()))
			throw new IllegalArgumentException("Username with this username already exists!");
		
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmpUsers.txt");
		File tmpFile = new File("data/tmpUsers.txt");
		
		while(usersFile.hasNext()) {
			User currentUser = readUser(usersFile);
			if (currentUser.equals(oldUser)) {
				addUser(tmpWriter, newUser);
			} else {
				addUser(tmpWriter, currentUser);
			}
		}
		
		if (oldUser == null) {
			addUser(tmpWriter, newUser);
		}
		
		usersFile.close();
		tmpWriter.close();
		
		file.delete();
		tmpFile.renameTo(file);
	}

	public static boolean doesUsernameExist(String username) throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		
		while(usersFile.hasNext()) {
			User currentUser = readUser(usersFile);
			
			if (currentUser.getUsername().equals(username)) {
				usersFile.close();
				return true;
			}
		}
		
		usersFile.close();
		
		return false;
	}
	
}
