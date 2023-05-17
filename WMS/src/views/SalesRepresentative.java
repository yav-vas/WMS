package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class SalesRepresentative extends JFrame  {
	private JTextField txtOrders;
	public SalesRepresentative() {
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New Order");
		btnNewButton.setBounds(180, 20, 233, 193);
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
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
