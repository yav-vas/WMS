package repositories;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserRepository {

	public static User findUserByUsernameAndPassword(String username, String password) throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		
		while(usersFile.hasNext()) {
			User currentUser = new User(usersFile.next(), usersFile.next(), usersFile.next(), usersFile.next());
			
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
			User currentUser = new User(usersFile.next(), usersFile.next(), usersFile.next(), usersFile.next());
			users.add(currentUser);
		}
		
		usersFile.close();
		
		return users.toArray(new User[users.size()]);
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
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmp.txt");
		File tmpFile = new File("data/tmp.txt");
		
		while(usersFile.hasNext()) {
			User currentUser = new User(usersFile.next(), usersFile.next(), usersFile.next(), usersFile.next());
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
	
}
