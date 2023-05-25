package models;

public class OrderProduct {

	private Product product;
	private int orderQuantity;
	
	public OrderProduct() {
		
	}
	
	public OrderProduct(Product product, int orderQuantity) {
		setProduct(product);
		setOrderQuantity(orderQuantity);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public double getCost() {
		return product.getUnitPrice() * orderQuantity;
	}
	
	@Override
	public String toString() {
		return product.getProductName() + " - " + orderQuantity;
	}
	
}
