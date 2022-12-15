/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     *  Constructor with configurable counter of the score.
     * @param scoreCounter - the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * the function increases the counter of the score by 5 every time a block is hit.
     * @param beingHit - the block that was hit
     * @param hitter - the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}