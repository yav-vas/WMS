package repositories;

import java.util.Scanner;
import java.io.*;
import java.util.*;

import models.Order;
import models.OrderProduct;

public class OrderRepository {

	public static Order readOrder(Scanner reader) {
		try {
			Order currentOrder = new Order();
			
			String salesRepNameLine = reader.nextLine();
			
			if (salesRepNameLine.equals("null")) {
				throw new IllegalArgumentException("Sales representative name not set!");
			}
			
			currentOrder.setSalesRepName(salesRepNameLine);
			currentOrder.setClientName(reader.nextLine());
			
			int productsCount = Integer.parseInt(reader.nextLine());
			
			for (int i = 0; i < productsCount; i++) {
				String productName = reader.next();
				int productQuantity = Integer.parseInt(reader.next());
				reader.nextLine(); // go to the next product
				OrderProduct orderProduct = new OrderProduct(productName, productQuantity);
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
		
		orderString.append(order.getSalesRepName() + "\n");
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
	
	public static Order[] getAllOrdersWithoutDriver() throws FileNotFoundException {
		List<Order> ordersWithoutDriver = new ArrayList<Order>();
		
		File file = new File("data/orders.txt");
		Scanner ordersFile = new Scanner(file);
		
		while (ordersFile.hasNext()) {
			Order currentOrder = readOrder(ordersFile);
			if (currentOrder.getDriverName() == null) {
				ordersWithoutDriver.add(currentOrder);
			}
		}
		
		ordersFile.close();
		
		Order[] result = new Order[ordersWithoutDriver.size()];
		ordersWithoutDriver.toArray(result);
		return result;
	}
	
	public static Order[] getAllOrdersWithDriver(String driverRealName) throws FileNotFoundException {
        List<Order> ordersWithDriver = new ArrayList<Order>();

        File file = new File("data/orders.txt");
        Scanner ordersFile = new Scanner(file);

        while (ordersFile.hasNext()) {
            Order currentOrder = readOrder(ordersFile);
            String driverName = currentOrder.getDriverName();
            
            if (driverName == null)
            	continue;
            
            if (currentOrder.getDriverName().equals(driverRealName)) {
                ordersWithDriver.add(currentOrder);
            }
        }

        ordersFile.close();

        Order[] result = new Order[ordersWithDriver.size()];
        ordersWithDriver.toArray(result);
        return result;
    }
	
	public static void assignDriver(Order order, String driverName) throws FileNotFoundException {
		File file = new File("data/orders.txt");
		Scanner ordersFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmpOrders.txt");
		File tmpFile = new File("data/tmpOrders.txt");
		
		while (ordersFile.hasNext()) {
			Order currentOrder = readOrder(ordersFile);
			if (currentOrder.equals(order)) {
				currentOrder.setDriverName(driverName);
			}
			writerOrder(tmpWriter, currentOrder);
		}
		
		ordersFile.close();
		tmpWriter.close();
		
		file.delete();
		tmpFile.renameTo(file);
	}
	
	public static void removeOrder(Order orderToRemove) throws FileNotFoundException {
        File file = new File("data/orders.txt");
        Scanner ordersFile = new Scanner(file);
        PrintWriter tmpWriter = new PrintWriter("data/tmpOrders.txt");
        File tmpFile = new File("data/tmpOrders.txt");

        while (ordersFile.hasNext()) {
            Order currentOrder = readOrder(ordersFile);
            if (!currentOrder.equals(orderToRemove)) {
                writerOrder(tmpWriter, currentOrder);
            }
        }

        ordersFile.close();
        tmpWriter.close();

        file.delete();
        tmpFile.renameTo(file);
    }
}