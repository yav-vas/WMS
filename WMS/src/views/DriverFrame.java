package views;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.border.*;

import controllers.DriverController;

import java.awt.*;
import javax.swing.*;

import models.Client;
import models.Order;
import models.OrderProduct;
import repositories.ClientRepository;
import repositories.OrderRepository;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DriverFrame extends JFrame {

	private JPanel contentPane;
	
	private JLabel welcomeLabel;
	private String realName;
	
	private JList<Order> ordersList;

	/**
	 * Create the frame.
	 */
	public DriverFrame() {
		setResizable(false);
		setTitle("Driver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welcomeLabel = new JLabel("Welcome, name!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(399, 12, 339, 17);
		contentPane.add(welcomeLabel);
		
		JLabel ordersListLabel = new JLabel("Orders list:");
		ordersListLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		ordersListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordersListLabel.setBounds(12, 12, 375, 44);
		contentPane.add(ordersListLabel);
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		orderPanel.setBounds(399, 118, 339, 274);
		contentPane.add(orderPanel);
		orderPanel.setLayout(null);
		
		JButton btnSetAsCompleted = new JButton("Set as completed");
		btnSetAsCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OrderRepository.removeOrder(ordersList.getSelectedValue());
					ordersList.setModel(DriverController.loadDriverOrders(realName));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnSetAsCompleted, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(btnSetAsCompleted, "Data file not found!", "An error occured", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSetAsCompleted.setBackground(Color.GREEN);
		btnSetAsCompleted.setBounds(12, 219, 136, 43);
		orderPanel.add(btnSetAsCompleted);
		
		JButton btnSetAsCanceled = new JButton("Set as canceled");
		btnSetAsCanceled.setBackground(Color.RED);
		btnSetAsCanceled.setBounds(191, 219, 136, 43);
		orderPanel.add(btnSetAsCanceled);
		btnSetAsCanceled.setVisible(false); // TODO: fix to activate the expected behavior of the button
		
		JLabel customerNameInfoLabel = new JLabel("Customer name:");
		customerNameInfoLabel.setBounds(12, 12, 98, 17);
		orderPanel.add(customerNameInfoLabel);
		
		JLabel customerNameLabel = new JLabel("customerName");
		customerNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		customerNameLabel.setBounds(122, 12, 205, 17);
		orderPanel.add(customerNameLabel);
		
		JLabel addressInfoLabel = new JLabel("Address:");
		addressInfoLabel.setBounds(12, 44, 51, 17);
		orderPanel.add(addressInfoLabel);
		
		JLabel addressLabel = new JLabel("address");
		addressLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		addressLabel.setBounds(75, 44, 252, 17);
		orderPanel.add(addressLabel);
		
		JLabel lblOrderedProducts = new JLabel("Ordered products:");
		lblOrderedProducts.setBounds(12, 73, 109, 17);
		orderPanel.add(lblOrderedProducts);
		
		JLabel totalInfoLabel = new JLabel("Total:");
		totalInfoLabel.setBounds(254, 140, 73, 17);
		orderPanel.add(totalInfoLabel);
		totalInfoLabel.setVisible(false); // TODO: fix to show the real data
		
		JLabel totalLabel = new JLabel("total");
		totalLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		totalLabel.setBounds(254, 159, 73, 17);
		orderPanel.add(totalLabel);
		totalLabel.setVisible(false); // TODO: fix to show the real data
		
		JCheckBox chckbxPaidNow = new JCheckBox("Paid");
		chckbxPaidNow.setBounds(254, 182, 73, 25);
		orderPanel.add(chckbxPaidNow);
		chckbxPaidNow.setVisible(false); // TODO: fix to show the real data
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 102, 217, 105);
		orderPanel.add(scrollPane_1);
		
		JList<OrderProduct> productsList = new JList<OrderProduct>();
		scrollPane_1.setViewportView(productsList);
		
		JLabel informationLabel = new JLabel("To see order details select an order");
		informationLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationLabel.setBounds(399, 69, 339, 37);
		contentPane.add(informationLabel);
		
		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(613, 404, 125, 43);
		contentPane.add(btnLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 68, 375, 379);
		contentPane.add(scrollPane);
		
		ordersList = new JList<Order>();
		ordersList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (ordersList.getSelectedIndex() == -1)
					return;
				
				Order currentOrder = ordersList.getSelectedValue();
				
				// TODO: not following MVC - stop using repositories in the view
				Client currentClient;
				try {
					currentClient = ClientRepository.getClientByClientName(currentOrder.getClientName());
					customerNameLabel.setText(currentClient.getClientName());
					addressLabel.setText(currentClient.getCity() + " - " + currentClient.getAddress());
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(ordersList, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
				
				ArrayList<OrderProduct> currentOrderProducts = currentOrder.getProducts();
				
				DefaultListModel<OrderProduct> model = new DefaultListModel<OrderProduct>();
				
				for (OrderProduct product : currentOrderProducts) {
					model.addElement(product);
				}
				
				productsList.setModel(model);
				
				totalLabel.setText(Double.toString(currentOrder.getTotal()));
			}
		});
		scrollPane.setViewportView(ordersList);
	}
	
	public DriverFrame(String realName) {
		this();
		
		this.realName = realName;
		welcomeLabel.setText("Welcome, " + realName);
		
		try {
			ordersList.setModel(DriverController.loadDriverOrders(realName));
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
		}
	}
}
