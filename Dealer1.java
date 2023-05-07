package Sklad;

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

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(250, 200));  // Set the width of the scrollpane
        scrollBar = new JScrollBar(JScrollBar.VERTICAL);  // Set the scrollbar orientation to vertical
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dealersText)
                        .addComponent(scrollPane))
                .addComponent(scrollBar)  // Add scrollbar to the right of scrollpane
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(newOrderButton))
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(dealersText)
                        .addComponent(scrollPane))
                .addComponent(scrollBar)  // Add scrollbar to the right of scrollpane
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
