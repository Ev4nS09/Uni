package util;

/**
 * Interface that provides a blueprint for any Collection that will behave as a queue
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 27/04/2023
 */
public interface IQueue<Item> extends Iterable<Item>
{
    /**
     * Adds item to start of queue
     * @param item Item
     */
    void enqueue(Item item);

    /**
     * Removes item from end of queue
     * @return Item at end of queue
     */
    Item dequeue();

    /**
     * Checks item at end of queue, without removing it
     * @return Item at end of queue or null if queue is empty
     */
    Item peek();

    /**
     * Checks if queue is empty
     * @return True if queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Gets size of queue
     * @return Int that is queue's size
     */
    int size();
}
