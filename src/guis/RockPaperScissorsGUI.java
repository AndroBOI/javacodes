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
    private JLabel statusLabel, scoreLabel,  loseGifLabel, winLabel;
    private int playerScore = 0;
    private int compScore = 0;
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private final Random random = new Random();
    private final CardLayout cardLayout = new CardLayout();

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Adjusted size slightly to better accommodate centered content
        setSize(450, 450);
        setLocationRelativeTo(null);

        // --- Main card layout ---
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);

        // ========== GAME PANEL ==========
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(new Color(34, 34, 34));

        // Title at top
        JLabel title = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        gamePanel.add(title, BorderLayout.NORTH);

        // ******* Alignment FIX: Use an outer panel with FlowLayout to center the inner content *******
        JPanel outerCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerCenterPanel.setBackground(new Color(34, 34, 34));

        // Center panel holds buttons + result text (This is the vertically stacked content)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(34, 34, 34));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // vertical stack

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // center horizontally
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

        // Add buttons to center panel
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // small space between buttons and status

        // Status label below buttons
        statusLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 22));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Important for BoxLayout
        centerPanel.add(statusLabel);

        // Add the vertical stack panel to the FlowLayout panel
        outerCenterPanel.add(centerPanel);

        // Add the centering panel to the game panel
        gamePanel.add(outerCenterPanel, BorderLayout.CENTER);
        // ******* Alignment FIX END *******

        // Score at very bottom
        scoreLabel = new JLabel("Player: 0  |  Computer: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        gamePanel.add(scoreLabel, BorderLayout.SOUTH);

        // Add to card panel
        cardPanel.add(gamePanel, "Game");

        // ========== LOSE PANEL ==========
        losePanel = new JPanel(new BorderLayout());
        losePanel.setBackground(new Color(34, 34, 34));
        loseGifLabel = new JLabel("", SwingConstants.CENTER);
        losePanel.add(loseGifLabel, BorderLayout.CENTER);

        restartBtnLose = styleButton("Restart Game", new Color(0, 123, 255));
        restartBtnLose.addActionListener(e -> resetGame());
        losePanel.add(restartBtnLose, BorderLayout.SOUTH);

        cardPanel.add(losePanel, "Lose");

        // ========== WIN PANEL ==========
        winPanel = new JPanel(new BorderLayout());
        winPanel.setBackground(new Color(34, 34, 34));

        winLabel = new JLabel("YOU WIN!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 32));
        winLabel.setForeground(new Color(0, 150, 0));
        winPanel.add(winLabel, BorderLayout.CENTER);

        restartBtnWin =styleButton("Restart Game", new Color(0, 123, 255));

        restartBtnWin.addActionListener(e -> resetGame());
        winPanel.add(restartBtnWin, BorderLayout.SOUTH);

        cardPanel.add(winPanel, "Win");

        // show game first
        cardLayout.show(cardPanel, "Game");

        setVisible(true);
    }


    private JButton styleButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        String compChoice = choices[random.nextInt(3)];
        String result = getResult(playerChoice, compChoice);

        // Wrap result text using HTML so it doesn't stretch
        String wrappedText = "<html><center>"
                + "You: " + playerChoice + "<br>"
                + "Computer: " + compChoice + "<br>"
                + "→ " + result
                + "</center></html>";

        statusLabel.setText(wrappedText);
        scoreLabel.setText("Player: " + playerScore + "  |  Computer: " + compScore);

        if (playerScore == 3) {
            showWinScreen();
        } else if (compScore == 3) {
            showLoseScreen();
        }
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
            // NOTE: This will fail if the resource is not correctly placed in the package
            ImageIcon icon = new ImageIcon(getClass().getResource("/guis/resources/aray-ko.gif"));
            loseGifLabel.setIcon(icon);
        } catch (Exception ex) {
            loseGifLabel.setText("You Lost!");
            loseGifLabel.setFont(new Font("Arial", Font.BOLD, 32));
            loseGifLabel.setForeground(Color.RED);
            System.err.println("Could not load GIF: " + ex.getMessage());
        }

        cardLayout.show(cardPanel, "Lose");
        // NOTE: This will fail if the resource is not correctly placed in the package
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
                // e.printStackTrace(); // Keep this commented out unless debugging
                System.err.println("Error playing sound: " + e.getMessage());
            }
        }).start();
    }

    // Optional main method for quick testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsGUI());
    }
}