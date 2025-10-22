package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Test extends JFrame {

    Test() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,500);
        setTitle("Test Card");
        setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel();
        cardPanel.setSize(250, 50);
        JPanel buttonPanel = new JPanel();

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout());
        JLabel firstPanelTitle = new JLabel("This is First Panel");
        firstPanelTitle.setForeground(Color.WHITE);
        firstPanel.add(firstPanelTitle);
        firstPanel.setBackground(Color.blue);


        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new FlowLayout());
        JLabel secondPanelTitle = new JLabel("This is Second Panel");
        secondPanelTitle.setForeground(Color.WHITE);
        secondPanel.add(secondPanelTitle);
        secondPanel.setBackground(Color.RED);


        mainPanel.add(firstPanel, "First");
        mainPanel.add(secondPanel, "Second");

        JButton nextBtn = new JButton("Next Page");
        buttonPanel.add(nextBtn);


        cardPanel.add(mainPanel);


        add(cardPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);


        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.next(cardPanel);

            }
        });






    }
    public static void main(String[] args) {
        Test app = new Test();
        app.setVisible(true);
    }
}