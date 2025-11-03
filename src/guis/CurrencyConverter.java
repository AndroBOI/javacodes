package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame {
    CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(500, 350);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(45, 45, 45));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

        JLabel labelInput = new JLabel("Philippine Peso");
        labelInput.setForeground(Color.WHITE);
        labelInput.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField input = new JTextField("23");
        input.setPreferredSize(new Dimension(120, 30));
        input.setForeground(Color.WHITE);
        input.setBackground(new Color(60, 60, 60));
        input.setCaretColor(Color.WHITE);
        input.setFont(new Font("Arial", Font.PLAIN, 14));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        topPanel.add(labelInput);
        topPanel.add(input);


        JPanel midPanel = new JPanel();
        midPanel.setBackground(new Color(45, 45, 45));
        midPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JButton convertBtn = createFlatButton("Convert", new Color(0, 123, 255));
        convertBtn.setPreferredSize(new Dimension(200, 40));
        midPanel.add(convertBtn);


        JPanel bottomMainPanel = new JPanel();
        bottomMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        bottomMainPanel.setBackground(new Color(45, 45, 45));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(45, 45, 45));

        JLabel resultUsd = new JLabel("U.S. Dollar: ");
        resultUsd.setForeground(Color.WHITE);
        resultUsd.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel resultCad = new JLabel("Canadian Dollar: ");
        resultCad.setForeground(Color.WHITE);
        resultCad.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel resultSgd = new JLabel("Singapore Dollar: ");
        resultSgd.setForeground(Color.WHITE);
        resultSgd.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel resultSar = new JLabel("Saudi Riyal: ");
        resultSar.setForeground(Color.WHITE);
        resultSar.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel resultEur = new JLabel("Euro: ");
        resultEur.setForeground(Color.WHITE);
        resultEur.setFont(new Font("Arial", Font.BOLD, 15));

        bottomPanel.add(resultUsd);
        bottomPanel.add(Box.createVerticalStrut(8));
        bottomPanel.add(resultCad);
        bottomPanel.add(Box.createVerticalStrut(8));
        bottomPanel.add(resultSgd);
        bottomPanel.add(Box.createVerticalStrut(8));
        bottomPanel.add(resultSar);
        bottomPanel.add(Box.createVerticalStrut(8));
        bottomPanel.add(resultEur);

        bottomMainPanel.add(bottomPanel);

        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomMainPanel, BorderLayout.SOUTH);


        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = input.getText().trim();

                if (inputValue.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double inputNum = Double.parseDouble(inputValue);

                    double usdValue = inputNum / 58.00;
                    double cadValue = inputNum / 42.5;
                    double sgdValue = inputNum / 43.00;
                    double sarValue = inputNum / 15.4;
                    double eurValue = inputNum / 63;
                    resultUsd.setText("U.S. Dollar: $" + String.format("%.2f", usdValue));
                    resultCad.setText("Canadian Dollar: $" + String.format("%.2f", cadValue));
                    resultSgd.setText("Singapore Dollar: $" + String.format("%.2f", sgdValue));
                    resultSar.setText("Saudi Riyal: ﷼" + String.format("%.2f", sarValue));
                    resultEur.setText("Euro: €" + String.format("%.2f", eurValue));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JButton createFlatButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


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
        CurrencyConverter app = new CurrencyConverter();
        app.setVisible(true);
    }
}