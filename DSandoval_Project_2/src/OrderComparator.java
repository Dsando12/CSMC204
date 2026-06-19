/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description: Comparator used to determine the priority of Order objects
 * within the warehouse simulation.
 * Due: 2/22/2026
 * Platform/compiler: IntelliJ IDEA
 *
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *
 * Name: David Sandoval
 */

import java.util.Comparator;


public class OrderComparator implements Comparator<Order> {

    /**
     * Makes a comparsion on the two Order objects
     *  that is based on deadline and arrival time.
     *
     * @param o1 the first order
     * @param o2 the second order
     * @return a negative integer if o1 has higher priority than o2,
     *         a positive integer if o1 has lower priority than o2,
     *         zero if they are considered equal in priority,
     */
    @Override
    public int compare(Order o1, Order o2) {
        int cmp = Integer.compare(o1.getDeadlineMinute(), o2.getDeadlineMinute());

        if (cmp != 0) {
            return cmp;
        }

        return Integer.compare(o1.getArrivalMinute(), o2.getArrivalMinute());
    }
}