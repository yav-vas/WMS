package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import models.Client;
import models.Product;
import models.OrderProduct;
import models.Order;
import javax.swing.event.ListSelectionListener;

import controllers.NewOrderController;

import javax.swing.event.ListSelectionEvent;

public class NewOrderFrame extends JFrame {
	
	private Order order = new Order();
	
	private JTextField cityField;
	private JTextField addressField;
	
	private JButton btnSaveNew;
	private JButton btnCancel;
	
	private JTextField clientNameField;
	
	private JButton btnRemove;
	private JList<OrderProduct> productsList;
	
	private JComboBox<Product> productComboBox;
	private JLabel availableQuantityLabel;
	private JLabel pricePerUnitLabel;
	private JSpinner orderQuantitySpinner;
	private JLabel totalLabel;

	/**
	 * Create the frame.
	 */
	public NewOrderFrame() {
		setResizable(false);
		setTitle("New order creation wizard");
		setBounds(100, 100, 475, 350);
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
				
				order.setClientName(currentClient.getClientName());
			}
		});
		clientComboBox.setBounds(106, 7, 195, 26);
		getContentPane().add(clientComboBox);
		
		try {
			clientComboBox.setModel(controllers.NewOrderController.loadClientComboBox());
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
					clientComboBox.setModel(controllers.NewOrderController.loadClientComboBox());
					
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
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderProduct currentProduct = productsList.getSelectedValue();
				
				order.removeProduct(currentProduct);
				((DefaultListModel<OrderProduct>) productsList.getModel()).removeElementAt(productsList.getSelectedIndex());
				productsList.setSelectedIndex(-1);
				totalLabel.setText(Double.toString(order.getTotal()));
				
				try {
					controllers.NewOrderController.btnRemoveProduct(currentProduct);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnRemove, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
				
				try {
					productComboBox.setModel(controllers.NewOrderController.loadProductComboBox());
					productComboBox.setSelectedIndex(-1);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnRemove, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
			}
		});
		btnRemove.setBounds(106, 94, 89, 27);
		getContentPane().add(btnRemove);
		btnRemove.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 128, 241, 181);
		getContentPane().add(scrollPane);
		
		productsList = new JList<OrderProduct>();
		productsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (productsList.getSelectedIndex() == -1)
					btnRemove.setVisible(false);
				else {
					btnRemove.setVisible(true);
					
					orderQuantitySpinner.setValue((Integer) 0);;
					availableQuantityLabel.setText("0");
					pricePerUnitLabel.setText("0");
					productComboBox.setSelectedIndex(-1);
				}
			}
		});
		productsList.setModel(new DefaultListModel<OrderProduct>());
		scrollPane.setViewportView(productsList);
		
		JButton btnAddProduct = new JButton("+");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<OrderProduct> model = (DefaultListModel<OrderProduct>) productsList.getModel();
				
				// no product is selected
				if (productComboBox.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(btnAddProduct, "No product selected to be added to the order!", "An error occured", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// zero order quantity is selected
				if (orderQuantitySpinner.getValue().equals((Integer) 0)) {
					JOptionPane.showMessageDialog(btnAddProduct, "Zero order quantity is selected!", "An error occured", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				OrderProduct currentProduct = new OrderProduct((Product) productComboBox.getSelectedItem(), (Integer) orderQuantitySpinner.getValue());
				model.addElement(currentProduct);
				order.addProduct(currentProduct);
				
				totalLabel.setText(Double.toString(order.getTotal()));
				
				try {
					controllers.NewOrderController.btnAddProduct(currentProduct);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnAddProduct, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
				
				orderQuantitySpinner.setValue((Integer) 0);;
				availableQuantityLabel.setText("0");
				pricePerUnitLabel.setText("0");
				productComboBox.setSelectedIndex(-1);
				
				try {
					productComboBox.setModel(controllers.NewOrderController.loadProductComboBox());
					productComboBox.setSelectedIndex(-1);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnAddProduct, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
			}
		});
		btnAddProduct.setFont(new Font("Dialog", Font.BOLD, 20));
		btnAddProduct.setBounds(202, 94, 51, 27);
		getContentPane().add(btnAddProduct);
		
		JLabel productNameLabel = new JLabel("Name:");
		productNameLabel.setBounds(271, 130, 39, 17);
		getContentPane().add(productNameLabel);
		
		productComboBox = new JComboBox<Product>();
		productComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (productComboBox.getSelectedIndex() == -1)
					return;
				
				productsList.setSelectedIndex(-1);
				btnRemove.setVisible(false);
			}
		});
		productComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (productComboBox.getSelectedIndex() == -1)
					return;
				
				Product currentProduct = (Product) productComboBox.getSelectedItem();
				
				availableQuantityLabel.setText(Integer.toString(currentProduct.getAvailableToOrderQuantity()));
				pricePerUnitLabel.setText(Double.toString(currentProduct.getUnitPrice()));
				orderQuantitySpinner.setModel(new SpinnerNumberModel(0, 0, currentProduct.getAvailableToOrderQuantity(), 1));
			}
		});
		productComboBox.setBounds(328, 125, 133, 26);
		getContentPane().add(productComboBox);
		
		try {
			productComboBox.setModel(controllers.NewOrderController.loadProductComboBox());
			productComboBox.setSelectedIndex(-1);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(productComboBox, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		JLabel orderQulantityLabel = new JLabel("Order quantity:");
		orderQulantityLabel.setBounds(271, 159, 101, 17);
		getContentPane().add(orderQulantityLabel);
		
		orderQuantitySpinner = new JSpinner();
		orderQuantitySpinner.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		orderQuantitySpinner.setBounds(390, 157, 71, 22);
		getContentPane().add(orderQuantitySpinner);
		
		JLabel availableQuantityInfoLabel = new JLabel("Available quantity:");
		availableQuantityInfoLabel.setBounds(271, 188, 114, 17);
		getContentPane().add(availableQuantityInfoLabel);
		
		availableQuantityLabel = new JLabel("0");
		availableQuantityLabel.setBounds(401, 188, 60, 17);
		getContentPane().add(availableQuantityLabel);
		
		JLabel pricePerUnitInfoLabel = new JLabel("Price per unit:");
		pricePerUnitInfoLabel.setBounds(271, 213, 84, 17);
		getContentPane().add(pricePerUnitInfoLabel);
		
		pricePerUnitLabel = new JLabel("0");
		pricePerUnitLabel.setBounds(401, 213, 60, 17);
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
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (order.getClientName() == null) {
						JOptionPane.showMessageDialog(btnAddProduct, "No client is selected!", "An error occured", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (order.getProducts().size() == 0) {
						JOptionPane.showMessageDialog(btnAddProduct, "No products selected!", "An error occured", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					// still in client editing mode
					if (clientComboBox.isVisible() == false) {
						JOptionPane.showMessageDialog(btnAddProduct, "Finish new client creation procedure", "An error occured", JOptionPane.ERROR_MESSAGE);
						return;
					}
					NewOrderController.btnOrder(order);
					dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(productComboBox, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOrder.setBounds(377, 271, 84, 38);
		getContentPane().add(btnOrder);
		
		totalLabel = new JLabel("0");
		totalLabel.setBounds(328, 243, 133, 17);
		getContentPane().add(totalLabel);
		
		JButton btnCancelOrder = new JButton("Cancel");
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancelOrder.setBounds(271, 271, 84, 38);
		getContentPane().add(btnCancelOrder);
		btnCancel.setVisible(false);
	}
	
	public NewOrderFrame(String realName) {
		this();
		order.setSalesRepName(realName);
	}
}
