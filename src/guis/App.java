package guis;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {

    public App() {
        setTitle("App Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window
        setLayout(new GridLayout(5, 1, 10, 10));


        JButton btnTempConverter = new JButton("Temperature Converter");
        btnTempConverter.addActionListener(e -> {
            TemperatureConverterGUI tempApp = new TemperatureConverterGUI();
            tempApp.setVisible(true);
        });


        JButton btnApp2 = new JButton("Random Number Game");
        btnApp2.addActionListener(e -> {
            RandomNumberGame randomGameApp = new RandomNumberGame();
            randomGameApp.setVisible(true);
        });

        JButton btnApp3 = new JButton("Rock Paper Scissors");
        btnApp3.addActionListener(e -> {
            RockPaperScissorsGUI rpsGame = new RockPaperScissorsGUI();
            rpsGame.setVisible(true);
        });

        JButton btnApp4 = new JButton("App 4 (Coming Soon)");
        btnApp4.addActionListener(e -> JOptionPane.showMessageDialog(this, "This app is not ready yet."));


        JButton btnApp5 = new JButton("App 5 (Coming Soon)");
        btnApp5.addActionListener(e -> JOptionPane.showMessageDialog(this, "This app is not ready yet."));


        add(btnTempConverter);
        add(btnApp2);
        add(btnApp3);
        add(btnApp4);
        add(btnApp5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App menu = new App();
            menu.setVisible(true);
        });
    }
}
