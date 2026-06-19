/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 3/8/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */

/**
 * This exception throws a request if a user cannot be found in the system.
 */
public class UserNotFoundException extends Exception {

    /**
     * This constructs a new {@code UserNotFoundException} with the specified detail message.
     *
     * @param message The invalid message that plays when the user inserts an invalid text
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}