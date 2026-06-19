/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 4/19/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */


public class TreeNode<T> {

    /** The data stored in the node */
    public T data;

    /** Reference to the left child (represents '.' in Morse code) */
    public TreeNode<T> left;

    /** Reference to the right child (represents '-' in Morse code) */
    public TreeNode<T> right;

    /**
     * Constructs a TreeNode with the given data.
     *
     * @param data the data to store in the node
     */
    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}