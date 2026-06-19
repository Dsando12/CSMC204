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

public class WarehouseSimulation implements SimulationInterface {

    /** The priority queue that manages the orders that are awaiting shipment */
    private final MyPriorityQueue<Order> orderQueue;

    /** The stack used to store late or returned orders */
    private final MyStack<Order> returnsStack;

    /** The Array of orders that will be released into the simulation */
    private final Order[] ordersToRelease;

    /** The current simulated minute */
    private int currentMinute;

    /** The index of the next order to release */
    private int nextOrderIndex;

    /** The total number of orders that have arrived on time */
    private int totalArrived;

    /** The total number of orders that have been shipped out */
    private int totalShipped;

    /** The total number of orders shipped late */
    private int totalLate;

    /**
     * Constructs the WarehouseSimulation with a set of orders.
     *
     * @param orders the array of orders to be released over time
     */
    public WarehouseSimulation(Order[] orders) {
        this.ordersToRelease = orders;
        this.orderQueue = new MyPriorityQueue<>(new OrderComparator());
        this.returnsStack = new MyStack<>();
        this.currentMinute = 0;
        this.nextOrderIndex = 0;
        this.totalArrived = 0;
        this.totalShipped = 0;
        this.totalLate = 0;
    }

    /**
     * This makes an advancement of the simulation by one minute.
     */
    @Override
    public void tick() {


        if (nextOrderIndex < ordersToRelease.length) {
            Order o = ordersToRelease[nextOrderIndex++];
            o.setArrivalMinute(currentMinute);
            orderQueue.enqueue(o);
            totalArrived++;
        }


        if (!orderQueue.isEmpty()) {
            Order o = orderQueue.dequeue();
            totalShipped++;

            if (currentMinute > o.getDeadlineMinute()) {
                returnsStack.push(o);
                totalLate++;
            }
        }


        currentMinute++;
    }

    /**
     * This determines if the simulation has completed.
     * @return true if all orders have been fulfilled and the queue is empty,
     * false otherwise.
     */
    @Override
    public boolean isFinished() {
        return nextOrderIndex >= ordersToRelease.length && orderQueue.isEmpty();
    }

    /**
     * Returns the current simulated minute.
     * @return the current time in minutes
     */
    @Override
    public int getCurrentMinute() {
        return currentMinute;
    }

    /**
     * Returns the total number of orders that have arrived.
     * @return total arrived orders
     */
    @Override
    public int getTotalArrived() {
        return totalArrived;
    }

    /**
     * Returns the total number of orders that have been shipped.
     * @return total shipped orders
     */
    @Override
    public int getTotalShipped() {
        return totalShipped;
    }

    /**
     * Returns the total number of orders shipped after their deadline.
     * @return total late orders
     */
    @Override
    public int getTotalLate() {
        return totalLate;
    }

    /**
     * Returns a snapshot of the current priority queue contents.
     * @return an array representing the current order queue
     */
    public Object[] getQueueSnapshot() {
        return orderQueue.toArray();
    }

    /**
     * Returns a snapshot of the current returns stack contents.
     * @return an array representing the current returns stack
     */
    public Object[] getReturnsSnapshot() {
        return returnsStack.toArray();
    }
}