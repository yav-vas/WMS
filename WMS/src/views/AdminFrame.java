package views;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import models.User;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JPanel userInfoPanel;
	
	private JLabel welcomeLabel;
	private JLabel informationLabel;
	
	private JButton btnClearSelection;
	private JButton btnSaveChanges;
	
	private JList<User> usersList;
	
	private JTextField usernameField;
	private JTextField realNameField;
	private JPasswordField passwordField;
	private JTextField userRoleField;

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welcomeLabel = new JLabel("Welcome, name!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(362, 12, 316, 17);
		contentPane.add(welcomeLabel);
		
		JLabel currentUsersLabel = new JLabel("Current users:");
		currentUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentUsersLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		currentUsersLabel.setBounds(12, 12, 338, 38);
		contentPane.add(currentUsersLabel);
		
		usersList = new JList<User>();
		
		userInfoPanel = new JPanel();
		userInfoPanel.setBounds(362, 102, 316, 192);
		contentPane.add(userInfoPanel);
		userInfoPanel.setLayout(null);
		userInfoPanel.setVisible(false);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		usernameLabel.setBounds(12, 12, 81, 17);
		userInfoPanel.add(usernameLabel);
		
		JLabel realNameLabel = new JLabel("Real name:");
		realNameLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		realNameLabel.setBounds(12, 41, 89, 17);
		userInfoPanel.add(realNameLabel);
		
		JLabel userRoleLabel = new JLabel("User role:");
		userRoleLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		userRoleLabel.setBounds(12, 70, 81, 17);
		userInfoPanel.add(userRoleLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		passwordLabel.setBounds(12, 99, 81, 17);
		userInfoPanel.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSaveChanges.setEnabled(true);
			}
		});
		usernameField.setBounds(111, 11, 114, 21);
		userInfoPanel.add(usernameField);
		usernameField.setColumns(10);
		
		realNameField = new JTextField();
		realNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSaveChanges.setEnabled(true);
			}
		});
		realNameField.setText("");
		realNameField.setBounds(111, 40, 193, 21);
		userInfoPanel.add(realNameField);
		realNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSaveChanges.setEnabled(true);
			}
		});
		passwordField.setBounds(111, 98, 114, 21);
		userInfoPanel.add(passwordField);
		
		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setBounds(173, 137, 131, 43);
		userInfoPanel.add(btnDeleteUser);
		
		btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User oldUser = usersList.getSelectedValue();
					User newUser = new User(usernameField.getText(), new String(passwordField.getPassword()), userRoleField.getText(), realNameField.getText());
					int lastSelectedIndex = usersList.getSelectedIndex();
					usersList.setModel(controllers.AdminController.btnSaveChanges(oldUser, newUser));
					usersList.setSelectedIndex(lastSelectedIndex);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(btnSaveChanges, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSaveChanges.setBounds(12, 137, 131, 43);
		userInfoPanel.add(btnSaveChanges);
		
		userRoleField = new JTextField();
		userRoleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(userRoleField, 
						"The user role is not editable, as editing it will render its database data useless. For more info contact the support team.",
						"Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		userRoleField.setEditable(false);
		userRoleField.setToolTipText("The user role is not editable, as editing it will render its database data useless.");
		userRoleField.setBounds(111, 69, 193, 21);
		userInfoPanel.add(userRoleField);
		userRoleField.setColumns(10);
		
		JButton btnAddNewUser = new JButton("Add a new user");
		btnAddNewUser.setBounds(362, 316, 125, 43);
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
		btnLogout.setBounds(553, 316, 125, 43);
		contentPane.add(btnLogout);
		
		btnClearSelection = new JButton("Clear selection");
		btnClearSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClearSelection.setVisible(false);
				informationLabel.setVisible(true);
				usersList.clearSelection();
			}
		});
		btnClearSelection.setFont(new Font("Dialog", Font.BOLD, 15));
		btnClearSelection.setBounds(362, 60, 316, 30);
		contentPane.add(btnClearSelection);
		btnClearSelection.setVisible(false);
		
		informationLabel = new JLabel("To edit the user's details select a username");
		informationLabel.setBounds(362, 60, 316, 30);
		contentPane.add(informationLabel);
		informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationLabel.setVisible(true);
	}
	
	public AdminFrame(String realName, User[] users) {
		this();
		welcomeLabel.setText("Welcome, " + realName + "!");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 338, 297);
		contentPane.add(scrollPane);
		
		usersList = new JList<User>(users);
		scrollPane.setViewportView(usersList);
		usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				userInfoPanel.setVisible(true);
				btnSaveChanges.setEnabled(false);
				
				if (usersList.getSelectedIndex() == -1) {
					userInfoPanel.setVisible(false);
					return;
				}
				
				User currentUser = usersList.getSelectedValue();
				usernameField.setText(currentUser.getUsername());
				realNameField.setText(currentUser.getRealName());
				userRoleField.setText(currentUser.getUserRole().toString());
				passwordField.setText(currentUser.getPassword());
				
				informationLabel.setVisible(false);
				btnClearSelection.setVisible(true);
			}
		});
	}
}
