import java.awt.Color;
import biuoop.DrawSurface;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */

public class Ball implements Sprite {
    static final int DEFAULT = 0;

    private Point point;
    private int size;
    private Color color;
    private Velocity v;
    private GameEnvironment gameE;

    /**
     * Constructor with configurable ball center point, size and color.
     * @param center - the ball coordinates
     * @param r - ball size
     * @param color - ball color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        point = new Point(center.getX(), center.getY());
        size = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.gameE = new GameEnvironment();
    }

    /**
     * the function sets the game environment to a given game environment.
     * @param gameE
     */
    public void setGameE(GameEnvironment gameE) {
        this.gameE = gameE;
    }

    /**
     * the function adds this ball to the sprites list in the game.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this function removes this ball from the sprites list in the game.
     * @param g - the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * the function returns the x coordinate of the ball center point.
     * @return the x coordinate of the ball center point
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * the function returns the y coordinate of the ball center point.
     * @return the y coordinate of the ball center point
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * the function returns the size of the ball.
     * @return the size of the ball
     */
    public int getSize() {
        return size;
    }

    /**
     * the function returns the color of the ball.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * the function draws the ball of the given surface.
     * @param surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.fillCircle((int) point.getX(), (int) point.getY(), size);
    }

    /**
     * the function moves the ball when the time is passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the function sets the velocity of the ball according to a given velocity.
     * @param v
     */
    public void setVelocity(Velocity v) {
        this.v = v;
        this.v.calculateSpeed();
    }

    /**
     * the function sets the velocity of the ball according to dx and dy values.
     * @param dx
     * @param dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
        this.v.calculateSpeed();
    }

    /**
     * the function returns the velocity of the ball.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * the function changes the ball coordinates if the ball hits collaidable object or the canvas boundries.
     */
    public void moveOneStep() {
        Point nextMove = new Point((point.getX() + v.getDx()), (point.getY() + v.getDy()));
        Line ballLine = new Line(this.point, nextMove);
        // the ball has collision point with blocks
        if (gameE.getClosestCollision(ballLine) != null) {
            CollisionInfo myCollisionInfo = gameE.getClosestCollision(ballLine);
            // the collision point is on the way of the ball
            if (ballLine.isInLine(myCollisionInfo.collisionPoint())) {
                Velocity newVelocity = myCollisionInfo.collisionObject().hit(this,
                        myCollisionInfo.collisionPoint(), this.v);
                this.setVelocity(newVelocity);
                Line hitLine = new Line(this.point, myCollisionInfo.collisionPoint());
                this.point = hitLine.middle();
            }
            // the ball has collision point with the canvas
        } else {
            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }
}
