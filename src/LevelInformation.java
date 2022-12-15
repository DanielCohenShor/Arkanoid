import java.util.List;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public interface LevelInformation {
    /**
     * the function returns the number of balls in this level.
     * @return the number of balls in this level
     */
    int numberOfBalls();

    /**
     * the function updates the number of balls in this level.
     * @param numOfBalls
     */
    void setNumOfBalls(int numOfBalls);

    /**
     * the function creates the initial velocity of each ball.
     * @return list of velocities for the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * the function returns the paddle speed.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * the function returns the paddle width.
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * the function returns the level name that will be displayed at the top of the screen.
     * @return the level name
     */
    String levelName();

    /**
     * the function returns a sprite with the background of the level.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * the function creates the Blocks that make up this level.
     * @return blocks list
     */
    List<Block> blocks();

    /**
     * the function returns the number of blocks that need to be removes in order to win the level.
     * @return the number of blocks that need to be removes in order to win the level
     */
    int numberOfBlocksToRemove();

    /**
     * the function creates the location of the balls on the screen.
     * @return list with points for every ball
     */
    List<Point> getBallsLocation();
}