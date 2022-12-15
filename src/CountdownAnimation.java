import biuoop.DrawSurface;
import java.awt.Color;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Constructor with configurable number of seconds, number to count down from and the game screen.
     * @param numOfSeconds - the nunmber of seconds for the countdown
     * @param countFrom - the number to start count down from
     * @param gameScreen - the screen of the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * the function draws the background of the game and shows the player the count down before the game starts.
     * @param d - the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(254, 238, 236));
        d.fillRectangle(0, 0, 800, 600);
        gameScreen.drawAllOn(d);
        double startTime = System.currentTimeMillis();
        double time = (startTime - this.numOfSeconds) / 1000;
        // show the number 3
        if (time >= 0 && time < 0.7) {
            d.drawText(380, 100, "3", 32);
        }
        // shows the number 2
        if (time >= 0.7 && time < 1.4) {
            d.drawText(380, 100, "2", 32);
        }
        // shows the number 1
        if (time >= 1.4 && time < 2) {
            d.drawText(380, 100, "1", 32);
        }
        // stopping the countdown
        if (time >= 2) {
            this.stop = true;
            return;
        }
    }

    /**
     * the function returns the stop variable.
     * @return the stop variable
     */
    public boolean shouldStop() {
        return this.stop;
    }
}