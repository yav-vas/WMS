package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesRepresentativeFrame extends JFrame  {
	
	private JTextField txtOrders;
	
	public SalesRepresentativeFrame() {
		setTitle("Sales representative");
		setBounds(100, 100, 435, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewOrderFrame newOrderFrame = new NewOrderFrame();
				newOrderFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(180, 20, 234, 101);
		getContentPane().add(btnNewButton);
		
		txtOrders = new JTextField();
		txtOrders.setText("    Orders:");
		txtOrders.setBounds(10, 0, 116, 19);
		getContentPane().add(txtOrders);
		txtOrders.setColumns(10);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(133, 20, 17, 233);
		getContentPane().add(scrollBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 121, 233);
		getContentPane().add(scrollPane);
	}
}
