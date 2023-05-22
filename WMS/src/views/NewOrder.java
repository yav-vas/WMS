package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewOrder extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public NewOrder() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Order:");
		lblNewLabel.setBounds(180, 10, 64, 22);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Full name:");
		lblNewLabel_1.setBounds(10, 37, 64, 29);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(10, 93, 45, 13);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(58, 42, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(58, 90, 96, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(369, 148, 39, 34);
		getContentPane().add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(10, 158, 45, 13);
		getContentPane().add(list);
		
		JLabel lblNewLabel_3 = new JLabel("Orders:");
		lblNewLabel_3.setBounds(10, 135, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("ORDER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(323, 41, 85, 21);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.setBounds(351, 242, 85, 21);
		getContentPane().add(btnNewButton_2);
	}
}
