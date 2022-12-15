import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int flag;
    private Counter score;
    private GUI gui;

    /**
     * Constructor with configurable keyboard sensor, flag, score and gui.
     * @param k - the keyboard sensor
     * @param flag - flag if the user lost or won
     * @param score - the score
     * @param gui - the gui
     */
    public EndScreen(KeyboardSensor k, int flag, Counter score, GUI gui) {
        this.keyboard = k;
        this.stop = false;
        this.flag = flag;
        this.score = score;
        this.gui = gui;
    }

    /**
     * the function create the end game screen according to the game.
     * @param d - the surface
     */
    public void doOneFrame(DrawSurface d) {
        // the user won the game
        if (flag == 1) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
                this.gui.close();
            }
            // the user lost
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
                this.gui.close();
            }
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