package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Calculator extends JFrame {

    private JLabel result;
    private StringBuilder currentInput = new StringBuilder();

    public Calculator() {
        setTitle("Calculator");
        setSize(350, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(45, 45, 45));

        // === Result Panel ===
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setPreferredSize(new Dimension(350, 80));
        resultPanel.setBackground(new Color(30, 30, 30));

        result = new JLabel("0", SwingConstants.RIGHT);
        result.setFont(new Font("Arial", Font.BOLD, 36));
        result.setForeground(Color.WHITE);
        result.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resultPanel.add(result, BorderLayout.CENTER);

        // === Buttons Panel ===
        JPanel buttonsPanel = new JPanel(new BorderLayout(5, 5));
        buttonsPanel.setBackground(new Color(45, 45, 45));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel gridButtons = new JPanel(new GridLayout(4, 4, 8, 8));
        gridButtons.setBackground(new Color(45, 45, 45));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", ".", "+"
        };

        for (String label : buttonLabels) {
            Color baseColor;

            if (label.equals("C")) {
                baseColor = new Color(255, 80, 80); // Red
            } else if (label.matches("[+\\-*/]")) {
                baseColor = new Color(255, 165, 0); // Orange
            } else {
                baseColor = new Color(0, 123, 255); // Blue for numbers
            }

            JButton btn = styleButton(label, baseColor);
            btn.setFont(new Font("Arial", Font.BOLD, 24));

            btn.addActionListener(e -> handleButtonPress(label));
            gridButtons.add(btn);
        }

        // === Equals Button ===
        JButton equalsBtn = styleButton("=", new Color(0, 150, 0));
        equalsBtn.setFont(new Font("Arial", Font.BOLD, 24));
        equalsBtn.setPreferredSize(new Dimension(0, 60));
        equalsBtn.addActionListener(e -> calculateResult());

        buttonsPanel.add(gridButtons, BorderLayout.CENTER);
        buttonsPanel.add(equalsBtn, BorderLayout.SOUTH);

        add(resultPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
    }
    private void handleButtonPress(String label) {
        if (label.equals("C")) {
            currentInput.setLength(0);
            result.setText("0");
            return;
        }

        String current = currentInput.toString();

        // --- Prevent invalid operator placement ---
        if ("+-*/".contains(label)) {
            if (current.isEmpty()) {
                // Cannot start with +, *, /
                if (!label.equals("-")) return;
            } else {
                char lastChar = current.charAt(current.length() - 1);
                // Prevent double operators like ++, *-, etc.
                if ("+-*/".indexOf(lastChar) >= 0) return;
                // Prevent operator right after decimal
                if (lastChar == '.') return;
            }
        }

        // --- Prevent double decimals in the same number ---
        if (label.equals(".")) {
            int lastOperatorIndex = Math.max(
                    Math.max(current.lastIndexOf("+"), current.lastIndexOf("-")),
                    Math.max(current.lastIndexOf("*"), current.lastIndexOf("/"))
            );
            String lastNumber = current.substring(lastOperatorIndex + 1);
            if (lastNumber.contains(".")) return;

            // Automatically prepend a 0 if user types '.' first
            if (lastNumber.isEmpty()) {
                currentInput.append("0");
            }
        }

        currentInput.append(label);
        result.setText(currentInput.toString());
    }


    private void calculateResult() {
        try {
            double evalResult = evaluate(currentInput.toString());
            result.setText(removeTrailingZeros(evalResult));
            currentInput = new StringBuilder(result.getText());
        } catch (Exception e) {
            result.setText("Error");
            currentInput.setLength(0);
        }
    }

    // --- Simple Expression Evaluator ---
    private double evaluate(String expr) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> ops = new Stack<>();
        StringBuilder num = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {

                num.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                if (num.length() > 0) {
                    numbers.push(Double.parseDouble(num.toString()));
                    num.setLength(0);
                }

                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()));
                }
                ops.push(c);
            }
        }

        if (num.length() > 0) numbers.push(Double.parseDouble(num.toString()));

        while (!ops.isEmpty()) {
            numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }

    private double applyOp(char op, double b, double a) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b == 0 ? Double.NaN : a / b;
            default -> 0;
        };
    }

    private String removeTrailingZeros(double num) {
        if (num == (long) num)
            return String.format("%d", (long) num);
        else
            return String.format("%s", num);
    }

    private JButton styleButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));

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
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
