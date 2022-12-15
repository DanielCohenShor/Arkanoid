/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speed;

    /**
     * Constructor with configurable dx and dy.
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function returns the speed.
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * the function changes the speed.
     * @param speed - the new speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * the function calculates the speed according to the dx and the dy.
     */
    public void calculateSpeed() {
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        this.speed = speed;
    }

    /**
     * the function returns the dx value.
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * the function returns the dy value.
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * the function changes the dx value according to the sent value.
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * the function changes the dy value according to the sent value.
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * the function Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }

    /**
     * the function gets angle and speed and converts it to dx and dy of the ball.
     * @param angle
     * @param speed
     * @return new velocity with dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = speed * Math.sin(radianAngle);
        double dy = speed * (-1) * Math.cos(radianAngle);
        return new Velocity(dx, dy);
    }
}