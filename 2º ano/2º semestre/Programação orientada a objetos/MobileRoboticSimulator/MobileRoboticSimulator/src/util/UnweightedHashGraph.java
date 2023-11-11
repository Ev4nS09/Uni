package util;

import java.util.*;

/**
 * Simple and minimal implementation of an unweighted graph using a HashMap of vertices, and their respective connections
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.4 27/04/2023
 */
public class UnweightedHashGraph<Item> implements IUnweightedGraph<Item>
{
    private final HashMap<Item, LinkedList<Item>> vertices;

    /**
     * Creates a new empty graph
     */
    public UnweightedHashGraph()
    {
        this.vertices = new HashMap<>();
    }

    /**
     * {@inheritDoc} <br>
     * Ignores item if it already exists within vertices
     * @param item Item
     */
    @Override
    public void addVertex(Item item)
    {
        if (item == null)
            throw new NullPointerException();
        else if (!this.vertices.containsKey(item))
            this.vertices.put(item, new LinkedList<>());
    }

    /**
     * {@inheritDoc} <br>
     * Ignores connection if it already exists in origin's connections
     * @param origin Item
     * @param end Item
     */
    @Override
    public void addConnection(Item origin, Item end)
    {
        if (origin == null || end == null)
            throw new NullPointerException();
        else if (!this.vertices.containsKey(origin) || !this.vertices.containsKey(end))
            throw new NoSuchElementException();
        else if (!this.vertices.get(origin).contains(end))
            this.vertices.get(origin).add(end);
    }

    /**
     * Checks if graph contains given item as one of it's vertices
     * @param item Item
     * @return True if graph contains item, false otherwise
     */
    public boolean contains(Item item)
    {
        return this.vertices.containsKey(item);
    }

    /**
     * {@inheritDoc}
     * @return Set of all graph's vertices
     */
    @Override
    public Set<Item> getVertices()
    {
        return this.vertices.keySet();
    }

    /**
     * {@inheritDoc}
     * @param item Item
     * @return Linked List of all vertices that are connected to vertex item
     */
    @Override
    public LinkedList<Item> getConnections(Item item)
    {
        if (item == null)
            throw new NullPointerException();
        else if (!this.vertices.containsKey(item))
            throw new NoSuchElementException();

        return this.vertices.get(item);
    }

    /**
     * {@inheritDoc}
     * @return Int that is graph's size
     */
    @Override
    public int size()
    {
        return this.vertices.size();
    }

    /**
     * {@inheritDoc}
     * @param item Item
     * @return Int that is a vertex's degree
     */
    @Override
    public int degree(Item item)
    {
        if (item == null)
            throw new NullPointerException();
        else if (!this.vertices.containsKey(item))
            throw new NoSuchElementException();

        return this.vertices.get(item).size();
    }

    /**
     * Creates an iterator for graph's vertices
     * @return Iterator to be able to iterate over graph's vertices
     */
    @Override
    public Iterator<Item> iterator()
    {
        return this.vertices.keySet().iterator();
    }
}
