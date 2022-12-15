import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Paddle implements Sprite, Collidable {
    static final int DEFAULT = 0, MOVE_PADDLE = 10;
    private static final double BALL_MOVEMENT = -0.999999;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private Point canvasStart;
    private int canvasWidth;
    private List<Line> paddleRegions;

    /**
     * Constructor with configurable rectangle, gui, canvas height and canvas width.
     * @param paddleRect - the paddle rectangle
     * @param gui - the gui
     * @param canvasWidth - the canvas width
     */
    public Paddle(Rectangle paddleRect, GUI gui, int canvasWidth) {
        keyboard = gui.getKeyboardSensor();
        this.paddleRect = paddleRect;
        this.canvasStart = new Point(DEFAULT, DEFAULT);
        this.canvasWidth = canvasWidth;
        paddleRegions = new ArrayList<Line>();
        createPaddleRegions();
    }

    /**
     * the function moves the paddle left according to the keyboard press.
     */
    public void moveLeft() {
        double rectNewX = paddleRect.getUpperLeft().getX() - MOVE_PADDLE;
        Point newPoint = new Point(rectNewX, paddleRect.getUpperLeft().getY());
        // the paddle can move
        if (rectNewX >= (canvasStart.getX() + MOVE_PADDLE)) {
            paddleRect.setUpperLeft(newPoint);
            updateRegion(0);
        }
    }

    /**
     * the function moves the paddle right according to the keyboard press.
     */
    public void moveRight() {
        double rectNewX = paddleRect.getUpperRight().getX() + MOVE_PADDLE;
        Point newPoint = new Point(rectNewX, paddleRect.getUpperLeft().getY());
        // the paddle can move
        if (rectNewX <= (canvasWidth - MOVE_PADDLE)) {
            paddleRect.setUpperRight(newPoint);
            updateRegion(1);
        }
    }

    /**
     * the function checks if the user wanted to move the paddle.
     */
    @Override
    public void timePassed() {
        // move paddle to the left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            // move paddle to the right
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * the function draws the paddle on the surface.
     * @param d - the given surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(12, 207, 191));
        d.fillRectangle((int) paddleRect.getUpperLeft().getX(), (int) paddleRect.getUpperLeft().getY(),
                (int) paddleRect.getWidth(), (int) paddleRect.getHeight());
    }

    /**
     * the function returns the paddle rect.
     * @return the paddle rect
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return paddleRect;
    }

    /**
     * the function checks in which region the ball collided and change the velocity according to that.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the current velocity
     * @return the new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        int hitRegion = -1;
        // the hit point is on the upper side of the rect
        if (this.paddleRect.getUpperLine().isInLine(collisionPoint)) {
            for (int i = 0; i < 5; i++) {
                // there is a hit
                if (paddleRegions.get(i).isInLine(collisionPoint)) {
                    hitRegion = i;
                }
            }
            // hit with the most left region
            if (hitRegion == 0) {
                newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                newVelocity.setSpeed(currentVelocity.getSpeed());
                return newVelocity;
                // hit with the left region
            } else if (hitRegion == 1) {
                newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                newVelocity.setSpeed(currentVelocity.getSpeed());
                return newVelocity;
                // hit with the middle region
            } else if (hitRegion == 2) {
                return new Velocity(currentVelocity.getDx(), (currentVelocity.getDy() * BALL_MOVEMENT));
                // hit with the right region
            } else if (hitRegion == 3) {
                newVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                newVelocity.setSpeed(currentVelocity.getSpeed());
                return newVelocity;
                // hit with the most right region
            } else if (hitRegion == 4) {
                newVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                newVelocity.setSpeed(currentVelocity.getSpeed());
                return newVelocity;
            }
        }
        // the hit point is on the right side of the rect
        if (this.paddleRect.getRightLine().isInLine(collisionPoint)) {
            return new Velocity((currentVelocity.getDx() * BALL_MOVEMENT), currentVelocity.getDy());
        }
        // the hit point is on the bottom side of the rect
        if (this.paddleRect.getBottomLine().isInLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (currentVelocity.getDy() * BALL_MOVEMENT));
        }
        // the hit point is on the left side of the rect
        if (this.paddleRect.getLeftLine().isInLine(collisionPoint)) {
            return new Velocity((currentVelocity.getDx() * BALL_MOVEMENT), currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * the function diveds the paddle to 5 regions.
     */
    public void createPaddleRegions() {
        int regionDistance = (int) paddleRect.getWidth() / 5;
        double startX = paddleRect.getUpperLeft().getX();
        double pointY = paddleRect.getUpperLeft().getY();
        Point startPoint = paddleRect.getUpperLeft();
        Point endPoint = new Point((startX + regionDistance), pointY);
        // divdes the paddle to 5 regions and create from each region a line
        for (int i = 0; i < 5; i++) {
            paddleRegions.add(new Line(startPoint, endPoint));
            startPoint = endPoint;
            endPoint = new Point((endPoint.getX() + regionDistance), pointY);
        }
    }

    /**
     * the function updates the lines of the regions according to the paddle movement.
     * @param flag
     */
    public void updateRegion(int flag) {
        for (int i = 0; i < 5; i++) {
            // the paddle moved to the right
            if (flag == 1) {
                Point newStartPoint = new Point((paddleRegions.get(i).start().getX() + MOVE_PADDLE),
                        paddleRegions.get(i).start().getY());
                Point newEndPoint = new Point((paddleRegions.get(i).end().getX() + MOVE_PADDLE),
                        paddleRegions.get(i).start().getY());
                paddleRegions.get(i).setStart(newStartPoint);
                paddleRegions.get(i).setEnd(newEndPoint);
                // the paddle moved to the left
            } else {
                Point newStartPoint = new Point((paddleRegions.get(i).start().getX() - MOVE_PADDLE),
                        paddleRegions.get(i).start().getY());
                Point newEndPoint = new Point((paddleRegions.get(i).end().getX() - MOVE_PADDLE),
                        paddleRegions.get(i).start().getY());
                paddleRegions.get(i).setStart(newStartPoint);
                paddleRegions.get(i).setEnd(newEndPoint);
            }
        }
    }

    /**
     * the function adds this paddle to the game.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}