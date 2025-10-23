package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {

    Test() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setTitle("CardLayout Example");
        setLayout(new BorderLayout());
        UIManager.put("Label.foreground", Color.WHITE);

        // Create CardLayout and main panel
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // === Panels ===
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.BLUE);
        homePanel.add(new JLabel("🏠 This is the Home panel"));

        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.RED);
        settingsPanel.add(new JLabel("⚙️ This is the Settings panel"));

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.GREEN);
        profilePanel.add(new JLabel("👤 This is the Profile panel"));

        // Add panels to main panel with "names"
        mainPanel.add(homePanel, "Home");
        mainPanel.add(settingsPanel, "Settings");
        mainPanel.add(profilePanel, "Profile");

        // === Buttons ===
        JPanel buttonPanel = new JPanel();
        JButton homeBtn = new JButton("Home");
        JButton settingsBtn = new JButton("Settings");
        JButton profileBtn = new JButton("Profile");

        buttonPanel.add(homeBtn);
        buttonPanel.add(settingsBtn);
        buttonPanel.add(profileBtn);

        // === Add panels to frame ===
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // === Button actions ===
        homeBtn.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        settingsBtn.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));
        profileBtn.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
    }

    public static void main(String[] args) {
        Test app = new Test();
        app.setVisible(true);
    }
}
