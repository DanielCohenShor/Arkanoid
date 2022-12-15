/**
 * Author: Daniel Cohen shor.
 * ID: 207954975
 */
public class Counter {
    private int value;

    /**
     * default constructor.
     */
    public Counter() {
        value = 0;
    }
    /**
     * the function add number to current count.
     * @param number
     */
    public void increase(int number) {
        value += number;
    }
    /**
     * the function subtract number from current count.
     * @param number
     */
    public void decrease(int number) {
        value -= number;
    }
    /**
     * the function returns the current count.
     * @return the current count
     */
    public int getValue() {
        return value;
    }
}