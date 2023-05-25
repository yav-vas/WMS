package controllers;

import java.io.FileNotFoundException;
import javax.swing.*;

import models.Client;
import models.OrderProduct;
import models.Product;

public class NewOrderController {

	public static ComboBoxModel<Client> loadClientComboBox() {
		DefaultComboBoxModel<Client> model = new DefaultComboBoxModel<Client>();
		
		try {
			Client[] clients = services.ClientService.getAllClients();
			
			for (Client c : clients) {
				model.addElement(c);
			}
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
		
		return model;
	}
	
	public static ComboBoxModel<Product> loadProductComboBox() {
		DefaultComboBoxModel<Product> model = new DefaultComboBoxModel<Product>();
		
		try {
			Product[] products = services.ProductService.getAllProducts();
			
			for (Product c : products) {
				model.addElement(c);
			}
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
		
		return model;
	}
	
	public static void btnSaveNew(Client newClient) {
		try {
			services.ClientService.addNewClient(newClient);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
	
	public static void btnAddProduct(OrderProduct product) {
		try {
			services.ProductService.orderProductQuantity(product);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
	}
}
