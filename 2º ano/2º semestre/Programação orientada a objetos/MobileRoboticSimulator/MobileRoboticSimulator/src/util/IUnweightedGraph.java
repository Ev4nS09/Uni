package util;


import java.util.LinkedList;
import java.util.Set;

/**
 * Interface that provides a blueprint for any Unweighted graph implementation
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.3 27/04/2023
 */
public interface IUnweightedGraph<Item> extends Iterable<Item>
{
    /**
     * Adds item as a vertex to graph
     * @param item Item
     */
    void addVertex(Item item);

    /**
     * Adds connection from origin to end to graph
     * @param origin Item
     * @param end Item
     */
    void addConnection(Item origin, Item end);

    /**
     * Gets a graph's vertices
     * @return Set of all graph's vertices
     */
    Set<Item> getVertices();

    /**
     * Gets a vertex's connections
     * @param item Item
     * @return Linked List of all vertices that are connected to vertex item
     */
    LinkedList<Item> getConnections(Item item);

    /**
     * Gets size of graph (Number of vertices)
     * @return Int that is graph's size
     */
    int size();

    /**
     * Gets how many connected vertices there are to a vertex
     * @param item Item
     * @return Int that is a vertex's degree
     */
    int degree(Item item);
}
