
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

/**
 * Exception is thrown when a dictionary word can't be found to be removed
 */
public class DictionaryEntryNotFoundException extends Exception {

    /**
     * This constructs exception with message
     *
     * @param message error message plays
     */
    public DictionaryEntryNotFoundException(String message) {
        super(message);
    }
}