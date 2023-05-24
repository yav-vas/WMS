package services;

import java.io.FileNotFoundException;

import models.Client;
import repositories.ClientRepository;

public class ClientService {

	public static Client[] getAllClients() throws FileNotFoundException {
		// TODO: write a sorting algorithm to sort the clients by clientName
		// TODO: create another method for sorted return, let it be getAllClientsSortedByName
		return ClientRepository.getAllClients();
	}
	
	public static void addNewClient(Client newClient) throws FileNotFoundException {
		ClientRepository.addNewClient(newClient);
	}
}
