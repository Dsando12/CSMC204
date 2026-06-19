/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 4/22/2026
 * Platform/compiler: IntelliJ IDEA
 *
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *
 * Name: David Sandoval
 */

public class Road implements Comparable<Road> {

    /** Starting town of the road */
    private Town source;

    /** Ending town of the road */
    private Town destination;

    /** Weight of the road (distance or cost) */
    private int weight;

    /** Name of the road */
    private String name;

    /**
     * This constructs a Road object with specified endpoints, weight, and name
     *
     * @param source the starting town
     * @param destination the ending town
     * @param weight the distance or cost of the road
     * @param name the name/description of the road
     */
    public Road(Town source, Town destination, int weight, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    /**
     * Returns the name of the road
     *
     * @return road name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the source (starting town) of the road
     *
     * @return source town
     */
    public Town getSource() {
        return source;
    }

    /**
     * Returns the destination (ending town) of the road
     *
     * @return destination town
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Returns the weight (distance or cost) of the road
     *
     * @return road weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Compares this road with another road based on their names
     *
     * @param o the other road to compare
     * @return a negative integer, zero, or a positive integer as this road's
     *         name is less than, equal to, or greater than the specified road's name
     */
    @Override
    public int compareTo(Road o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Determines whether two roads are equal
     * Roads are considered equal if they connect the same two towns
     *
     * @param obj the object to compare with
     * @return true if the roads are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Road)) return false;
        Road other = (Road) obj;

        return ((source.equals(other.source) && destination.equals(other.destination)) ||
                (source.equals(other.destination) && destination.equals(other.source)))
                && weight == other.weight
                && name.equals(other.name);
    }

    /**
     * Returns the hash code for this road
     * Based on the road's name
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}