import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Level2 implements LevelInformation {
    static final int BLOCK_WIDTH = 30, BLOCK_HEIGHT = 30;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    private List<Point> ballsLocation;
    private int numOfBalls;
    /**
     * default constructor.
     */
    public Level2() {
        this.blockList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
        this.ballsLocation = new ArrayList<Point>();
        this.numOfBalls = 1;
    }

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public void setNumOfBalls(int numOfBalls) {
        this.numOfBalls = numOfBalls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }
    @Override
    public List<Block> blocks() {
        // create the block
        Point rectPoint = new Point(420, 200);
        Rectangle rect = new Rectangle(rectPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
        Block block = new Block(rect, new Color(255, 140, 202));
        blockList.add(block);
        return blockList;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        double startDX = 0, startDY = 10;
        // create the velocity of the balls in the game
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(new Velocity(startDX, startDY));
            startDX += 2;
        }
        return velocityList;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Point> getBallsLocation() {
        int ballStartX = 435, ballStartY = 570;
        // creating the balls location on the screen for every ball in the game
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Point ballPoint = new Point(ballStartX, ballStartY);
            this.ballsLocation.add(ballPoint);
            if (ballStartY - 10 > 0) {
                ballStartY -= 10;
            }
        }
        return this.ballsLocation;
    }
}
