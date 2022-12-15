import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor with configurable keyboard sensor.
     * @param k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * the function creates the pause screen and checks when to remove it.
     * @param d - the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        // remove the pause screen
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
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