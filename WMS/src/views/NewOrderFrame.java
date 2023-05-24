package views;

import javax.swing.*;

import models.Client;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NewOrderFrame extends JFrame {
	private JTextField cityField;
	private JTextField addressField;

	/**
	 * Create the frame.
	 */
	public NewOrderFrame() {
		setResizable(false);
		setTitle("New order creation wizard");
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(null);
		
		JLabel clientNameLabel = new JLabel("Client name:");
		clientNameLabel.setBounds(12, 12, 76, 17);
		getContentPane().add(clientNameLabel);
		
		JComboBox<Client> clientComboBox = new JComboBox<Client>();
		clientComboBox.setBounds(106, 7, 195, 26);
		getContentPane().add(clientComboBox);
		
		try {
			clientComboBox.setModel(controllers.NewOrderController.loadComboBox());
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(clientComboBox, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		JLabel orLabel = new JLabel("OR");
		orLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orLabel.setBounds(319, 12, 23, 17);
		getContentPane().add(orLabel);
		
		JButton btnCreateANew = new JButton("Create new");
		btnCreateANew.setBounds(360, 7, 101, 27);
		getContentPane().add(btnCreateANew);
		
		JLabel cityInfoLabel = new JLabel("City:");
		cityInfoLabel.setBounds(12, 41, 27, 17);
		getContentPane().add(cityInfoLabel);
		
		JLabel addressInfoLabel = new JLabel("Address:");
		addressInfoLabel.setBounds(12, 70, 51, 17);
		getContentPane().add(addressInfoLabel);
		
		JLabel productsInfoLabel = new JLabel("Products:");
		productsInfoLabel.setBounds(12, 99, 60, 17);
		getContentPane().add(productsInfoLabel);
		
		cityField = new JTextField();
		cityField.setBounds(106, 39, 114, 21);
		getContentPane().add(cityField);
		cityField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(106, 68, 355, 21);
		getContentPane().add(addressField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 128, 241, 131);
		getContentPane().add(scrollPane);
		
		JList productsList = new JList();
		scrollPane.setViewportView(productsList);
		
		JButton btnAddProduct = new JButton("+");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddProduct.setFont(new Font("Dialog", Font.BOLD, 20));
		btnAddProduct.setBounds(202, 94, 51, 27);
		getContentPane().add(btnAddProduct);
		
		JLabel productNameLabel = new JLabel("Name:");
		productNameLabel.setBounds(271, 130, 39, 17);
		getContentPane().add(productNameLabel);
		
		JComboBox productNameComboBox = new JComboBox();
		productNameComboBox.setBounds(328, 125, 133, 26);
		getContentPane().add(productNameComboBox);
		
		JLabel orderQulantityLabel = new JLabel("Order quantity:");
		orderQulantityLabel.setBounds(271, 159, 101, 17);
		getContentPane().add(orderQulantityLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(390, 157, 71, 22);
		getContentPane().add(spinner);
		
		JLabel availableQuantityInfoLabel = new JLabel("Available quantity:");
		availableQuantityInfoLabel.setBounds(271, 188, 114, 17);
		getContentPane().add(availableQuantityInfoLabel);
		
		JLabel availableQuantityLabel = new JLabel("0");
		availableQuantityLabel.setBounds(401, 188, 60, 17);
		getContentPane().add(availableQuantityLabel);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(106, 94, 89, 27);
		getContentPane().add(btnRemove);

		
	}
}
