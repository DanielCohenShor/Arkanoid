import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor with configurable keyboard sensor, key and animtion.
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // calling that animation doOneFrame method
        this.animation.doOneFrame(d);
        // check if key button was pressed
        if (this.sensor.isPressed(this.key)) {
            this.stop = true;
            // check if he already been pressed
            if (!isAlreadyPressed) {
                return;
            }
            // notify that the key hasn't been pressed yet
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
