package controllers;

import java.io.FileNotFoundException;
import javax.swing.*;

import models.Client;

public class NewOrderController {

	public static ComboBoxModel<Client> loadComboBox() {
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
	
	public static void btnSaveNew(Client newClient) {
		try {
			services.ClientService.addNewClient(newClient);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Data file not found!");
		}
		
	}
}
