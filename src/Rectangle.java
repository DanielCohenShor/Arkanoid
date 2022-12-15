import java.util.List;
import java.util.ArrayList;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private double width;
    private double height;
    private Line upperLine;
    private Line bottomLine;
    private Line rightLine;
    private Line leftLine;

    /**
     * Constructor with configurable upper left point of the rectangle and width and height.
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.upperRight = new Point((this.upperLeft.getX() + width), this.upperLeft.getY());
        updateLinesAndPoints();
    }

    /**
     * the function changes the upper left point of the rectangle and updates the other lines and
     * points according to the change.
     * @param upperLeft - the new upper-left point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point((this.upperLeft.getX() + width), this.upperLeft.getY());
        updateLinesAndPoints();
    }

    /**
     * the function changes the upper right point of the rectangle and updates the other lines and
     * points according to the change.
     * @param upperRight - the new upper-right point of the rectangle
     */
    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
        this.upperLeft = new Point((this.upperRight.getX() - width), this.upperRight.getY());
        updateLinesAndPoints();
    }

    /**
     * the function updates the other lines and points of the rect according to
     * the upper-left and upper-right points of the rectangle.
     */
    public void updateLinesAndPoints() {
        this.bottomLeft = new Point(this.upperLeft.getX(), (this.upperLeft.getY() + height));
        this.bottomRight = new Point(this.upperRight.getX(), this.bottomLeft.getY());
        this.upperLine = new Line(this.upperLeft, this.upperRight);
        this.rightLine = new Line(this.upperRight, this.bottomRight);
        this.leftLine = new Line(this.upperLeft, this.bottomLeft);
        this.bottomLine = new Line(this.bottomLeft, this.bottomRight);
    }

    /**
     * the function returns the bottom line of the rectangle.
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return bottomLine;
    }

    /**
     * the function returns the left line of the rectangle.
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * the function returns the right line of the rectangle.
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * the function returns the upper line of the rectangle.
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return upperLine;
    }

    /**
     * the function checks if the rectangle and the line has intersection points.
     * @param line
     * @return a (possibly empty) List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsArr = new ArrayList<Point>();
        Point checkIntersect;
        // checks if the top side of the rect intersects with the line
         checkIntersect = line.intersectionWith(upperLine);
        if (checkIntersect != null) {
            intersectionPointsArr.add(checkIntersect);
        }
        // checks if the right side of the rect intersects with the line
        checkIntersect = line.intersectionWith(rightLine);
        if (checkIntersect != null) {
            intersectionPointsArr.add(checkIntersect);
        }
        // checks if the left side of the rect intersects with the line
        checkIntersect = line.intersectionWith(leftLine);
        if (checkIntersect != null) {
            intersectionPointsArr.add(checkIntersect);
        }
        // checks if the bottom side of the rect intersects with the line
        checkIntersect = line.intersectionWith(bottomLine);
        if (checkIntersect != null) {
            intersectionPointsArr.add(checkIntersect);
        }
        return intersectionPointsArr;
    }
    /**
     * the function returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function returns the height of the rectangle.
     * @return the width of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the function returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function returns the bottom-left point of the rectangle.
     * @return the bottom-left point of the rectangle
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }

    /**
     * the function returns the bottom-right point of the rectangle.
     * @return the bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * the function returns the upper-right point of the rectangle.
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }
}
