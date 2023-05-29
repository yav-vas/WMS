package services;

import java.io.FileNotFoundException;

import models.Order;
import repositories.OrderRepository;

public class OrderService {

	public static void addNewOrder(Order order) throws FileNotFoundException {
		// TODO: add debt to the new client
		OrderRepository.addNewOrder(order);
	}
}
