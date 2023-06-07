package controllers;

import java.io.FileNotFoundException;

import javax.swing.*;

import models.Order;
import models.User;
import services.OrderService;
import services.UserService;

public class OperatorController {

	public static ListModel<Order> loadUnassignedOrders() {
		DefaultListModel<Order> model = new DefaultListModel<Order>();
		
		Order[] unassignedOrders;
		try {
			unassignedOrders = OrderService.getUnassignedOrders();
			
			for (int i = 0; i < unassignedOrders.length; i++) {
				model.addElement(unassignedOrders[i]);
			}
			
			return model;
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
	
	public static ComboBoxModel<String> loadDriverUsers() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		User[] driverUsers;
		try {
			driverUsers = UserService.getDriverUsersSortedByUsername();
			
			for (int i = 0; i < driverUsers.length; i++) {
				model.addElement(driverUsers[i].getRealName());
			}
			
			return model;
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}

	public static void btnAssignDriver(Order order, String driverName) {
		try {
			OrderService.assignDriver(order, driverName);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
}
