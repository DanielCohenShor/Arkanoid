import biuoop.DrawSurface;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public interface Sprite {
    /**
     * the function draws the sprite to the screen.
     * @param d - the given surface
     */
    void drawOn(DrawSurface d);

    /**
     * the function notify the sprite that time has passed.
     */
    void timePassed();
}