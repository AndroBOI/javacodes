package guis;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterGUI extends JFrame {

    JPanel panelTop;
    JPanel panelMid;
    JPanel panelBottom;
    JTextField inputField;
    JLabel resultLabel;

    TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(500, 250);

        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(28, 28, 28));



        panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelTop.setOpaque(false);
        inputField = new JTextField("32", 10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputLabel.setForeground(Color.WHITE);
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panelTop.add(inputLabel);
        panelTop.add(inputField);
        add(panelTop, BorderLayout.NORTH);


        panelMid = new JPanel();
        panelMid.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelMid.setOpaque(false);

        JButton btnCtoF = createFlatButton("C → F", new Color(0, 123, 255));
        JButton btnFtoC = createFlatButton("F → C",new Color(0, 123, 255));
        JButton btnClear = createFlatButton("Clear", new Color(0, 123, 255));


        panelMid.add(btnCtoF);
        panelMid.add(btnFtoC);
        panelMid.add(btnClear);
        panelMid.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        add(panelMid, BorderLayout.CENTER);


        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setOpaque(false);
        resultLabel = new JLabel("Result will appear here");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelBottom.add(resultLabel);
        panelBottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        add(panelBottom, BorderLayout.SOUTH);


        btnCtoF.addActionListener(e -> convertToF());
        btnFtoC.addActionListener(e -> convertToC());
        btnClear.addActionListener(e -> clear());

    }


    private JButton createFlatButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));


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

    private void convertToF() {
        try {
            double c = Double.parseDouble(inputField.getText());
            double f = c * 9 / 5 + 32;
            resultLabel.setText(String.format("%.2f °C → %.2f °F", c, f));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void convertToC() {
        try {
            double f = Double.parseDouble(inputField.getText());
            double c = (f - 32) * 5 / 9;
            resultLabel.setText(String.format("%.2f °F → %.2f °C", f, c));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void clear() {
        inputField.setText("");
        resultLabel.setText("Result will appear here");
    }

}
