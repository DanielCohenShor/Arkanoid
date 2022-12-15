/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public interface Collidable {
    /**
     * the function return the "collision shape" of the object.
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * the function checks in which side of the rectangle the object colliaded with and changes the
     * velocity according to that.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the current velocity
     * @param hitter - the ball that hit
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
