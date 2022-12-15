import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Level4 implements LevelInformation {
    static final int NUMBER_OF_BLOCKS = 105, BLOCK_WIDTH = 52, BLOCK_HEIGHT = 20, NUMBER_OF_ROWS = 7;
    private List<Color> brickRowsColor;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    private List<Point> ballsLocation;
    private int numOfBalls;
    /**
     * default constructor.
     */
    public Level4() {
        brickRowsColor = new ArrayList<Color>();
        updateColorList();
        this.blockList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
        this.ballsLocation = new ArrayList<Point>();
        this.numOfBalls = 3;
    }

    /**
     * the function creates list of colors for the blocks rows.
     */
    public void updateColorList() {
        int r = 255, g = 140, b = 202;
        for (int i = 1; i <= NUMBER_OF_BLOCKS; i++) {
            if (i % 15 != 0) {
                Color randomColor = new Color(r, g, b);
                brickRowsColor.add(randomColor);
            } else {
                Color randomColor = new Color(r, g, b);
                brickRowsColor.add(randomColor);
                g -= 15;
                b -= 15;
            }
        }
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
        return NUMBER_OF_BLOCKS;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public List<Block> blocks() {
        int counterOfLines = 0, startX = 737, startY = 200, count = 0;
        Point rectPoint = new Point(startX, startY);
        // create the blocks according to the number of rows
        while (counterOfLines < NUMBER_OF_ROWS) {
            for (int i = count; i < (count + 15); i++) {
                Rectangle rect = new Rectangle(rectPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, brickRowsColor.get(i));
                blockList.add(block);
                rectPoint.setX(rectPoint.getX() - BLOCK_WIDTH);
            }
            count += 15;
            counterOfLines++;
            startY += BLOCK_HEIGHT;
            rectPoint = new Point(startX, startY);
        }
        return blockList;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities() {
        double startVelocity = 6;
        // create the velocity of the balls in the game
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(new Velocity(startVelocity, startVelocity));
        }
        return velocityList;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Point> getBallsLocation() {
        int ballFirstStartX = 400, ballFirstStartY = 400, ballLastStartX = 500;
        // creating the balls location on the screen for every ball in the game
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Point ballPoint = new Point(ballFirstStartX, ballFirstStartY);
            this.ballsLocation.add(ballPoint);
            ballFirstStartX += 5;
            ballFirstStartY += 10;
        }
        for (int i = this.numberOfBalls() / 2; i < this.numberOfBalls(); i++) {
            Point ballPoint = new Point(ballLastStartX, ballFirstStartY);
            this.ballsLocation.add(ballPoint);
            ballLastStartX -= 5;
            ballFirstStartY -= 10;
        }
        return this.ballsLocation;
    }
}
