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

import java.util.*;


public class Graph implements GraphInterface<Town, Road> {

    /** Set of all vertices (towns) in the graph */
    private Set<Town> vertices = new HashSet<>();

    /** Set of all edges (roads) in the graph */
    private Set<Road> edges = new HashSet<>();

    /** Adjacency list mapping each town to its connecting roads */
    private Map<Town, List<Road>> adj = new HashMap<>();

    /** Stores shortest distances from source during Dijkstra execution */
    private Map<Town, Integer> distances;

    /** Stores previous vertex in shortest path */
    private Map<Town, Town> previous;

    /**
     * Returns the edge connecting two towns, if it exists
     *
     * @param source starting town
     * @param destination ending town
     * @return the Road connecting source and destination, or null if none exists
     */
    @Override
    public Road getEdge(Town source, Town destination) {
        for (Road r : edges) {
            if ((r.getSource().equals(source) && r.getDestination().equals(destination)) ||
                    (r.getSource().equals(destination) && r.getDestination().equals(source))) {
                return r;
            }
        }
        return null;
    }

    /**
     * This adds an edge (road) between two towns with a given weight and description
     *
     * @param source starting town
     * @param destination ending town
     * @param weight distance or cost of the road
     * @param description name/description of the road
     * @return the created Road object
     * @throws IllegalArgumentException if either town is not in the graph
     */
    @Override
    public Road addEdge(Town source, Town destination, int weight, String description) {
        if (!vertices.contains(source) || !vertices.contains(destination))
            throw new IllegalArgumentException();

        Road r = new Road(source, destination, weight, description);
        edges.add(r);

        adj.get(source).add(r);
        adj.get(destination).add(r);

        return r;
    }

    /**
     * This adds a vertex (town) to the graph
     *
     * @param v the town to add
     * @return true if the vertex was added, false if it was null or already exists
     */
    @Override
    public boolean addVertex(Town v) {
        if (v == null) return false;
        if (vertices.add(v)) {
            adj.put(v, new ArrayList<>());
            return true;
        }
        return false;
    }

    /**
     * Checks if an edge exists between two towns.
     *
     * @param source starting town
     * @param destination ending town
     * @return true if the edge exists, false otherwise
     */
    @Override
    public boolean containsEdge(Town source, Town destination) {
        return getEdge(source, destination) != null;
    }

    /**
     * Checks if a vertex exists in the graph
     *
     * @param v the town to check
     * @return true if the town exists, false otherwise
     */
    @Override
    public boolean containsVertex(Town v) {
        return vertices.contains(v);
    }

    /**
     * Returns the set of all edges in the graph
     *
     * @return set of Road objects
     */
    @Override
    public Set<Road> edgeSet() {
        return edges;
    }

    /**
     * Returns all edges connected to a given vertex
     *
     * @param vertex the town whose edges are requested
     * @return set of connected roads
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        return new HashSet<>(adj.get(vertex));
    }

    /**
     * This takes off an edge between two towns if it exists
     *
     * @param source starting town
     * @param destination ending town
     * @param weight expected weight of the road
     * @param description expected description of the road
     * @return the removed Road, or null if no matching edge exists
     */
    @Override
    public Road removeEdge(Town source, Town destination, int weight, String description) {
        Road r = getEdge(source, destination);
        if (r != null) {
            edges.remove(r);
            adj.get(source).remove(r);
            adj.get(destination).remove(r);
        }
        return r;
    }

    /**
     * This removes a vertex and all connected edges from the graph
     *
     * @param v the town to remove
     * @return true if the vertex was removed, false if it did not exist
     */
    @Override
    public boolean removeVertex(Town v) {
        if (!vertices.contains(v)) return false;

        edges.removeIf(r -> r.getSource().equals(v) || r.getDestination().equals(v));
        adj.remove(v);
        vertices.remove(v);

        return true;
    }

    /**
     * Returns the set of all vertices in the graph
     *
     * @return set of Town objects
     */
    @Override
    public Set<Town> vertexSet() {
        return vertices;
    }

    /**
     * Computes shortest paths from a source town to all other towns
     * using Dijkstra's algorithm
     *
     * @param source the starting town
     */
    @Override
    public void dijkstraShortestPath(Town source) {
        distances = new HashMap<>();
        previous = new HashMap<>();

        PriorityQueue<Town> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Town t : vertices) {
            distances.put(t, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pq.add(source);

        while (!pq.isEmpty()) {
            Town current = pq.poll();

            for (Road r : adj.get(current)) {
                Town neighbor = r.getSource().equals(current)
                        ? r.getDestination()
                        : r.getSource();

                int newDist = distances.get(current) + r.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    /**
     * This returns the shortest path from source to destination as a list of strings
     *
     * @param source starting town
     * @param dest destination town
     * @return list of formatted path steps, or empty list if no path exists
     */
    @Override
    public ArrayList<String> shortestPath(Town source, Town dest) {

        ArrayList<String> path = new ArrayList<>();
        dijkstraShortestPath(source);

        if (!previous.containsKey(dest)) return path;

        List<Town> nodes = new ArrayList<>();
        for (Town at = dest; at != null; at = previous.get(at)) {
            nodes.add(at);
        }
        Collections.reverse(nodes);

        for (int i = 0; i < nodes.size() - 1; i++) {
            Town from = nodes.get(i);
            Town to = nodes.get(i + 1);
            Road r = getEdge(from, to);

            path.add(from + " via " + r.getName() +
                    " to " + to + " " + r.getWeight() + " mi");
        }

        return path;
    }
}