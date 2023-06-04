package services;

import java.io.FileNotFoundException;

import models.Product;
import models.OrderProduct;
import repositories.ProductRepository;

public class ProductService {

	public static Product[] getAllProducts() throws FileNotFoundException {
		Product[] products = ProductRepository.getAllProducts();
		sortProductsByName(products);
		return products;
	}
	
	public static Product[] getAllProductsSortedByName() throws FileNotFoundException {
		return getAllProducts();
	}
	
	public static void orderProductQuantity(OrderProduct product) throws FileNotFoundException {
		ProductRepository.editProductQuantity(product.getProductName(), product.getUnitPrice(), product.getOrderQuantity());
	}

	public static void freeProductQuantity(OrderProduct product) throws FileNotFoundException {
		ProductRepository.editProductQuantity(product.getProductName(), product.getUnitPrice(), -product.getOrderQuantity());
	}
	
	private static void sortProductsByName(Product[] products) {
		int n = products.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (products[j].getProductName().compareTo(products[minIndex].getProductName()) < 0) {
					minIndex = j;
				}
			}
			Product temp = products[i];
			products[i] = products[minIndex];
			products[minIndex] = temp;
		}
	}
}
