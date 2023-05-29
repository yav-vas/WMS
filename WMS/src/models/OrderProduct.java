package models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderProduct {

	private String productName;
	private double unitPrice;
	
	private int orderQuantity;
	
	public OrderProduct() {
		
	}
	
	public OrderProduct(Product product, int orderQuantity) {
		setProductName(product.getProductName());
		setUnitPrice(product.getUnitPrice());
		setOrderQuantity(orderQuantity);
	}
	
	public OrderProduct(String productName, int orderQuantity) {
		setProductName(productName);
		setOrderQuantity(orderQuantity);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public double getCost() {
		BigDecimal costBigDecimal = new BigDecimal(unitPrice);
		costBigDecimal = costBigDecimal.multiply(new BigDecimal(orderQuantity));
		costBigDecimal.setScale(2, RoundingMode.DOWN);
		return costBigDecimal.doubleValue();
	}
	
	@Override
	public String toString() {
		return productName + " - " + orderQuantity;
	}
	
}
