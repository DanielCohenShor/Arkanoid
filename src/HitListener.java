/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public interface HitListener {
    /**
     * the function is called whenever the beingHit object is hit and make changes in the game.
     * @param beingHit - the block that was hit
     * @param hitter - the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}