import javax.swing.*;
import java.awt.*;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * ScorePanel class:
 * A simple display to show the current score.
 */
public class ScorePanel extends JPanel {
    /**
     * Stores the current display of the score.
     */
    private JLabel scoreLabel;

    /**
     * Creates the labels to show for representing the score.
     */
    public ScorePanel() {
        JLabel scoreDescriptionLabel = new JLabel("Score: ");
        scoreDescriptionLabel.setFont(new Font("Arial",Font.BOLD,30));
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Arial",Font.BOLD,30));

        add(scoreDescriptionLabel);
        add(scoreLabel);
    }

    /**
     * Updates the display to show the new score.
     *
     * @param score New score to show.
     */
    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }
}
