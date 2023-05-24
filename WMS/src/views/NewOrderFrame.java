package views;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import models.Client;

public class NewOrderFrame extends JFrame {
	private JTextField cityField;
	private JTextField addressField;
	
	private JButton btnSaveNew;
	private JButton btnCancel;
	private JTextField clientNameField;

	/**
	 * Create the frame.
	 */
	public NewOrderFrame() {
		setResizable(false);
		setTitle("New order creation wizard");
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(null);
		
		JLabel clientNameInfoLabel = new JLabel("Client name:");
		clientNameInfoLabel.setBounds(12, 12, 76, 17);
		getContentPane().add(clientNameInfoLabel);
		
		JComboBox<Client> clientComboBox = new JComboBox<Client>();
		clientComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (clientComboBox.getSelectedIndex() == -1)
					return;
				
				Client currentClient = (Client) clientComboBox.getSelectedItem();
				
				cityField.setText(currentClient.getCity());
				cityField.setEditable(false);
				
				addressField.setText(currentClient.getAddress());
				addressField.setEditable(false);
				
				btnSaveNew.setVisible(false);
			}
		});
		clientComboBox.setBounds(106, 7, 195, 26);
		getContentPane().add(clientComboBox);
		
		try {
			clientComboBox.setModel(controllers.NewOrderController.loadComboBox());
			clientComboBox.setSelectedIndex(-1);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(clientComboBox, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		JLabel orLabel = new JLabel("OR");
		orLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orLabel.setBounds(319, 12, 23, 17);
		getContentPane().add(orLabel);
		
		btnSaveNew = new JButton("Save new");
		btnSaveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client newClient = new Client(clientNameField.getText(), cityField.getText(), addressField.getText());
					controllers.NewOrderController.btnSaveNew(newClient);
					
					clientNameField.setVisible(false);
					clientComboBox.setVisible(true);
					clientComboBox.setModel(controllers.NewOrderController.loadComboBox());
					
					ComboBoxModel<Client> comboBoxModel = clientComboBox.getModel();
					
					for (int i = 0; i < comboBoxModel.getSize(); i++) {
						Client currentClient = comboBoxModel.getElementAt(i);
						if (currentClient.equals(newClient)) {
							clientComboBox.setSelectedIndex(i);
						}
					}
					
					btnSaveNew.setVisible(false);
					btnCancel.setVisible(false);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnSaveNew, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSaveNew.setBounds(254, 36, 101, 27);
		getContentPane().add(btnSaveNew);
		btnSaveNew.setVisible(false);
		
		JButton btnCreateNew = new JButton("Create new");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientComboBox.setSelectedIndex(-1);
				clientComboBox.setVisible(false);
				
				clientNameField.setVisible(true);
				clientNameField.setText("");
				
				cityField.setEditable(true);
				cityField.setText("");
				
				addressField.setEditable(true);
				addressField.setText("");
				
				btnSaveNew.setVisible(true);
				btnCancel.setVisible(true);
			}
		});
		btnCreateNew.setBounds(360, 7, 101, 27);
		getContentPane().add(btnCreateNew);
		
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
		
		JLabel pricePerUnitInfoLabel = new JLabel("Price per unit:");
		pricePerUnitInfoLabel.setBounds(271, 217, 84, 17);
		getContentPane().add(pricePerUnitInfoLabel);
		
		JLabel pricePerUnitLabel = new JLabel("0");
		pricePerUnitLabel.setBounds(401, 217, 60, 17);
		getContentPane().add(pricePerUnitLabel);
		
		JLabel totalInfoLabel = new JLabel("Total:");
		totalInfoLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		totalInfoLabel.setBounds(271, 242, 60, 17);
		getContentPane().add(totalInfoLabel);
		
		clientNameField = new JTextField();
		clientNameField.setText("");
		clientNameField.setBounds(106, 10, 195, 21);
		getContentPane().add(clientNameField);
		clientNameField.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientComboBox.setVisible(true);
				clientNameField.setVisible(false);
				btnSaveNew.setVisible(false);
				btnCancel.setVisible(false);
			}
		});
		btnCancel.setBounds(360, 36, 101, 27);
		getContentPane().add(btnCancel);
		btnCancel.setVisible(false);
	}
}
