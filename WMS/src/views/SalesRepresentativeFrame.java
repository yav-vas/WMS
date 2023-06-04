package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import models.User;
import javax.swing.SwingConstants;

public class SalesRepresentativeFrame extends JFrame  {
	
	JLabel lblWelcomeRealName;
	String loggedInUserRealName;
	
	public SalesRepresentativeFrame() {
		setTitle("Sales representative");
		setBounds(100, 100, 435, 140);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewOrderFrame newOrderFrame = new NewOrderFrame(loggedInUserRealName);
				newOrderFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 41, 411, 55);
		getContentPane().add(btnNewButton);
		
		lblWelcomeRealName = new JLabel("Welcome, realName!");
		lblWelcomeRealName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeRealName.setBounds(12, 12, 411, 17);
		getContentPane().add(lblWelcomeRealName);
	}
	
	public SalesRepresentativeFrame(String realName) {
		this();
		loggedInUserRealName = new String(realName);
		lblWelcomeRealName.setText("Welcome, " + realName + "!");
	}
}
