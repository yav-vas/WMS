package services;

import java.io.FileNotFoundException;

import models.Product;
import models.OrderProduct;
import repositories.ProductRepository;

public class ProductService {

	public static Product[] getAllProducts() throws FileNotFoundException {
		// TODO: write a sorting algorithm to sort the products by productName
		// TODO: create another method for sorted return, let it be getAllProductsSortedByName
		return ProductRepository.getAllProducts();
	}
	
	public static void orderProductQuantity(OrderProduct product) throws FileNotFoundException {
		Product orderedProduct = product.getProduct();
		Product newProduct = new Product(orderedProduct);
		newProduct.setAvailableToOrderQuantity(orderedProduct.getAvailableToOrderQuantity() - product.getOrderQuantity());
		newProduct.setOrderedQuantity(newProduct.getOrderedQuantity() + product.getOrderQuantity());
		ProductRepository.overwriteExistingProduct(orderedProduct, newProduct);
	}
	
}
