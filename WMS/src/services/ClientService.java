package services;

import java.io.FileNotFoundException;

import models.Client;
import repositories.ClientRepository;

public class ClientService {

	public static Client[] getAllClients() throws FileNotFoundException {
		Client[] clients = ClientRepository.getAllClients();
		sortClientsByName(clients);
		return clients;
	}
	
	public static Client[] getAllClientsSortedByName() throws FileNotFoundException {
		Client[] clients = getAllClients();
		return clients;
	}
	
	public static void addNewClient(Client newClient) throws FileNotFoundException {
		ClientRepository.addNewClient(newClient);
	}
	
	private static void sortClientsByName(Client[] clients) {
		int n = clients.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (clients[j].getClientName().compareTo(clients[j + 1].getClientName()) > 0) {
					Client temp = clients[j];
					clients[j] = clients[j + 1];
					clients[j + 1] = temp;
				}
			}
		}
	}
}
