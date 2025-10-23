package guis;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class App extends JFrame {

    public App() {
        setTitle("App Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));

        // --- Create main panel with padding and spacing ---
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        mainPanel.setBackground(new Color(45, 45, 45));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnApp1 = styleButton("Temperature Converter", new Color(0, 123, 255));
        JButton btnApp2 = styleButton("Random Number Game", new Color(0, 123, 255));
        JButton btnApp3 = styleButton("Rock Paper Scissors", new Color(0, 123, 255));
        JButton btnApp4 = styleButton("Calculator", new Color(0, 123, 255));
        JButton btnApp5 = styleButton("Web App", new Color(0, 123, 255));

        btnApp1.addActionListener(e -> {
            TemperatureConverterGUI tempApp = new TemperatureConverterGUI();
            tempApp.setVisible(true);
        });

        btnApp2.addActionListener(e -> {
            RandomNumberGame randomGameApp = new RandomNumberGame();
            randomGameApp.setVisible(true);
        });

        btnApp3.addActionListener(e -> {
            RockPaperScissorsGUI rpsGame = new RockPaperScissorsGUI();
            rpsGame.setVisible(true);
        });


        btnApp5.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://seybing-webapp.vercel.app"));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open website!");
            }
        });

        btnApp4.addActionListener(e -> JOptionPane.showMessageDialog(this, "This app is not ready yet."));


        mainPanel.add(btnApp1);
        mainPanel.add(btnApp2);
        mainPanel.add(btnApp3);
        mainPanel.add(btnApp4);
        mainPanel.add(btnApp5);

        add(mainPanel);
    }

    private JButton styleButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App menu = new App();
            menu.setVisible(true);
        });
    }
}
