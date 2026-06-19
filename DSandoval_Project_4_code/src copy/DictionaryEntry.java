
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


public class DictionaryEntry {

    /** The stored normalized word */
    String word;

    /** Number of times the word has been added */
    int frequency;

    /**
     * This creates the DictionaryEntry with frequency initialized to 1.
     *
     * @param word normalized word to store inside the dictionary
     */
    public DictionaryEntry(String word) {
        this.word = word;
        this.frequency = 1;
    }
}