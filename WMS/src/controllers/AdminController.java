package controllers;

import java.io.FileNotFoundException;

import javax.swing.*;

import models.User;

public class AdminController {

	public static ListModel<User> btnSaveChanges(User oldUser, User newUser) throws IllegalArgumentException {
		DefaultListModel<User> listModel = new DefaultListModel<User>();
		
		try {
			services.UserService.editUser(oldUser, newUser);
			
			User[] newUsers = services.UserService.getAllUsers();
			
			for(User u : newUsers) {
				listModel.addElement(u);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Data file not found!");
		}
		
		return listModel;
	}
}
