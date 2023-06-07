package services;

import java.io.FileNotFoundException;

import models.Order;
import repositories.OrderRepository;

public class OrderService {

	public static void addNewOrder(Order order) throws FileNotFoundException {
		// TODO: add debt to the new client
		OrderRepository.addNewOrder(order);
	}
	
	public static Order[] getUnassignedOrders() throws FileNotFoundException {
		return OrderRepository.getAllOrdersWithoutDriver();
	}
	
	public static Order[] getDriverOrders(String driverName) throws FileNotFoundException {
		return OrderRepository.getAllOrdersWithDriver(driverName);
	}

	public static void assignDriver(Order order, String driverName) throws FileNotFoundException {
		OrderRepository.assignDriver(order, driverName);
	}
}
