package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class DriverFrame extends JFrame {

	private JPanel contentPane;
	
	private JLabel welcomeLabel;

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
		btnSetAsCompleted.setBackground(Color.GREEN);
		btnSetAsCompleted.setBounds(12, 219, 136, 43);
		orderPanel.add(btnSetAsCompleted);
		
		JButton btnSetAsCanceled = new JButton("Set as canceled");
		btnSetAsCanceled.setBackground(Color.RED);
		btnSetAsCanceled.setBounds(191, 219, 136, 43);
		orderPanel.add(btnSetAsCanceled);
		
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
		
		JLabel totalLabel = new JLabel("total");
		totalLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		totalLabel.setBounds(254, 159, 73, 17);
		orderPanel.add(totalLabel);
		
		JCheckBox chckbxPaidNow = new JCheckBox("Paid");
		chckbxPaidNow.setBounds(254, 182, 73, 25);
		orderPanel.add(chckbxPaidNow);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 102, 217, 105);
		orderPanel.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JButton btnClearSelection = new JButton("Clear selection");
		btnClearSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearSelection.setFont(new Font("Dialog", Font.BOLD, 20));
		btnClearSelection.setBounds(399, 69, 339, 37);
		contentPane.add(btnClearSelection);
		
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
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}
	
	public DriverFrame(String realName) {
		this();
		
		welcomeLabel.setText("Welcome, " + realName);
	}
}
