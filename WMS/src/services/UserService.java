package services;

import java.io.FileNotFoundException;

import models.User;
import repositories.UserRepository;

public class UserService {

	public static User logIn(String username, String password) throws FileNotFoundException {
		return UserRepository.findUserByUsernameAndPassword(username, password);
	}
	
}
