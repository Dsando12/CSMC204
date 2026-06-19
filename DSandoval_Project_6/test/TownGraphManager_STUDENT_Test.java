import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {

    private TownGraphManager manager;

    @Before
    public void setUp() {
        manager = new TownGraphManager();

        manager.addTown("X");
        manager.addTown("Y");
        manager.addTown("Z");

        manager.addRoad("X", "Y", 5, "RoadXY");
        manager.addRoad("Y", "Z", 7, "RoadYZ");
    }

    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("W"));
    }

    @Test
    public void testContainsTown() {
        assertTrue(manager.containsTown("X"));
        assertFalse(manager.containsTown("Unknown"));
    }

    @Test
    public void testAddRoad() {
        assertTrue(manager.addRoad("X", "Z", 10, "RoadXZ"));
    }

    @Test
    public void testGetRoad() {
        assertEquals("RoadXY", manager.getRoad("X", "Y"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertTrue(manager.containsRoadConnection("X", "Y"));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = manager.allRoads();
        assertTrue(roads.contains("RoadXY"));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(manager.deleteRoadConnection("X", "Y", "RoadXY"));
    }

    @Test
    public void testDeleteTown() {
        assertTrue(manager.deleteTown("Z"));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> towns = manager.allTowns();
        assertTrue(towns.contains("X"));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = manager.getPath("X", "Z");
        assertNotNull(path);
    }
}