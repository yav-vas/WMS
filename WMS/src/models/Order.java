package models;

import java.util.*;

public class Order {

	private Client client;
	private ArrayList<Product> products;
	private User driver;
	
	public Order() {
		
	}
	
	public Order(Client client, ArrayList<Product> products) {
		this();
		setClient(client);
		setProducts(products);
	}
	
	public Order(Client client, ArrayList<Product> products, User driver) {
		this();
		setDriver(driver);
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
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
	
}
