import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * default constructor.
     */
    public SpriteCollection() {
        spritesList = new ArrayList<Sprite>();
    }

    /**
     * the function adds sprites to the sprites list.
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * the function removes sprites from the sprites list.
     * @param s - the sprite
     */
    public void removeSprite(Sprite s) {
        spritesList.remove(s);
    }

    /**
     * the function notify all the sprite objects that the time has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).timePassed();
        }
    }

    /**
     * the function draws all the sprite objects on the given surface.
     * @param d - the given surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).drawOn(d);
        }
    }
}