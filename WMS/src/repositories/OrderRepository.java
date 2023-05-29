package repositories;

import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

import models.Order;
import models.OrderProduct;

public class OrderRepository {

	public static Order readOrder(Scanner reader) {
		try {
			Order currentOrder = new Order();
			currentOrder.setClientName(reader.nextLine());
			
			int productsCount = Integer.parseInt(reader.nextLine());
			
			for (int i = 0; i < productsCount; i++) {
				OrderProduct orderProduct = new OrderProduct(reader.next(), Integer.parseInt(reader.nextLine()));
				currentOrder.addProduct(orderProduct);
			}
			
			String driverLine = reader.nextLine();
			
			if (!driverLine.equals("null")) {
				currentOrder.setDriverName(driverLine);
			}
			
			if (!reader.nextLine().equals("----------")) {
				throw new IllegalArgumentException("Wrong file format!");
			}
			
			return currentOrder;
		} catch (InputMismatchException ex) {
			throw new IllegalArgumentException("Wrong file format!");
		}
	}
	
	public static void writerOrder(PrintWriter writer, Order order) {
		StringBuilder orderString = new StringBuilder();
		
		orderString.append(order.getClientName() + "\n");
		orderString.append(order.getProducts().size() + "\n");
		
		for (int i = 0; i < order.getProducts().size(); i++) {
			orderString.append(order.getProducts().get(i).getProductName() + " " + order.getProducts().get(i).getOrderQuantity() + "\n");
		}
		
		orderString.append(order.getDriverName() + "\n");
		orderString.append("----------\n");
		
		writer.append(orderString);
		return;
	}

	public static void addNewOrder(Order order) throws FileNotFoundException {
		File file = new File("data/orders.txt");
		Scanner ordersFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmpOrders.txt");
		File tmpFile = new File("data/tmpOrders.txt");
		
		while (ordersFile.hasNext()) {
			Order currentOrder = readOrder(ordersFile);
			writerOrder(tmpWriter, currentOrder);
		}
		
		writerOrder(tmpWriter, order);
		
		ordersFile.close();
		tmpWriter.close();
		
		file.delete();
		tmpFile.renameTo(file);
	}
}