package repositories;

import java.io.*;
import java.util.*;

import models.Product;

public class ProductRepository {

	public static Product readProduct(Scanner reader) {
		try {
			Product product = new Product(reader.nextLine(), Double.parseDouble(reader.nextLine()),
					Integer.parseInt(reader.nextLine()), Integer.parseInt(reader.nextLine()), 
					Integer.parseInt(reader.nextLine()), Integer.parseInt(reader.nextLine()));
			if (!reader.nextLine().equals("----------")) {
				throw new IllegalArgumentException("Wrong file format!");
			}
			return product;
		} catch (InputMismatchException ex) {
			throw new IllegalArgumentException("Wrong file format!");
		}
	}
	
	public static void writeProduct(PrintWriter writer, Product product) {
		StringBuilder productString = new StringBuilder();
		
		productString.append(product.getProductName() + "\n");
		productString.append(product.getUnitPrice() + "\n");
		productString.append(product.getWarehouseQuantity() + "\n");
		productString.append(product.getAvailableToOrderQuantity() + "\n");
		productString.append(product.getOrderedQuantity() + "\n");
		productString.append(product.getTravellingQuantity() + "\n");
		productString.append("----------\n");
		
		writer.append(productString);
		return;
	}
	
	public static Product[] getAllProducts() throws FileNotFoundException {
		File file = new File("data/products.txt");
		Scanner productsFile = new Scanner(file);
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		while (productsFile.hasNext()) {
			Product currentProduct = readProduct(productsFile);
			products.add(currentProduct);
		}
		
		productsFile.close();
		
		return products.toArray(new Product[products.size()]);
	}

	// if orderedQuantityChange is positive => ordered quantity
	// if orderedQuantityChange is negative => freed quantity
	public static void editProductQuantity(String productName, double unitPrice, int orderedQuantityChange) throws FileNotFoundException {
		File file = new File("data/products.txt");
		Scanner productsFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmpProducts.txt");
		File tmpFile = new File("data/tmpProducts.txt");
		
		while (productsFile.hasNext()) {
			Product currentProduct = readProduct(productsFile);
			
			if (currentProduct.getProductName().equals(productName)) {
				Product newProduct = new Product(currentProduct);
				newProduct.setAvailableToOrderQuantity(currentProduct.getAvailableToOrderQuantity() - orderedQuantityChange);
				newProduct.setOrderedQuantity(currentProduct.getOrderedQuantity() + orderedQuantityChange);
				writeProduct(tmpWriter, newProduct);
			} else {
				writeProduct(tmpWriter, currentProduct);
			}
		}
		
		productsFile.close();
		tmpWriter.close();
		
		file.delete();
		tmpFile.renameTo(file);
		
		return;
	}
}
