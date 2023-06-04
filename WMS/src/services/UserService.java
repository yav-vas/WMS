package services;

import java.io.FileNotFoundException;

import models.User;
import repositories.UserRepository;

public class UserService {

	public static User logIn(String username, String password) throws FileNotFoundException {
		return UserRepository.findUserByUsernameAndPassword(username, password);
	}

	public static User[] getAllUsers() throws FileNotFoundException {
		User[] users = UserRepository.getAllUsers();
		sortUsersByUsername(users, 0, users.length - 1);
		return users;
	}
	
	public static User[] getAllUsersSortedByUsername() throws FileNotFoundException {
		return getAllUsers();
	}
	
	public static void editUser(User oldUser, User newUser) throws FileNotFoundException {
		UserRepository.editUser(oldUser, newUser);
	}
	
	private static void sortUsersByUsername(User[] users, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(users, low, high);
			sortUsersByUsername(users, low, pivotIndex - 1);
			sortUsersByUsername(users, pivotIndex + 1, high);
		}
	}
	
	private static int partition(User[] users, int low, int high) {
		User pivot = users[high];
		int i = low - 1;
		
		for (int j = low; j < high; j++) {
			if (users[j].getUsername().compareTo(pivot.getUsername()) < 0) {
				i++;
				swap(users, i, j);
			}
		}
		
		swap(users, i + 1, high);
		return i + 1;
	}
	//cause why not :P
	private static void swap(User[] users, int i, int j) {
		User temp = users[i];
		users[i] = users[j];
		users[j] = temp;
	}
}
