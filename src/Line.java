import java.util.List;

/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Line {
    private static final int NOT_INTERSECTING = 0, MORE_THAN_ONE = 2, ONE_INTERSECTION = 1;
    static final double EPSILON = Math.pow(10, -10);
    private Point start;
    private Point end;

    /**
     * Constructor with configurable start and end points of the line.
     * @param start - the start point of the line.
     * @param end - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor with configurable two points coordinates.
     * @param x1 - the x coordinate of the start point.
     * @param y1 - the y coordinate of the start point.
     * @param x2 - the x coordinate of the end point.
     * @param y2 - the y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * the function calls a function that calculates the length of this line.
     * @return the length of this line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * the function calculates the middle point of this line.
     * @return middle point of the line.
     */
    public Point middle() {
        double xPoint = (start.getX() + end.getX()) / 2;
        double yPoint = (start.getY() + end.getY()) / 2;
        return new Point(xPoint, yPoint);
    }

    /**
     * the function checks if given point is on the line.
     * @param checkPoint - the wanted point
     * @return true if the point is on the line, false otherwise
     */
    public boolean isInLine(Point checkPoint) {
        double compareStartX = Double.compare(checkPoint.getX(), start.getX());
        double compareEndX =  Double.compare(checkPoint.getX(), end.getX());
        double compareStartY = Double.compare(checkPoint.getY(), start.getY());
        double compareEndY = Double.compare(checkPoint.getY(), end.getY());
        // the point is on the line
        if ((compareStartX >= 0 && compareEndX <= 0 && compareStartY >= 0 && compareEndY <= 0)
            || (compareStartX >= 0 && compareEndX <= 0 && compareStartY <= 0 && compareEndY >= 0)) {
            return true;
            // the point is on the line
        } else if ((compareStartX <= 0 && compareEndX >= 0 && compareStartY <= 0 && compareEndY >= 0)
                || (compareStartX <= 0 && compareEndX >= 0 && compareStartY >= 0 && compareEndY <= 0)) {
            return true;
        }
        return false;
    }

    /**
     * the function returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * the function changes the line start point.
     * @param start - the new start point of the line
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * the function changes the line end point.
     * @param end - the new end point of the line
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * the function returns the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * the function checks if two lines intersect.
     * @param other - the other line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // this line is vertical (infinity incline) and the other line is regular
        if ((Double.compare(this.start.getX(), this.end.getX()) == 0)
                && (Double.compare(other.start.getX(), other.end.getX()) != 0)) {
            Point intersectionPoint = verticalLineIntersection(this, other);
            if (intersectionPoint == null) {
                return false;
            }
            return true;
        }
        // the other line is vertical (infinity incline) and this line is regular
        if ((Double.compare(this.start.getX(), this.end.getX()) != 0)
                && (Double.compare(other.start.getX(), other.end.getX()) == 0)) {
            Point intersectionPoint = verticalLineIntersection(other, this);
            if (intersectionPoint == null) {
                return false;
            }
            return true;
        }
        // both of the lines is vertical (infinity incline)
        if ((Double.compare(this.start.getX(), this.end.getX()) == 0)
                && (Double.compare(other.start.getX(), other.end.getX()) == 0)) {
            if (checkVertical(other) == NOT_INTERSECTING) {
                return false;
            }
            return true;
        }
        // this line is horizontal (zero incline) and the other line is regular
        if ((Double.compare(this.start.getY(), this.end.getY()) == 0)
                && (Double.compare(other.start.getY(), other.end.getY()) != 0)) {
            Point intersectionPoint = horizontalLineIntersection(this, other);
            if (intersectionPoint == null) {
                return false;
            }
            return true;
        }
        // the other line is horizontal (zero incline) and this line is regular
        if ((Double.compare(this.start.getY(), this.end.getY()) != 0)
                && (Double.compare(other.start.getY(), other.end.getY()) == 0)) {
            Point intersectionPoint = horizontalLineIntersection(other, this);
            if (intersectionPoint == null) {
                return false;
            }
            return true;
        }
        // both of the lines are horizontal (zero incline)
        if ((Double.compare(this.start.getY(), this.end.getY()) == 0)
                && (Double.compare(other.start.getY(), other.end.getY()) == 0)) {
            if (checkHorizontial(other) == NOT_INTERSECTING) {
                return false;
            }
            return true;
        }
        // both lines are regular - numeric incline
        if (regularIntersectionPoint(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * the function checks the intersection point of two line that one is vertical and the other is regular.
     * @param v - the vertical line
     * @param r - the regular line
     * @return if intersects returns the intersection point, otherwise returns null
     */
    public Point verticalLineIntersection(Line v, Line r) {
        double lineIncline = incline(r);
        double freeVariable = freeVariable(lineIncline, r.start.getX(), r.start.getY());
        double intersectionY = (lineIncline * v.start.getX()) + freeVariable;
        Point newIntersection = new Point(v.start.getX(), intersectionY);
        // the intersection point is on both lines
        if (v.isInLine(newIntersection) && r.isInLine(newIntersection)) {
            return new Point(v.start.getX(), intersectionY);
        }
        return null;
    }

    /**
     * the function checks the intersection point of two line that one is horizontal and the other is regular.
     * @param h - the horizontal line
     * @param r - the regular line
     * @return if intersects returns the intersection point, otherwise returns null
     */
    public Point horizontalLineIntersection(Line h, Line r) {
        double lineIncline = incline(r);
        double freeVariable = freeVariable(lineIncline, r.start.getX(), r.start.getY());
        double intersectionX = (h.start.getY() - freeVariable) / lineIncline;
        Point newIntersection = new Point(intersectionX, h.start.getY());
        // the intersection point is on both lines
        if (h.isInLine(newIntersection) && r.isInLine(newIntersection)) {
            return new Point(intersectionX, h.start.getY());
        }
        return null;
    }

    /**
     * the function check if both lines are horizontial and has only one intersection point.
     * @param other line
     * @return if the lines has one intersection point return 1, if there is more than one returns 2,
     * otherwise there is no intersection so returns 0
     */
    public int checkHorizontial(Line other) {
        // the lines are parallel
        if (incline(this) != incline(other)) {
            return NOT_INTERSECTING;
        }
        // there is more than one intersection point
        if ((other.start.getX() < this.start.getX() && this.start.getX() < other.end.getX())
                || (other.start.getX() < this.end.getX() && this.start.getX() < other.end.getX())) {
            return MORE_THAN_ONE;
        }
        return ONE_INTERSECTION;
    }

    /**
     * the function calculates the intersection point between two horizontial lines.
     * @param other
     * @return the intersection point
     */
    public Point horizontialIntersection(Line other) {
        if (Double.compare(this.start.getX(), other.end.getX()) == 0) {
            return new Point(start.getX(), start.getY());
        }
        return new Point(end.getX(), end.getY());
    }

    /**
     * the function check if both lines are vertical and has only one intersection point.
     * @param other line
     * @return if the lines has one intersection point return 1, if there is more than one returns 2,
     * otherwise there is no intersection so returns 0
     */
    public int checkVertical(Line other) {
        // the lines are parallel
        if (Double.compare(this.start.getY(), other.start.getY()) == 0) {
            return NOT_INTERSECTING;
        }
        // there is more than one intersection point
        if ((other.start.getY() < this.start.getY() && this.start.getY() < other.end.getY())
                || (other.start.getY() < this.end.getY() && this.start.getY() < other.end.getY())) {
            return MORE_THAN_ONE;
        }
        return ONE_INTERSECTION;
    }

    /**
     * the function calculates the intersection point between two vertical lines.
     * @param other
     * @return the intersection point
     */
    public Point verticalIntersection(Line other) {
        if (Double.compare(this.start.getY(), other.end.getY()) == 0) {
            return new Point(start.getX(), start.getY());
        }
        return new Point(end.getX(), end.getY());
    }

    /**
     * the function checks if two lines has interscetion point.
     * @param other
     * @return if intersect return the intersection point else null
     */
    public Point regularIntersectionPoint(Line other) {
        double thisFreeVariable = freeVariable(incline(this), start.getX(), start.getY());
        // calculates the free variable of the other line
        double otherFreeVariable = freeVariable(incline(other), other.start.getX(), other.start.getY());
        // calculates the intersection x value
        double interscetionX = ((thisFreeVariable - otherFreeVariable) / (incline(other) - incline(this)));
        double interscetionY = (incline(this) * interscetionX) + thisFreeVariable;
        Point intersectionPoint = new Point(interscetionX, interscetionY);
        // the intersection point is on both lines
        if (this.isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * the function returns the intersection point if the lines intersect, and null otherwise.
     * @param other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            // this line is vertical (infinity incline) and the other line is regular
            if ((Double.compare(this.start.getX(), this.end.getX()) == 0)
                    && (Double.compare(other.start.getX(), other.end.getX()) != 0)) {
                return verticalLineIntersection(this, other);
            }
            // the other line is vertical (infinity incline) and this line is regular
            if ((Double.compare(this.start.getX(), this.end.getX()) != 0)
                    && (Double.compare(other.start.getX(), other.end.getX()) == 0)) {
                return verticalLineIntersection(other, this);
            }
            // both of the lines is vertical (infinity incline)
            if ((Double.compare(this.start.getX(), this.end.getX()) == 0)
                    && (Double.compare(other.start.getX(), other.end.getX()) == 0)) {
                if (checkVertical(other) == ONE_INTERSECTION) {
                    return verticalIntersection(other);
                }
                return null;
            }
            // this line is horizontal (zero incline) and the other line is regular
            if ((Double.compare(this.start.getY(), this.end.getY()) == 0)
                    && (Double.compare(other.start.getY(), other.end.getY()) != 0)) {
                return horizontalLineIntersection(this, other);
            }
            // the other line is horizontal (zero incline) and this line is regular
            if ((Double.compare(this.start.getY(), this.end.getY()) != 0)
                    && (Double.compare(other.start.getY(), other.end.getY()) == 0)) {
                return horizontalLineIntersection(other, this);
            }
            // both of the lines are horizontal (zero incline)
            if ((Double.compare(this.start.getY(), this.end.getY()) == 0)
                    && (Double.compare(other.start.getY(), other.end.getY()) == 0)) {
                if (checkHorizontial(other) == ONE_INTERSECTION) {
                    return horizontialIntersection(other);
                }
                return null;
            }
            // both lines are regular - numeric incline
            return regularIntersectionPoint(other);
        }
        return null;
    }

    /**
     * the function checks if two given lines are equal.
     * @param other - the other line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        // the lines are equal
        if ((start.equals(other.start) && end.equals(other.end))
                || (start.equals(other.end) && end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * the function calculates the incline of given line.
     * @param l
     * @return the incline of a line
     */
    public double incline(Line l) {
        double otherIncline = ((l.start.getY() - l.end.getY()) / (l.start.getX() - l.end.getX()));
        return otherIncline;
    }

    /**
     * the function calculates the free variable of a line.
     * @param lineIncline the incline of the line
     * @param x - x start point of the line
     * @param y - y start point of the line
     * @return the free variable fo the line.
     */
    public double freeVariable(double lineIncline, double x, double y) {
        return (y - (lineIncline * x));
    }

    /**
     * the function checks which of the intersection points of the line and the rect is closer to the start of the line.
     * @param rect
     * @return If this line does not intersect with the rectangle, return null,
     *          Otherwise, return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPointsArr = rect.intersectionPoints(this);
        // there are intersection points
        if (intersectionPointsArr.size() != 0) {
            Point closePoint = intersectionPointsArr.get(0);
            for (int i = 1; i < intersectionPointsArr.size(); i++) {
                // there is closer intersection point to the start of the line
                if (intersectionPointsArr.get(i).distance(start) < closePoint.distance(start)) {
                    closePoint = intersectionPointsArr.get(i);
                }
            }
            return closePoint;
        }
        return null;
    }
}
