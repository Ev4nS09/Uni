package util.tests;

import util.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeAsQueueTest
{
    @Test
    void testEnqueueAndDequeue()
    {
        ArrayDequeAsQueue<Integer> testQ = new ArrayDequeAsQueue<>();
        for (int i = 0; i < 10; i++)
            testQ.enqueue(i + 1);

        for (int i = 0; i < 10; i++)
            assertEquals(i + 1, testQ.dequeue());

        assertThrows(NoSuchElementException.class, testQ::dequeue);
    }

    @Test
    void testPeek()
    {
        ArrayDequeAsQueue<Integer> testQ = new ArrayDequeAsQueue<>();

        assertNull(testQ.peek());

        testQ.enqueue(7);
        testQ.enqueue(2);
        testQ.enqueue(7);

        assertEquals(7, testQ.peek());

        testQ.dequeue();
        assertEquals(2, testQ.peek());
    }

    @Test
    void testIsEmpty()
    {
        ArrayDequeAsQueue<Integer> testQ = new ArrayDequeAsQueue<>();
        assertTrue(testQ.isEmpty());

        testQ.enqueue(0);

        assertFalse(testQ.isEmpty());
    }

    @Test
    void testSize()
    {
        ArrayDequeAsQueue<Integer> testQ = new ArrayDequeAsQueue<>();
        assertEquals(0, testQ.size());

        testQ.enqueue(7);
        testQ.enqueue(2);
        testQ.enqueue(7);

        assertEquals(3, testQ.size());

        testQ.enqueue(2);

        assertEquals(4, testQ.size());
    }

    @Test
    void testIterator()
    {
        ArrayDequeAsQueue<String> testQ = new ArrayDequeAsQueue<>();

        testQ.enqueue("Testing");
        testQ.enqueue("123");
        testQ.enqueue("Testing");

        Iterator<String> iterator = testQ.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("Testing", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("123", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("Testing", iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}