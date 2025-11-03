package guis;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    private JPanel cardPanel, gamePanel, losePanel, winPanel;
    private JButton rockBtn, paperBtn, scissorBtn, restartBtnLose, restartBtnWin;
    private JLabel statusLabel, scoreLabel, loseGifLabel, winLabel, loseLabel;
    private int playerScore = 0;
    private int compScore = 0;
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private final Random random = new Random();
    private final CardLayout cardLayout = new CardLayout();

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors");
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(450, 400);
        setLocationRelativeTo(null);


        cardPanel = new JPanel(cardLayout);
        add(cardPanel);


        gamePanel = new JPanel(new BorderLayout(0, 10));
        gamePanel.setBackground(new Color(34, 34, 34));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));


        JLabel title = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        gamePanel.add(title, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(34, 34, 34));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(new Color(45, 45, 45));
        scorePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        scoreLabel = new JLabel("Player: 0  |  Computer: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(255, 215, 0));
        scorePanel.add(scoreLabel);
        scorePanel.setMaximumSize(new Dimension(380, 50));

        centerPanel.add(scorePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));


        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(45, 45, 45));
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        statusLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setForeground(new Color(255, 255, 255));
        statusPanel.add(statusLabel);
        statusPanel.setMaximumSize(new Dimension(380, 90));

        centerPanel.add(statusPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(34, 34, 34));

        rockBtn = styleButton("Rock", new Color(0, 123, 255));
        paperBtn = styleButton("Paper", new Color(0, 123, 255));
        scissorBtn = styleButton("Scissors", new Color(0, 123, 255));

        rockBtn.addActionListener(this);
        paperBtn.addActionListener(this);
        scissorBtn.addActionListener(this);

        buttonPanel.add(rockBtn);
        buttonPanel.add(paperBtn);
        buttonPanel.add(scissorBtn);
        buttonPanel.setMaximumSize(new Dimension(450, 50));

        centerPanel.add(buttonPanel);

        gamePanel.add(centerPanel, BorderLayout.CENTER);


        cardPanel.add(gamePanel, "Game");


        losePanel = new JPanel(new BorderLayout());
        losePanel.setBackground(new Color(34, 34, 34));

        loseLabel = new JLabel("YOU LOSE!", SwingConstants.CENTER);
        loseLabel.setFont(new Font("Arial", Font.BOLD, 32));
        loseLabel.setForeground(Color.RED);
        losePanel.add(loseLabel, BorderLayout.NORTH);

        loseGifLabel = new JLabel("", SwingConstants.CENTER);
        losePanel.add(loseGifLabel, BorderLayout.CENTER);

        restartBtnLose = styleButton("Restart Game", new Color(0, 123, 255));
        restartBtnLose.addActionListener(e -> resetGame());
        JPanel loseBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loseBtnPanel.setBackground(new Color(34, 34, 34));
        loseBtnPanel.add(restartBtnLose);
        losePanel.add(loseBtnPanel, BorderLayout.SOUTH);

        cardPanel.add(losePanel, "Lose");


        winPanel = new JPanel(new BorderLayout());
        winPanel.setBackground(new Color(34, 34, 34));

        winLabel = new JLabel("YOU WIN!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 32));
        winLabel.setForeground(new Color(0, 200, 0));
        winPanel.add(winLabel, BorderLayout.CENTER);

        restartBtnWin = styleButton("Restart Game", new Color(0, 123, 255));
        restartBtnWin.addActionListener(e -> resetGame());
        JPanel winBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winBtnPanel.setBackground(new Color(34, 34, 34));
        winBtnPanel.add(restartBtnWin);
        winPanel.add(winBtnPanel, BorderLayout.SOUTH);

        cardPanel.add(winPanel, "Win");


        cardLayout.show(cardPanel, "Game");

        setVisible(true);
    }

    private JButton styleButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));


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

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        String compChoice = choices[random.nextInt(3)];
        String result = getResult(playerChoice, compChoice);


        String wrappedText = "<html><center>"
                + "You: " + playerChoice + "<br>"
                + "Computer: " + compChoice + "<br>"
                + "<span style='color: " + getResultColor(result) + "; font-size: 16px;'>"
                + result + "</span>"
                + "</center></html>";

        statusLabel.setText(wrappedText);
        scoreLabel.setText("Player: " + playerScore + "  |  Computer: " + compScore);

        if (playerScore == 3) {
            showWinScreen();
        } else if (compScore == 3) {
            showLoseScreen();
        }
    }

    private String getResultColor(String result) {
        if (result.equals("You Win!")) return "#00FF00";
        if (result.equals("You Lose!")) return "#FF4444";
        return "#FFFF00";
    }

    private String getResult(String player, String comp) {
        if (player.equals(comp)) return "Draw!";
        if ((player.equals("Rock") && comp.equals("Scissors")) ||
                (player.equals("Paper") && comp.equals("Rock")) ||
                (player.equals("Scissors") && comp.equals("Paper"))) {
            playerScore++;
            return "You Win!";
        } else {
            compScore++;
            return "You Lose!";
        }
    }

    private void showLoseScreen() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/guis/resources/aray-ko.gif"));
            loseGifLabel.setIcon(icon);
        } catch (Exception ex) {
            loseGifLabel.setText("You Lost!");
            loseGifLabel.setFont(new Font("Arial", Font.BOLD, 32));
            loseGifLabel.setForeground(Color.RED);
            System.err.println("Could not load GIF: " + ex.getMessage());
        }

        cardLayout.show(cardPanel, "Lose");
        playSound("/guis/resources/aray-ko.wav");
    }

    private void showWinScreen() {
        cardLayout.show(cardPanel, "Win");
    }

    private void resetGame() {
        playerScore = 0;
        compScore = 0;
        statusLabel.setText("Make your move!");
        scoreLabel.setText("Player: 0  |  Computer: 0");
        cardLayout.show(cardPanel, "Game");
    }

    private void playSound(String soundPath) {
        new Thread(() -> {
            try {
                InputStream audioSrc = getClass().getResourceAsStream(soundPath);
                if (audioSrc == null) {
                    System.err.println("Sound not found: " + soundPath);
                    return;
                }
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                System.err.println("Error playing sound: " + e.getMessage());
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsGUI());
    }
}