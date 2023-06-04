package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import models.User;

public class SalesRepresentativeFrame extends JFrame  {
	
	String loggedInUserRealName;
	
	public SalesRepresentativeFrame() {
		setTitle("Sales representative");
		setBounds(100, 100, 435, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewOrderFrame newOrderFrame = new NewOrderFrame(loggedInUserRealName);
				newOrderFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(189, 57, 234, 39);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 166, 158, 239);
		getContentPane().add(scrollPane);
		
		JLabel lblMyOrders = new JLabel("My orders:");
		lblMyOrders.setBounds(12, 12, 158, 17);
		getContentPane().add(lblMyOrders);
	}
	
	public SalesRepresentativeFrame(String realName) {
		this();
		loggedInUserRealName = new String(realName);
	}
}
