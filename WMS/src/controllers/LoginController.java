package controllers;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.ListModel;

import models.*;
import services.*;
import views.*;

public class LoginController {

	public static JFrame btnLogIn(String username, String password) throws IllegalArgumentException{
		try {
			User user = UserService.logIn(username, password);
			
			if (user == null) {
				throw new IllegalArgumentException("Wrong username or password!");
			} else {
				switch (user.getUserRole()) {
					case ADMIN:
						return new AdminFrame(user.getRealName(), UserService.getAllUsers());
					case SALES_REPRESENTATIVE:
						return new SalesRepresentativeFrame();
					case DRIVER:
						return new DriverFrame(user.getRealName());
					default:
						return null;
				}
			}
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
	
}
