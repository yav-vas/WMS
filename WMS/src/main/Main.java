package main;
import views.LoginFrame;

import java.io.FileNotFoundException;
import java.util.*;

import models.Order;
import repositories.OrderRepository;

public class Main {

	// Launch the application
	public static void main(String[] args) throws FileNotFoundException {
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
	}

}
