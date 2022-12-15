import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Ass6Game {
    /**
     * main function that creates the game.
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        } else {
            for (int i = 0; i < args.length; i++) {
                try {
                    int num = Integer.parseInt(args[i]);
                    if (num <= 4 && num >= 1) {
                        if (num == 1) {
                            levels.add(new Level1());
                        } else if (num == 2) {
                            levels.add(new Level2());
                        } else if (num == 3) {
                            levels.add(new Level3());
                        } else {
                            levels.add(new Level4());
                        }
                    }
                } finally {
                    continue;
                }
            }
        }
        GameFlow gameF = new GameFlow(runner, keyboard, gui);
        gameF.runLevels(levels);
    }
}
