/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor with configurable game and counter of the balls.
     * @param game - the game
     * @param removedBalls - the counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // removes the ball from the game
        hitter.removeFromGame(game);
        // substracting the number of balls in the game by one
        remainingBalls.decrease(1);
    }
}
