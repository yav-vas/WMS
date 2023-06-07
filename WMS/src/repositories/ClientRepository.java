package repositories;

import java.util.*;
import java.io.*;

import models.Client;

public class ClientRepository {

	public static Client readClient(Scanner reader) {
		try {
			Client client = new Client(reader.nextLine(), reader.nextLine(), reader.nextLine(), Double.parseDouble(reader.nextLine()));
			if (!reader.nextLine().equals("----------")) {
				throw new IllegalArgumentException("Wrong file format!");
			}
			return client;
		} catch (InputMismatchException ex) {
			throw new IllegalArgumentException("Wrong file format!");
		}
	}
	
	public static void writeClient(PrintWriter writer, Client client) {
		StringBuilder clientString = new StringBuilder();
		
		clientString.append(client.getClientName() + "\n");
		clientString.append(client.getCity() + "\n");
		clientString.append(client.getAddress() + "\n");
		clientString.append(client.getDebt() + "\n");
		clientString.append("----------\n");
		
		writer.append(clientString);
		return;
	}
	
	public static Client[] getAllClients() throws FileNotFoundException {
		File file = new File("data/clients.txt");
		Scanner clientsFile = new Scanner(file);
		
		ArrayList<Client> clients = new ArrayList<Client>();
		
		while (clientsFile.hasNext()) {
			Client currentClient = readClient(clientsFile);
			clients.add(currentClient);
		}
		
		clientsFile.close();
		
		return clients.toArray(new Client[clients.size()]);
	}
	
	public static Client getClientByClientName(String clientName) throws FileNotFoundException {
		File file = new File("data/clients.txt");
		Scanner clientsFile = new Scanner(file);
		
		while (clientsFile.hasNext()) {
			Client currentClient = readClient(clientsFile);
			if (currentClient.getClientName().equals(clientName))
				return currentClient;
		}
		
		clientsFile.close();
		
		return null;
	}
	
	public static void addNewClient(Client newClient) throws FileNotFoundException {
		File file = new File("data/clients.txt");
		Scanner clientsFile = new Scanner(file);
		PrintWriter tmpWriter = new PrintWriter("data/tmpClients.txt");
		File tmpFile = new File("data/tmpClients.txt");
		
		while (clientsFile.hasNext()) {
			Client currentClient = readClient(clientsFile);
			writeClient(tmpWriter, currentClient);
		}
		
		writeClient(tmpWriter, newClient);
		
		clientsFile.close();
		tmpWriter.close();
		
		file.delete();
		tmpFile.renameTo(file);
	}
	
}
