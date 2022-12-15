/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor with configurable x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function calculates the distance between two points.
     * @param other - the other point.
     * @return return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double pointsDistance = ((x - other.x) * (x - other.x)) + ((y - other.y) * (y - other.y));
        return Math.sqrt(pointsDistance);
    }

    /**
     * the function checks if two points are equals.
     * @param other - the other point.
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        // the points are equal
        if ((Double.compare(x, other.x) == 0) && (Double.compare(y, other.y) == 0)) {
            return true;
        }
        return false;
    }
    /**
     * the function returns the x value of this point.
     * @return the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * the function returns the y value of this point.
     * @return the y value of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * the function changes the x value of the point.
     * @param x - the new x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * the function changes the y value of the point.
     * @param y - the new y value
     */
    public void setY(double y) {
        this.y = y;
    }
}
