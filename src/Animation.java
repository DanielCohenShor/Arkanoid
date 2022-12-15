import biuoop.DrawSurface;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */

public interface Animation {
    /**
     * the function creates the animation and activates it on the surface.
     * @param d - the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * the function returns if the animation should stop.
     * @return if the animation should stop
     */
    boolean shouldStop();
}