import java.util.List;
import java.util.ArrayList;
/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Constructor that builds the colliadable array list.
     */
    public GameEnvironment() {
        collidableList = new ArrayList<Collidable>();
    }

    /**
     * Constructor with configurable colliadable list.
     * @param collidableList
     */
    public GameEnvironment(ArrayList<Collidable> collidableList) {
        this.collidableList = collidableList;
    }
    /**
     * the function adds the given collidable to the environment.
     * @param c
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * the function removes the given collidable from the environment.
     * @param c
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
    /**
     * the function checks the closest collision that going to occur with every collidable object.
     * @param trajectory
     * @return If this object will not collide with any of the collidables in this collection, return null
     * Else, return the information about the closest collision that is going to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // there are colliadable objects
        if (collidableList.size() != 0) {
            int counter = 0;
            Rectangle collisionRect = collidableList.get(counter).getCollisionRectangle();
            // gets the first colliadble object that has collision point
            while (trajectory.closestIntersectionToStartOfLine(collisionRect) == null) {
                counter++;
                // there are no collision point with all the colliadable objects
                if (counter == collidableList.size()) {
                    return null;
                }
                collisionRect = collidableList.get(counter).getCollisionRectangle();
            }
            Point collidableObjClosePoint =
                    trajectory.closestIntersectionToStartOfLine(collidableList.get(counter).getCollisionRectangle());
            double collisionDistance = trajectory.start().distance(collidableObjClosePoint);
            int minDistanceObj = counter;
            for (int i = counter; i < collidableList.size(); i++) {
                collisionRect = collidableList.get(i).getCollisionRectangle();
                Point checkCloser = trajectory.closestIntersectionToStartOfLine(collisionRect);
                // there is another collision point
                if (checkCloser != null) {
                    // this object collision point is closer to the start point of the line
                    if (trajectory.start().distance(checkCloser) < collisionDistance) {
                        collisionDistance = trajectory.start().distance(checkCloser);
                        collidableObjClosePoint = checkCloser;
                        minDistanceObj = i;
                    }
                }
            }
            return new CollisionInfo(collidableObjClosePoint, collidableList.get(minDistanceObj));
        }
        return null;
    }
}
