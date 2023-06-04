package models;

import java.util.*;
import java.math.*;

public class Order {

	private String salesRepName;
	private String clientName;
	private ArrayList<OrderProduct> products;
	private String driverName; // the real name of the driver
	
	private double total;
	
	public Order() {
		setProducts(new ArrayList<OrderProduct>());
		setTotal(0);
	}
	
	public Order(String clientName, ArrayList<OrderProduct> products) {
		this();
		setClientName(clientName);
		setProducts(products);
	}
	
	public Order(Client client, ArrayList<OrderProduct> products, String driverName) {
		this();
		setDriverName(driverName);
	}
	
	public String getSalesRepName() {
		return salesRepName;
	}
	
	public void setSalesRepName(String salesRepName) {
		this.salesRepName = salesRepName;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public ArrayList<OrderProduct> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<OrderProduct> products) {
		this.products = products;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		if (total < 0.01)
			this.total = 0;
		
		this.total = total;
	}
	
	public void addProduct(OrderProduct product) {
		products.add(product);
		
		BigDecimal totalBigDecimal = new BigDecimal(total);
		totalBigDecimal = totalBigDecimal.add(new BigDecimal(product.getCost()));
		totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.DOWN);
		setTotal(totalBigDecimal.doubleValue());
	}
	
	public void removeProduct(OrderProduct product) {
		products.remove(product);
		
		BigDecimal totalBigDecimal = new BigDecimal(total);
		totalBigDecimal = totalBigDecimal.subtract(new BigDecimal(product.getCost()));
		totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.DOWN);
		setTotal(totalBigDecimal.doubleValue());
	}
	
}
