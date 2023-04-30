package repositories;

import java.io.*;
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
	
	public static boolean editUser() throws FileNotFoundException {
		File file = new File("data/users.txt");
		Scanner usersFile = new Scanner(file);
		// TODO: create a new file with the edited data and delete the previous one
		return false;
	}
	
}
