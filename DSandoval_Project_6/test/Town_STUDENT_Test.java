import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

    private Town town1;
    private Town town2;
    private Town town3;

    @Before
    public void setUp() {
        town1 = new Town("Rockville");
        town2 = new Town("Gaithersburg");
        town3 = new Town("Bethesda");
    }

    @Test
    public void testGetName() {
        assertEquals("Rockville", town1.getName());
    }

    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) != 0);
    }

    @Test
    public void testEquals() {
        Town copy = new Town("Rockville");
        assertTrue(town1.equals(copy));
        assertFalse(town1.equals(town2));
    }

    @Test
    public void testToString() {
        assertEquals("Rockville", town1.toString());
    }
}