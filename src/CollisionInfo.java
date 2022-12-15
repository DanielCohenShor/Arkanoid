/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObj;

    /**
     * Constructor with collisionPoint location and the collision object.
     * @param collisionPoint - the collision Point
     * @param collisionObj - the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObj) {
        this.collisionPoint = collisionPoint;
        this.collisionObj = collisionObj;
    }

    /**
     * the function returns the point at which the collision occurs.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * the function returns the collidable object involved in the collision.
     * @return the collision object
     */
    public Collidable collisionObject() {
        return collisionObj;
    }
}
