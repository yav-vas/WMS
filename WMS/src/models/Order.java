package models;

import java.util.*;
import java.math.*;

public class Order {

	private Client client;
	private ArrayList<OrderProduct> products;
	private User driver;
	
	private double total;
	
	public Order() {
		setProducts(new ArrayList<OrderProduct>());
		setTotal(0);
	}
	
	public Order(Client client, ArrayList<OrderProduct> products) {
		this();
		setClient(client);
		setProducts(products);
	}
	
	public Order(Client client, ArrayList<OrderProduct> products, User driver) {
		this();
		setDriver(driver);
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public ArrayList<OrderProduct> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<OrderProduct> products) {
		this.products = products;
	}
	
	public User getDriver() {
		return driver;
	}
	
	public void setDriver(User driver) {
		this.driver = driver;
		
		if (driver.getUserRole() != UserRole.DRIVER) {
			throw new IllegalArgumentException("The user assigned is not a driver! Select a user of type driver!");
		}
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void addProduct(OrderProduct product) {
		products.add(product);
		total += product.getCost();
		
		BigDecimal totalBigDecimal = new BigDecimal(Double.toString(total));
		totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.CEILING);
		total = totalBigDecimal.doubleValue();
	}
	
	public void removeProduct(OrderProduct product) {
		products.remove(product);
		total -= product.getCost();
		
		BigDecimal totalBigDecimal = new BigDecimal(Double.toString(total));
		totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.FLOOR);
		total = totalBigDecimal.doubleValue();
	}
	
}
