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

public class Town implements Comparable<Town> {

    /** Name of the town */
    private String name;

    /**
     * Constructs a Town with the specified name
     *
     * @param name the name of the town
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the town
     *
     * @return town name
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this town with another town based on their names
     *
     * @param o the other town to compare
     * @return a negative integer, zero, or a positive integer as this town's
     *         name is less than, equal to, or greater than the specified town's name
     */
    @Override
    public int compareTo(Town o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Determines whether two Town objects are equal
     *
     * @param obj the object to compare with
     * @return true if the towns have the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Town)) return false;
        Town other = (Town) obj;
        return name.equals(other.name);
    }

    /**
     * Returns the hash code for this town
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns a string representation of the town
     *
     * @return the town's name
     */
    @Override
    public String toString() {
        return name;
    }
}