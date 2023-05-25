package models;

import java.util.Objects;

public class Product {

	private String productName;
	
	private double unitPrice;
	
	private int warehouseQuantity; // in the warehouse
	private int availableToOrderQuantity; // in the warehouse, but not ordered
	private int orderedQuantity; // still in the warehouse, but ordered
	private int travellingQuantity; // on the road
	
	public Product() {
		
	}
	
	public Product(String productName, double unitPrice) {
		setProductName(productName);
		setUnitPrice(unitPrice);
	}
	
	public Product(String productName, double unitPrice, int warehouseQuantity, int availableToOrderQuantity, int orderedQuantity, int travellingQuantity) {
		this(productName, unitPrice);
		setWarehouseQuantity(warehouseQuantity);
		setAvailableToOrderQuantity(availableToOrderQuantity);
		setOrderedQuantity(orderedQuantity);
		setTravellingQuantity(travellingQuantity);
	}
	
	public Product(Product product) {
		this(product.getProductName(), product.getUnitPrice(), product.getWarehouseQuantity(), product.getAvailableToOrderQuantity(), product.getOrderedQuantity(), product.getTravellingQuantity());
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

	public int getWarehouseQuantity() {
		return warehouseQuantity;
	}

	public void setWarehouseQuantity(int warehouseQuantity) {
		this.warehouseQuantity = warehouseQuantity;
	}

	public int getAvailableToOrderQuantity() {
		return availableToOrderQuantity;
	}

	public void setAvailableToOrderQuantity(int availableToOrderQuantity) {
		this.availableToOrderQuantity = availableToOrderQuantity;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public int getTravellingQuantity() {
		return travellingQuantity;
	}

	public void setTravellingQuantity(int travellingQuantity) {
		this.travellingQuantity = travellingQuantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return availableToOrderQuantity == other.availableToOrderQuantity && orderedQuantity == other.orderedQuantity
				&& Objects.equals(productName, other.productName) && travellingQuantity == other.travellingQuantity
				&& Double.doubleToLongBits(unitPrice) == Double.doubleToLongBits(other.unitPrice)
				&& warehouseQuantity == other.warehouseQuantity;
	}

	@Override
	public String toString() {
		return productName;
	}
	
}
