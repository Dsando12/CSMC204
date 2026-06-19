import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

    private Town t1, t2, t3;
    private Road r1, r2;

    @Before
    public void setUp() {
        t1 = new Town("A");
        t2 = new Town("B");
        t3 = new Town("C");

        r1 = new Road(t1, t2, 5, "Route1");
        r2 = new Road(t2, t3, 10, "Route2");
    }

    @Test
    public void testContains() {
        assertTrue(r1.contains(t1));
        assertTrue(r1.contains(t2));
        assertFalse(r1.contains(t3));
    }

    @Test
    public void testGetName() {
        assertEquals("Route1", r1.getName());
    }

    @Test
    public void testGetWeight() {
        assertEquals(5, r1.getWeight());
    }

    @Test
    public void testEquals() {
        Road copy = new Road(t1, t2, 5, "Route1");
        assertTrue(r1.equals(copy));
    }

    @Test
    public void testCompareTo() {
        assertTrue(r1.compareTo(r2) != 0);
    }

    @Test
    public void testToString() {
        assertTrue(r1.toString().contains("Route1"));
    }
}