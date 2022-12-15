import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import biuoop.KeyboardSensor;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class GameLevel implements Animation {
    static final int WIDTH = 800, HEIGHT = 600, BALL_SIZE = 5, PADDLE_START_X = 400, PADDLE_START_Y = 580,
            PADDLE_HEIGHT = 10, CANVAS_BLOCKS_WIDTH = 10;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private BlockRemover removingBlocks;
    private Counter remainingBalls;
    private BallRemover removingBalls;
    private Counter score;
    private ScoreTrackingListener trackScore;
    private ScoreIndicator scoreInd;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInf;

    /**
     * default constructor that sets the game variables.
     * @param levelInf
     * @param ks
     * @param  ar
     * @param gui
     * @param currScore
     */
    public GameLevel(LevelInformation levelInf, KeyboardSensor ks, AnimationRunner ar, GUI gui, Counter currScore) {
        this.levelInf = levelInf;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        remainingBlocks = new Counter();
        remainingBlocks.increase(levelInf.numberOfBlocksToRemove());
        removingBlocks = new BlockRemover(this, remainingBlocks);
        remainingBalls = new Counter();
        remainingBalls.increase(levelInf.numberOfBalls());
        removingBalls = new BallRemover(this, remainingBalls);
        score = currScore;
        trackScore = new ScoreTrackingListener(score);
        int maxScore = levelInf.numberOfBlocksToRemove() * 5;
        scoreInd = new ScoreIndicator(score, maxScore);
        sprites.addSprite(scoreInd);
        this.gui = gui;
        this.keyboard = ks;
        this.runner = ar;
    }

    /**
     * the function returns the sprites collection of the game.
     * @return the sprites collection of the game
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * the function adds collidable objects to the collidable objects list.
     * @param c - the given collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * the function adds sprites objects to the sprites objects list.
     * @param s - the fiven sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * the function removes collidable objects from the collidable objects list.
     * @param c - the given collidable object
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * the function removes sprites objects from the sprites objects list.
     * @param s - the fiven sprite object
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //createBalls();
        // creating the paddle
        Point startPaddle = new Point(PADDLE_START_X, PADDLE_START_Y);
        Rectangle paddleRect = new Rectangle(startPaddle, levelInf.paddleWidth(), PADDLE_HEIGHT);
        Paddle paddle = new Paddle(paddleRect, gui, WIDTH);
        paddle.addToGame(this);
        //creating the blocks
        createBlocks();
    }

    /**
     * the function create the balls on the screen.
     */
    public void createBalls() {
        List<Point> ballLocation = levelInf.getBallsLocation();
        for (int i = 0; i < levelInf.numberOfBalls(); i++) {
            Ball ball = new Ball(ballLocation.get(i), BALL_SIZE, Color.MAGENTA);
            ball.addToGame(this);
            ball.setGameE(environment);
            ball.setVelocity(levelInf.initialBallVelocities().get(i));
        }
    }

    /**
     * the function creates the rows of the blocks on the screen.
     */
    public void createBlocks() {
        createCanvasBlocks();
        for (int i = 0; i < levelInf.numberOfBlocksToRemove(); i++) {
            levelInf.blocks().get(i).addToGame(this);
            levelInf.blocks().get(i).addHitListener(removingBlocks);
            levelInf.blocks().get(i).addHitListener(trackScore);
        }
    }

    /**
     * the function create the canvas blocks according to the canvas width and height.
     */
    public void createCanvasBlocks() {
        // left block
        Point leftRectPoint = new Point(0, 30);
        Rectangle leftRect = new Rectangle(leftRectPoint, CANVAS_BLOCKS_WIDTH, HEIGHT);
        Block leftBlock = new Block(leftRect, new Color(12, 207, 191));
        leftBlock.addToGame(this);
        // right block
        Point rightRectPoint = new Point(790, 30);
        Rectangle rightRect = new Rectangle(rightRectPoint, CANVAS_BLOCKS_WIDTH, HEIGHT);
        Block rightBlock = new Block(rightRect, new Color(12, 207, 191));
        rightBlock.addToGame(this);
        // upper block
        Point upperRectPoint = new Point(0, 30);
        Rectangle upperRect = new Rectangle(upperRectPoint, WIDTH, CANVAS_BLOCKS_WIDTH);
        Block upperBlock = new Block(upperRect, new Color(12, 207, 191));
        upperBlock.addToGame(this);
        // death Region block
        Point deathRegionRectPoint = new Point(0, 600);
        Rectangle deathRegionRect = new Rectangle(deathRegionRectPoint, WIDTH, CANVAS_BLOCKS_WIDTH);
        Block deathRegionBlock = new Block(deathRegionRect, new Color(12, 207, 191));
        deathRegionBlock.addToGame(this);
        deathRegionBlock.addHitListener(removingBalls);
    }

    /**
     * the function creates the level on the screen.
     * @param d - the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(254, 238, 236));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.sprites.drawAllOn(d);
        // the user ended the level
        if (remainingBlocks.getValue() == 0) {
            this.running = false;
        }
        // creating the pause screen
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P") || this.keyboard.isPressed("פ")) {
            KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard));
            this.runner.run(key);
        }
        this.sprites.notifyAllTimePassed();
        // the user lost the game
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * the function returns the stop variable.
     * @return the stop variable
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the function returns the number of remaining balls in the game.
     * @return the number of the remaining balls in the game
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * the function updates the number of remaining balls.
     * @param remainingBalls
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * the function updates the ball remover.
     * @param removingBalls
     */
    public void setRemovingBalls(BallRemover removingBalls) {
        this.removingBalls = removingBalls;
    }

    /**
     * the function returns the number of remaining blocks in the game.
     * @return the number of remaining blocks in the game
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * the function Run the game - start the animation loop.
     */
    public void run() {
        createBalls();
        long time = System.currentTimeMillis();
        this.runner = new AnimationRunner(gui, 60);
        this.runner.run(new CountdownAnimation(time, 3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
}
