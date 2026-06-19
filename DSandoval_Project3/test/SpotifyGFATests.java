
/**
 * NOTE TO STUDENTS:
 * -These tests represent the minimum requirements to validate the basic functionality of your project.
 * -If the project due date has passed, you must meet these requirements in order to pass the course.
 * -If you have not yet done so, move on to testing your code using the public tests provided to you.
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class SpotifyGFATests {

    private GenericLinkedList<String> list;

    @Before
    public void setUp() {
        list = new GenericLinkedList<>();
    }

    @Test
    public void testAddFirst() {
        list.addFirst("Book");
        list.addFirst("Table"); // should now be at index 0
        assertEquals("Table", list.get(0)); // confirms position at the beginning
    }

    @Test
    public void testAddLast() {
        list.addLast("Book");
        list.addLast("Table"); // should now be at the end (index 1)
        assertEquals("Table", list.get(1)); // confirms position at end
    }


    @Test
    public void testContains() {
        list.addLast("Book");
        assertTrue(list.contains("Book"));
        assertFalse(list.contains("Table"));
    }


}
