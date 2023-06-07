package controllers;

import java.io.FileNotFoundException;

import javax.swing.*;

import models.Order;
import services.OrderService;

public class DriverController {
	
	public static ListModel<Order> loadDriverOrders(String driverName) {
		DefaultListModel<Order> model = new DefaultListModel<Order>();
		
		Order[] driverOrders;
		try {
			driverOrders = OrderService.getDriverOrders(driverName);
			
			for (Order order : driverOrders) {
				model.addElement(order);
			}
			
			return model;
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
}
