package services;

import java.io.FileNotFoundException;

import models.User;
import repositories.UserRepository;

public class UserService {

	public static User logIn(String username, String password) throws FileNotFoundException {
		return UserRepository.findUserByUsernameAndPassword(username, password);
	}

	public static User[] getAllUsers() throws FileNotFoundException {
		// TODO: write a sorting algorithm to sort the users by username
		// TODO: create another method for sorted return, let it be getAllUsersSortedByUsername
		return UserRepository.getAllUsers();
	}
	
}
