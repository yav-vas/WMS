package views;

import javax.swing.*;
import java.awt.*;

public class Dealer1 extends JFrame{
    private JScrollPane scrollPane;
    private JScrollBar scrollBar;
    private JButton newOrderButton;
    private JLabel dealersText;

    public Dealer1() {
        super("Dealer");

        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        newOrderButton = new JButton();
        
        dealersText = new JLabel();
        dealersText.setText("Current orders");

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(250, 200));
        scrollBar = new JScrollBar(JScrollBar.VERTICAL);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dealersText)
                        .addComponent(scrollPane))
                .addComponent(scrollBar)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(newOrderButton))
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(dealersText)
                        .addComponent(scrollPane))
                .addComponent(scrollBar)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(newOrderButton))
        );

        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Dealer1 myFrame = new Dealer1();
    }
}
