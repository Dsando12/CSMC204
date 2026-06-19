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

import java.util.ArrayList;


public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

    /** The root node of the Morse code tree */
    private TreeNode<String> root;

    /**
     * Constructor that initializes and builds the Morse code tree
     */
    public MorseCodeTree() {
        buildTree();
    }

    /**
     * Returns the root of the tree
     *
     * @return the root TreeNode
     */
    public TreeNode<String> getRoot() {
        return root;
    }

    /**
     * Sets the root of the tree
     *
     * @param newNode the new root node
     */
    public void setRoot(TreeNode<String> newNode) {
        root = newNode;
    }

    /**
     * Inserts a letter into the tree based on its Morse code.
     *
     * @param code   the Morse code representation
     * @param result the corresponding English letter
     */
    public void insert(String code, String result) {
        addNode(root, code, result);
    }

    /**
     * A dot ('.') moves left, and a dash ('-') moves right
     *
     * @param root   the current node in the tree
     * @param code   the Morse code string
     * @param letter the letter to insert into the tree
     */
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() == 1) {
            if (code.equals("."))
                root.left = new TreeNode<>(letter);
            else
                root.right = new TreeNode<>(letter);
        } else {
            if (code.charAt(0) == '.') {
                if (root.left == null)
                    root.left = new TreeNode<>("");
                addNode(root.left, code.substring(1), letter);
            } else {
                if (root.right == null)
                    root.right = new TreeNode<>("");
                addNode(root.right, code.substring(1), letter);
            }
        }
    }

    /**
     * Recevies the English letter corresponding to the given Morse code
     *
     * @param code the Morse code string
     * @return the corresponding English letter
     */
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    /**
     * Recursively traverses the tree to find the letter associated
     * with the given Morse code
     *
     * @param root the current node in the traversal
     * @param code the remaining Morse code string
     * @return the matching letter stored in the tree
     */
    public String fetchNode(TreeNode<String> root, String code) {
        if (code.length() == 0)
            return root.data;

        if (code.charAt(0) == '.')
            return fetchNode(root.left, code.substring(1));
        else
            return fetchNode(root.right, code.substring(1));
    }

    /**
     * This operation is not supported for the MorseCodeTree
     *
     * @param data the data to delete
     * @return nothing (always throws exception)
     * @throws UnsupportedOperationException always thrown
     */
    public LinkedConverterTreeInterface<String> delete(String data) {
        throw new UnsupportedOperationException();
    }

    /**
     * This operation is not supported for the MorseCodeTree
     *
     * @return nothing (always throws exception)
     * @throws UnsupportedOperationException always thrown
     */
    public LinkedConverterTreeInterface<String> update() {
        throw new UnsupportedOperationException();
    }

    /**
     * Builds the Morse code tree by inserting all letter mappings
     */
    public void buildTree() {
        root = new TreeNode<>("");

        insert(".", "e");
        insert("-", "t");
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");

        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");

        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    /**
     * Returns an ArrayList containing the tree elements in
     * in-order (Left-Node-Right) traversal
     *
     * @return list of tree elements in LNR order
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        LNRoutputTraversal(root, list);
        return list;
    }

    /**
     * Recursively performs an in-order traversal of the tree
     * and stores each node's data in the provided list
     *
     * @param root the current node
     * @param list the list storing traversal results
     */
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if (root != null) {
            LNRoutputTraversal(root.left, list);
            list.add(root.data);
            LNRoutputTraversal(root.right, list);
        }
    }
}