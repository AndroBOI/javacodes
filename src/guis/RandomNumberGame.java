package guis;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class RandomNumberGame extends JFrame {
    boolean isRandomNumberGenerated = false;
    int randomNumber = 0;
    private Timer blinkingTimer;
    JTextField inputField;
    JLabel resultLabel;
    JButton generateNumberBtn;
    JButton submitBtn;

    JPanel panelTop;
    JPanel panelCenter;
    JPanel panelBottom;

    private static final String[] guessIsLow = {
            "Too low! Try a bigger number.",
            "Nope, that’s lower than the answer!",
            "You’re aiming too low, go higher!"
    };

    private static final String[] guessIsHigh = {
            "Too high! Try a smaller number.",
            "Whoa, that’s higher than it should be!",
            "Mataas na yan brad!"
    };

    private static int lowIndex = 0;
    private static int highIndex = 0;

    public static String getLowMessage() {
        String msg = guessIsLow[lowIndex];
        lowIndex = (lowIndex + 1) % guessIsLow.length;
        return msg;
    }

    public static String getHighMessage() {
        String msg = guessIsHigh[highIndex];
        highIndex = (highIndex + 1) % guessIsHigh.length;
        return msg;
    }

    RandomNumberGame() {
        setTitle("Random Number Guessing Game");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(34, 34, 34));


        panelTop = new JPanel();
        panelTop.setBackground(new Color(34, 34, 34));
        generateNumberBtn = styleButton("Generate Random Number", new Color(0, 123, 255));
        generateNumberBtn.addActionListener(e -> generateRandomNumber());
        panelTop.add(generateNumberBtn);
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        panelCenter = new JPanel();
        panelCenter.setBackground(new Color(34, 34, 34));
        resultLabel = new JLabel("Click 'Generate Random Number' to start!", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelCenter.add(resultLabel);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));



        panelBottom = new JPanel();
        panelBottom.setBackground(new Color(34, 34, 34));
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setBackground(new Color(50, 50, 50));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);

        submitBtn = styleButton("Submit", new Color(0, 123, 255));

        submitBtn.addActionListener(e -> {
            if (blinkingTimer != null && blinkingTimer.isRunning()) {
                blinkingTimer.stop();
                resultLabel.setText("Click 'Generate Random Number' to start!");
            }
            checkGuess();
        });

        panelBottom.add(inputField);
        panelBottom.add(submitBtn);
        panelBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        // Add panels to frame
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }
    private JButton styleButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
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


    private void generateRandomNumber() {
        if (blinkingTimer != null && blinkingTimer.isRunning()) {
            blinkingTimer.stop();
            resultLabel.setText("Click 'Generate Random Number' to start!");
        }
        randomNumber = (int) (Math.random() * 60) + 1;
        isRandomNumberGenerated = true;
        resultLabel.setText("Random number is generated! Enter your guess.");
        System.out.println("Generated Number (for testing): " + randomNumber);
    }

    private void checkGuess() {


        if (!isRandomNumberGenerated) {
            resultLabel.setText("Please generate a number first!");
            return;
        }

        String userInput = inputField.getText();
        int guess;

        try {
            guess = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number!");
            return;
        }

        if (guess < randomNumber) {
            resultLabel.setText(getLowMessage());
        } else if (guess > randomNumber) {
            resultLabel.setText(getHighMessage());
        } else {
            String result = "Congratulations! You guessed it!";


            Color[] rainbowColors = new Color[] {
                    Color.RED,
                    Color.ORANGE,
                    Color.YELLOW,
                    Color.GREEN,
                    Color.CYAN,
                    Color.BLUE,
                    new Color(148, 0, 211),
                    Color.MAGENTA
            };

            Random rand = new Random();


            if (blinkingTimer != null && blinkingTimer.isRunning()) {
                blinkingTimer.stop();
            }

            blinkingTimer = new Timer(100, null);
            blinkingTimer.addActionListener(e -> {
                StringBuilder html = new StringBuilder("<html><div style='font-size:22px; font-weight:bold;'>");
                for (char c : result.toCharArray()) {
                    if (c == ' ') {
                        html.append("&nbsp;");
                        continue;
                    }

                    Color color = rainbowColors[rand.nextInt(rainbowColors.length)];
                    String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
                    html.append("<span style='color:").append(hex).append("'>").append(c).append("</span>");
                }
                html.append("</div></html>");
                resultLabel.setText(html.toString());
            });

            blinkingTimer.start();

            isRandomNumberGenerated = false;
        }
    }

}
