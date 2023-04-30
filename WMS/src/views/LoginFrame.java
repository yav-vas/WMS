package views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to WMS");
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(12, 12, 426, 28);
		contentPane.add(welcomeLabel);
		
		JLabel informationLabel = new JLabel("Please provide your login credentials");
		informationLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationLabel.setBounds(12, 52, 426, 28);
		contentPane.add(informationLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(22, 99, 109, 17);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(22, 135, 109, 17);
		contentPane.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(149, 100, 139, 21);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 136, 139, 21);
		contentPane.add(passwordField);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				try {
					setVisible(false);
					controllers.LoginController.btnLogIn(username, password).setVisible(true);
					dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnLogIn, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		btnLogIn.setBounds(149, 169, 105, 27);
		contentPane.add(btnLogIn);
	}
}
