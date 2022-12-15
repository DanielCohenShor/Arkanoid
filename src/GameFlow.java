import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.util.List;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private EndScreen e;
    private Counter totalCount;

    /**
     * Constructor with configurable animation runner, keyboard sensor and gui.
     * @param ar
     * @param ks
     * @param gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        totalCount = new Counter();
    }

    /**
     * the function handles the levels of the game.
     * @param levels - the levels of the game
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0, numOfBalls = 0;
        Sleeper sleeper = new Sleeper();
        for (LevelInformation levelInfo : levels) {
            // counts the levels
            i++;
            numOfBalls = levelInfo.numberOfBalls();
            // create the level
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, totalCount);
            level.initialize();
            Counter livesCounter = new Counter();
            livesCounter.increase(1);
            level.getSprites().addSprite(new HeadIndicator(livesCounter, levelInfo.levelName()));
            // as long as the user didn't lose or didn't finish the level - keep runing the level
            while (level.getRemainingBalls().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }
            // the user lose
            if (level.getRemainingBalls().getValue() == 0) {
                livesCounter.decrease(1);
                // shows the lose screen
                KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(this.ks,
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.ks, 0, totalCount, this.gui));
                this.ar.run(key);
                // if the user pressed the space key so terminate the game
                if (key.shouldStop()) {
                    gui.close();
                    return;
                }
            }
            // the user won
            if (i == levels.size() && level.getRemainingBlocks().getValue() == 0) {
                // shows the win screen
                KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(this.ks,
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.ks, 1, totalCount, this.gui));
                this.ar.run(key);
                // if the user pressed the space key so terminate the game
                if (key.shouldStop()) {
                    gui.close();
                    return;
                }
            }
            sleeper.sleepFor(1000);
        }
    }
}