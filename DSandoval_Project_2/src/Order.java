/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 2/22/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */

public class Order {

    /** This is the unique identifier for the order */
    private final String id;

    /** The deadline minute by which the order should be shipped */
    private final int deadlineMinute;

    /** The minute the order entered the system */
    private int arrivalMinute;

    /**
     * This Constructs the Order with the specified ID and deadline minute.
     * @param id the unique identifier of the order that need specified ID
     * @param deadlineMinute the minute by which the order must be shipped
     */
    public Order(String id, int deadlineMinute) {
        this.id = id;
        this.deadlineMinute = deadlineMinute;
        this.arrivalMinute = -1; // -1 is not yet arrived as its below real time minute
    }

    /**
     * Returns the unique identifier of this order.
     * @return the order ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the deadline minute of this order.
     * @return the shipping deadline minute
     */
    public int getDeadlineMinute() {
        return deadlineMinute;
    }

    /**
     * Sets the arrival minute for this order.
     * @param arrivalMinute the minute the order entered the system
     */
    public void setArrivalMinute(int arrivalMinute) {
        this.arrivalMinute = arrivalMinute;
    }

    /**
     * Returns the arrival minute of this order.
     * @return the arrival minute, or -1 if the order has not yet arrived
     */
    public int getArrivalMinute() {
        return arrivalMinute;
    }

    /**
     * Returns a string representation of the order
     * @return a formatted string describing the order
     */
    @Override
    public String toString() {
        return id + " (dl=" + deadlineMinute + ", arr=" + arrivalMinute + ")";
    }
}