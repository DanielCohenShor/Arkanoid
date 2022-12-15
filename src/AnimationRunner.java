import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor with configurable gui anf frames per second for the animation.
     * @param gui - the gui
     * @param framesPerSecond - the number of frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * the function activates the given animation for an amount of time.
     * @param animation - the animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}