
/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 3/29/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */


public class Node {

    /** Data stored in this node */
    DictionaryEntry data;

    /** Pointer to next node in the chain */
    Node next;

    /**
     * Constructs a Node containing a DictionaryEntry.
     *
     * @param data dictionary entry to store
     */
    public Node(DictionaryEntry data) {
        this.data = data;
        this.next = null;
    }
}