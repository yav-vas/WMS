package models;

public class Client {

	private String clientName;
	
	private String city;
	private String address;
	
	private double debt;
	
	public Client() {
		setDebt(0);
	}
	
	public Client(String clientName, String city, String address) {
		this();
		setClientName(clientName);
		setCity(city);
		setAddress(address);
	}
	
	public Client(String clientName, String city, String address, double debt) {
		this(clientName, city, address);
		setDebt(debt);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	@Override
	public String toString() {
		return clientName;
	}
}

