import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {

    private Graph graph;
    private Town t1, t2, t3, t4;

    @Before
    public void setUp() {
        graph = new Graph();

        t1 = new Town("Alpha");
        t2 = new Town("Beta");
        t3 = new Town("Gamma");
        t4 = new Town("Delta");

        graph.addVertex(t1);
        graph.addVertex(t2);
        graph.addVertex(t3);
        graph.addVertex(t4);

        graph.addEdge(t1, t2, 3, "R1");
        graph.addEdge(t2, t3, 4, "R2");
        graph.addEdge(t3, t4, 2, "R3");
    }

    @Test
    public void testAddVertex() {
        Town t5 = new Town("Epsilon");
        assertTrue(graph.addVertex(t5));
    }

    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(t1));
        assertFalse(graph.containsVertex(new Town("Zeta")));
    }

    @Test
    public void testAddEdge() {
        assertNotNull(graph.addEdge(t1, t3, 6, "R4"));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(t1, t2));
        assertFalse(graph.containsEdge(t1, t4));
    }

    @Test
    public void testGetEdge() {
        assertNotNull(graph.getEdge(t1, t2));
    }

    @Test
    public void testEdgeSet() {
        Set<Road> edges = graph.edgeSet();
        assertEquals(3, edges.size());
    }

    @Test
    public void testVertexSet() {
        Set<Town> vertices = graph.vertexSet();
        assertEquals(4, vertices.size());
    }

    @Test
    public void testRemoveEdge() {
        assertNotNull(graph.removeEdge(t1, t2, 3, "R1"));
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.removeVertex(t4));
    }
}