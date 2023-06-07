package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import controllers.OperatorController;
import models.Order;
import models.OrderProduct;
import models.Client;
import repositories.ClientRepository;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperatorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField addressField;
	private JLabel lblWelcome;
	
	private JList<OrderProduct> productsList = new JList<OrderProduct>();

	/**
	 * Create the frame.
	 */
	public OperatorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrP_Op_Client = new JScrollPane();
		scrP_Op_Client.setBounds(10, 36, 223, 253);
		contentPane.add(scrP_Op_Client);
		
		JList<Order> list = new JList<Order>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() == -1)
					return;
				
				Order currentOrder = list.getSelectedValue();
				
				// TODO: not following MVC - stop using repositories in the view
				Client currentClient;
				try {
					currentClient = ClientRepository.getClientByClientName(currentOrder.getClientName());
					addressField.setText(currentClient.getCity() + " - " + currentClient.getAddress());
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(list, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
				
				ArrayList<OrderProduct> currentOrderProducts = currentOrder.getProducts();
				
				DefaultListModel<OrderProduct> model = new DefaultListModel<OrderProduct>();
				
				for (OrderProduct product : currentOrderProducts) {
					model.addElement(product);
				}
				
				productsList.setModel(model);
			}
		});
		list.setVisibleRowCount(15);
		scrP_Op_Client.setViewportView(list);
		list.setModel(OperatorController.loadUnassignedOrders());
		
		JLabel lblNewLabel_1 = new JLabel("Client");
		scrP_Op_Client.setColumnHeaderView(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Unassigned orders:");
		lblNewLabel.setBounds(10, 11, 165, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrP_Op_Prod = new JScrollPane();
		scrP_Op_Prod.setBounds(245, 66, 318, 98);
		contentPane.add(scrP_Op_Prod);
		
		JLabel lblNewLabel_3 = new JLabel("Products");
		scrP_Op_Prod.setColumnHeaderView(lblNewLabel_3);
		scrP_Op_Prod.setViewportView(productsList);
		
		JButton btnNewButton = new JButton("Look assigned orders");
		btnNewButton.setBounds(276, 34, 287, 23);
		contentPane.add(btnNewButton);
		
		addressField = new JTextField();
		addressField.setEditable(false);
		addressField.setBounds(323, 176, 240, 31);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(245, 184, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		try {
			comboBox.setModel(OperatorController.loadDriverUsers());
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(comboBox, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
		}
		comboBox.setBounds(303, 232, 176, 18);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Driver:");
		lblNewLabel_4.setBounds(245, 234, 40, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedIndex() == -1) {
						throw new IllegalArgumentException("Order not selected!");
					}
					
					if (comboBox.getSelectedIndex() == -1) {
						throw new IllegalArgumentException("Driver not selected!");
					}
					
					OperatorController.btnAssignDriver(list.getSelectedValue(), (String) comboBox.getSelectedItem());
					
					setVisible(false);
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
					dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnNewButton_1, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(491, 225, 72, 32);
		contentPane.add(btnNewButton_1);
		
		lblWelcome = new JLabel("Welcome, realName!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(276, 10, 287, 17);
		contentPane.add(lblWelcome);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(458, 262, 105, 27);
		contentPane.add(btnLogOut);
	}
	
	public OperatorFrame(String realName) {
		this();
		lblWelcome.setText("Welcome, " + realName);
	}
}