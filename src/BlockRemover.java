/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor with configurable game and counter of the blocks.
     * @param game - the game
     * @param removedBlocks - the counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // removing the hit listener of the block that was hit
        beingHit.removeHitListener(this);
        // removes the block from the game
        beingHit.removeFromGame(game);
        // substracting the number of blocks in the game by one
        remainingBlocks.decrease(1);
    }
}