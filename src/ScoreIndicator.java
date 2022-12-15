import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private int maxScore;

    /**
     * Constructor with configurable counter of the score.
     * @param scoreCounter - the score counter
     * @param maxScore
     */
    public ScoreIndicator(Counter scoreCounter, int maxScore) {
        this.currentScore = scoreCounter;
        this.maxScore = maxScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //create the rectangle
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 30);
        // the user cleared all the blocks
        if (currentScore.getValue() == maxScore) {
            this.currentScore.increase(100);
        }
        // create the text
        d.setColor(Color.black);
        d.drawText(350, 20, "Score: " + this.currentScore.getValue(), 17);
    }

    @Override
    public void timePassed() {
        return;
    }
}
