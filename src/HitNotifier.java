/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public interface HitNotifier {
    /**
     * the function adds hit listener to the hit events.
     * @param hl - the hit listener
     */
    void addHitListener(HitListener hl);
    /**
     * the function removes hit listener from the list of listeners to hit events.
     * @param hl - the hit listener
     */
    void removeHitListener(HitListener hl);
}