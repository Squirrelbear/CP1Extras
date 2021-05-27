import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * StatusPanel class:
 * Shows the current status of the game for current score,
 * best score, and number of crashes. Also provides
 *  a quit button to exit the game.
 */
public class StatusPanel extends JPanel implements ActionListener {
    /**
     * Label showing the current score number.
     */
    private JLabel scoreLabel;
    /**
     * Label showing the max score during the session.
     */
    private JLabel maxScoreLabel;
    /**
     * Label showing the number of crashes that occurred.
     */
    private JLabel crashesLabel;
    /**
     * Best score used for comparison when the crashes changes.
     */
    private int bestScore;
    /**
     * Current score used for comparison when the crashes changes.
     */
    private int currentScore;
    /**
     * Reference to the quit button for exiting.
     */
    private JButton quitButton;

    /**
     * Configures the panel to display score, best score, crashes, and the quit button.
     */
    public StatusPanel() {
        setPreferredSize(new Dimension(260, GamePanel.PANEL_HEIGHT));
        bestScore = 0;

        JLabel scoreDescriptionLabel = createLabel("SCORE");
        scoreLabel = createLabel("0");
        JLabel maxScoreDescriptionLabel = createLabel("BEST SCORE");
        maxScoreLabel = createLabel("0");
        JLabel crashesDescriptionLabel = createLabel("CRASHES");
        crashesLabel = createLabel("0");
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        quitButton.setFont(new Font("Arial", Font.BOLD, 40));

        setLayout(new GridBagLayout());
        JPanel centredPanel = new JPanel(new GridLayout(7,1));

        centredPanel.add(scoreDescriptionLabel);
        centredPanel.add(scoreLabel);
        centredPanel.add(maxScoreDescriptionLabel);
        centredPanel.add(maxScoreLabel);
        centredPanel.add(crashesDescriptionLabel);
        centredPanel.add(crashesLabel);
        centredPanel.add(quitButton);

        add(centredPanel);
    }

    /**
     * Updates the score label to display the new score.
     *
     * @param newScore The new score to display.
     */
    public void setScore(int newScore) {
        currentScore = newScore;
        scoreLabel.setText(String.valueOf(currentScore));
    }

    /**
     * Updates the best score if the current score is better.
     * Then updates the crashes label.
     *
     * @param newCrashes Number of crashes to display.
     */
    public void setCrashes(int newCrashes) {
        if(currentScore > bestScore) {
            bestScore = currentScore;
            maxScoreLabel.setText(String.valueOf(bestScore));
        }
        crashesLabel.setText(String.valueOf(newCrashes));
    }

    /**
     * Factory method to create JLabels with the same properties.
     *
     * @param message String to display in the JLabel.
     * @return Reference to the newly created JLabel.
     */
    private JLabel createLabel(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setPreferredSize(new Dimension(260,50));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    /**
     * Called when the quit button is pressed to exit the game.
     *
     * @param e Information about the event that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
