package util;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Simplifies the use of the ArrayDeque as a Queue by filtering unnecessary methods, and renaming some of them <br>
 * In reality nothing new is being implemented in this class
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 27/04/2023
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html">ArrayDeque documentation</a>
 */
public class ArrayDequeAsQueue<Item> implements IQueue<Item>
{
    private final ArrayDeque<Item> queue;

    /**
     * Creates a new empty queue
     */
    public ArrayDequeAsQueue()
    {
        this.queue = new ArrayDeque<>();
    }

    /**
     * {@inheritDoc}
     * @param item Item
     */
    @Override
    public void enqueue(Item item)
    {
        this.queue.add(item);
    }

    /**
     * {@inheritDoc}
     * @return Item at end of queue
     */
    @Override
    public Item dequeue()
    {
        return this.queue.remove();
    }

    /**
     * {@inheritDoc}
     * @return Item at end of queue or null if queue is empty
     */
    @Override
    public Item peek()
    {
        return this.queue.peek();
    }

    /**
     * {@inheritDoc}
     * @return True if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return this.queue.isEmpty();
    }

    /**
     * {@inheritDoc}
     * @return Int that is queue's size
     */
    @Override
    public int size()
    {
        return this.queue.size();
    }

    /**
     * Creates an iterator for queue
     * @return Iterator to be able to iterate over queue
     */
    @Override
    public Iterator<Item> iterator()
    {
        return this.queue.iterator();
    }
}
