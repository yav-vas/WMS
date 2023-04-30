package repositories;

import java.io.*;
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
	
}
