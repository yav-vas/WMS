package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class OperatorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblWelcome;

	/**
	 * Create the frame.
	 */
	public OperatorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrP_Op_Client = new JScrollPane();
		scrP_Op_Client.setBounds(10, 36, 167, 214);
		contentPane.add(scrP_Op_Client);
		
		JList list = new JList();
		list.setVisibleRowCount(15);
		scrP_Op_Client.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Client");
		scrP_Op_Client.setColumnHeaderView(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Unassigned orders:");
		lblNewLabel.setBounds(10, 11, 165, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrP_Op_Prod = new JScrollPane();
		scrP_Op_Prod.setBounds(216, 67, 237, 98);
		contentPane.add(scrP_Op_Prod);
		
		JLabel lblNewLabel_3 = new JLabel("Products");
		scrP_Op_Prod.setColumnHeaderView(lblNewLabel_3);
		
		JList list_1 = new JList();
		scrP_Op_Prod.setViewportView(list_1);
		
		JButton btnNewButton = new JButton("Look assigned orders");
		btnNewButton.setBounds(216, 33, 237, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(269, 176, 184, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(216, 184, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ivan Ivanow", "Pesho", "Gosho", "Nikola"}));
		comboBox.setBounds(216, 232, 155, 18);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Driver:");
		lblNewLabel_4.setBounds(216, 215, 167, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setToolTipText("");
		btnNewButton_1.setBounds(381, 225, 72, 32);
		contentPane.add(btnNewButton_1);
		
		lblWelcome = new JLabel("Welcome, realName!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(216, 10, 237, 17);
		contentPane.add(lblWelcome);
	}
	
	public OperatorFrame(String realName) {
		this();
		lblWelcome.setText("Welcome, " + realName);
	}
}