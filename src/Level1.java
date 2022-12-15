import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Level1 implements LevelInformation {
    static final int NUMBER_OF_ROWS = 7, BLOCK_WIDTH = 50, BLOCK_HEIGHT = 20, NUMBER_OF_BLOCKS = 57;
    private List<Color> brickRowsColor;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    private List<Point> ballsLocation;
    private int numOfBalls;

    /**
     * default constructor.
     */
    public Level1() {
        brickRowsColor = new ArrayList<Color>();
        updateColorList();
        this.blockList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
        this.ballsLocation = new ArrayList<Point>();
        this.numOfBalls = 2;
    }

    /**
     * the function creates list of colors for the blocks rows.
     */
    public void updateColorList() {
        int r = 255, g = 140, b = 202;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            Color randomColor = new Color(r, g, b);
            brickRowsColor.add(randomColor);
            g -= 15;
            b -= 15;
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
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public List<Block> blocks() {
        int counterOfLines = 0, counterOfBricks = 12, startX = 740, startY = 200;
        Point rectPoint = new Point(startX, startY);
        // create the blocks according to the number of rows
        while (counterOfLines < (NUMBER_OF_ROWS - 1)) {
            for (int i = 0; i < counterOfBricks; i++) {
                Rectangle rect = new Rectangle(rectPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, brickRowsColor.get(counterOfLines));
                blockList.add(block);
                rectPoint.setX(rectPoint.getX() - BLOCK_WIDTH);
            }
            counterOfBricks--;
            counterOfLines++;
            startY += BLOCK_HEIGHT;
            rectPoint = new Point(startX, startY);
        }
        return blockList;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        double startVelocity = 4;
        // create the velocity of the balls in the game
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(new Velocity(startVelocity, startVelocity));
            startVelocity += 2;
        }
        return velocityList;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Point> getBallsLocation() {
        int ballStartX = 420, ballStartY = 510;
        // creating the balls location on the screen for every ball in the game
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Point ballPoint = new Point(ballStartX, ballStartY);
            this.ballsLocation.add(ballPoint);
            if (ballStartX - 10 > 0) {
                ballStartX -= 10;
            }
            if (ballStartY - 10 > 0) {
                ballStartY -= 10;
            }
        }
        return this.ballsLocation;
    }
}
