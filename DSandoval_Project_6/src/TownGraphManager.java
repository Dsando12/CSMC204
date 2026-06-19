/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 4/22/2026
 * Platform/compiler: IntelliJ IDEA
 *
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *
 * Name: David Sandoval
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class TownGraphManager implements TownGraphManagerInterface {

    /** Underlying graph structure */
    private Graph graph = new Graph();

    /**
     * Adds a road between two towns
     *
     * @param town1 the name of the starting town
     * @param town2 the name of the ending town
     * @param weight the distance or cost of the road
     * @param roadName the name of the road
     * @return true if the road was successfully added, false if either town does not exist
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        if (t1 == null || t2 == null) return false;

        graph.addEdge(t1, t2, weight, roadName);
        return true;
    }

    /**
     * Retrieves the name of the road connecting two towns
     *
     * @param town1 the starting town
     * @param town2 the ending town
     * @return the road name if it exists, otherwise null
     */
    @Override
    public String getRoad(String town1, String town2) {
        Road r = graph.getEdge(getTown(town1), getTown(town2));
        return r != null ? r.getName() : null;
    }

    /**
     * Adds a town to the graph
     *
     * @param v the name of the town
     * @return true if the town was added, false otherwise
     */
    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    /**
     * Retrieves a Town object by name
     *
     * @param name the name of the town
     * @return the Town object if found, otherwise null
     */
    @Override
    public Town getTown(String name) {
        for (Town t : graph.vertexSet()) {
            if (t.getName().equals(name)) return t;
        }
        return null;
    }

    /**
     * Checks if a town exists in the graph
     *
     * @param v the name of the town
     * @return true if the town exists, false otherwise
     */
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    /**
     * Checks if a road connection exists between two towns
     *
     * @param town1 the starting town
     * @param town2 the ending town
     * @return true if a connection exists, false otherwise
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(getTown(town1), getTown(town2));
    }

    /**
     * Returns a list of all road names in the graph, sorted alphabetically
     *
     * @return sorted list of road names
     */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> list = new ArrayList<>();
        for (Road r : graph.edgeSet()) {
            list.add(r.getName());
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Deletes a road connection between two towns
     *
     * @param town1 the starting town
     * @param town2 the ending town
     * @param road the name of the road
     * @return true if the road was removed, false otherwise
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return graph.removeEdge(getTown(town1), getTown(town2), -1, road) != null;
    }

    /**
     * Deletes a town and all associated roads from the graph
     *
     * @param v the name of the town
     * @return true if the town was removed, false otherwise
     */
    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    /**
     * Returns a list of all town names in the graph, sorted alphabetically
     *
     * @return sorted list of town names
     */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> list = new ArrayList<>();
        for (Town t : graph.vertexSet()) {
            list.add(t.getName());
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Returns the shortest path between two towns as a list of steps
     *
     * @param town1 the starting town
     * @param town2 the destination town
     * @return list of formatted path steps, or empty list if no path exists
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);

        if (t1 == null || t2 == null)
            return new ArrayList<>();

        return graph.shortestPath(t1, t2);
    }

    /**
     * Populates the graph from a file.
     *
     * @param file the input file containing graph data
     * @throws FileNotFoundException if the file cannot be found
     */
    public void populateTownGraph(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");

            String[] roadInfo = parts[0].split(",");
            String roadName = roadInfo[0];
            int weight = Integer.parseInt(roadInfo[1]);

            String town1 = parts[1];
            String town2 = parts[2];

            addTown(town1);
            addTown(town2);
            addRoad(town1, town2, weight, roadName);
        }

        sc.close();
    }
}