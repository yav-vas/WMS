package views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JLabel usernameLabel = new JLabel("Welcome, name!");

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(389, 12, 199, 17);
		contentPane.add(usernameLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 247, 297);
		contentPane.add(scrollPane);
		
		JList usersList = new JList();
		scrollPane.setViewportView(usersList);
		
		JLabel currentUsersLabel = new JLabel("Current users:");
		currentUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentUsersLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		currentUsersLabel.setBounds(12, 12, 247, 38);
		contentPane.add(currentUsersLabel);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(256, 102, 332, 192);
		contentPane.add(userInfoPanel);
		
		JButton btnAddNewUser = new JButton("Add a new user");
		btnAddNewUser.setBounds(262, 316, 125, 43);
		contentPane.add(btnAddNewUser);
		
		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(463, 316, 125, 43);
		contentPane.add(btnLogout);
		
		JLabel informationLabel = new JLabel("To edit the user's details select a username");
		informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationLabel.setBounds(262, 60, 326, 30);
		contentPane.add(informationLabel);
	}
	
	public AdminFrame(String realName) {
		this();
		usernameLabel.setText("Welcome, " + realName + "!");
	}
}
