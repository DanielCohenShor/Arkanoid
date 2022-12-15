import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class HeadIndicator implements Sprite {
    private Counter lives;
    private String levelName;

    /**
     * Constructor with configurable counter of the lives and level name.
     * @param lives - the lives counter
     * @param levelName - the level name
     */
    public HeadIndicator(Counter lives, String levelName) {
        this.lives = lives;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // create the text
        d.setColor(Color.black);
        d.drawText(20, 20, "Lives: " + this.lives.getValue(), 17);
        d.drawText(600, 20, "Level Name: " + levelName, 17);
    }

    @Override
    public void timePassed() {
        return;
    }
}
