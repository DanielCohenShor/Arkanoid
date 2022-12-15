import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double BALL_MOVEMENT = -0.999999;
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor with configurable rectangle.
     * @param newRect
     * @param color - the color of the block
     */
    public Block(Rectangle newRect, java.awt.Color color) {
        this.rect = newRect;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * the function returns the block.
     * @return the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * the function adds this block to the sprites and collidable objects list in the game.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * the function draws the block on the surface.
     * @param d - the given surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    /**
     * the function notify the block that the time is passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * the function removes the block from the sprites list and collidable list in the game.
     * @param game - the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * the function adds hit listener to the hit events.
     * @param hl - the hit listener
     */
    public void addHitListener(HitListener hl) {
       hitListeners.add(hl);
    }
    /**
     * the function removes hit listener from the list of listeners to hit events.
     * @param hl - the hit listener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * the function notify all the hit listeners in the listeners list that there was a hit.
     * @param hitter - the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * the function changes the velocity of an object according to the hit point with the rectangle.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the current velocity of the object
     * @return the new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the hit point is on the upper side of the rect
        if (this.rect.getUpperLine().isInLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (currentVelocity.getDy() * BALL_MOVEMENT));
        }
        // the hit point is on the right side of the rect
        if (this.rect.getRightLine().isInLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity((currentVelocity.getDx() * BALL_MOVEMENT), currentVelocity.getDy());
        }
        // the hit point is on the bottom side of the rect
        if (this.rect.getBottomLine().isInLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (currentVelocity.getDy() * BALL_MOVEMENT));
        }
        // the hit point is on the left side of the rect
        if (this.rect.getLeftLine().isInLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity((currentVelocity.getDx() * BALL_MOVEMENT), currentVelocity.getDy());
        }
        return currentVelocity;
    }
}
