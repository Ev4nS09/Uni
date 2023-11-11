package util.tests;

import geometry.Point;
import util.*;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//We are only testing this graph implementation with objects of type Point and behaving like an undirected graph, since in the given problem it will be it's only use
class UnweightedHashGraphTest
{
    @Test
    void testAddVertex()
    {
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)};
        UnweightedHashGraph<Point> graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        assertArrayEquals(points, graphTest.getVertices().toArray());

        graphTest.addVertex(points[0]); //Should not add
        assertEquals(5, graphTest.size());


        points = new Point[]{new Point(0,1), new Point(1, 2), new Point(1, 0), new Point(2, 1)};
        graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        assertArrayEquals(points, graphTest.getVertices().toArray());

        graphTest.addVertex(points[0]); //Should not add
        assertEquals(4, graphTest.size());
    }

    @Test
    void testAddVertexExceptions()
    {
        UnweightedHashGraph<Point> testGraph = new UnweightedHashGraph<>();
        assertThrows(NullPointerException.class, () -> testGraph.addVertex(null));
    }

    @Test
    void testConnections()
    {
        /*
        Creating graph with points points and with connections as represented in this comment

        points[2]
           |
           |
        points[0] --- points[1]

         */

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1)};
        UnweightedHashGraph<Point> graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        graphTest.addConnection(points[0], points[1]);
        graphTest.addConnection(points[0], points[2]);

        graphTest.addConnection(points[1], points[0]);

        graphTest.addConnection(points[2], points[0]);

        assertEquals(List.of(points[1], points[2]), graphTest.getConnections(points[0]));
        assertEquals(List.of(points[0]), graphTest.getConnections(points[1]));
        assertEquals(List.of(points[0]), graphTest.getConnections(points[2]));


        /*
        Creating graph with points points and with connections as represented in this comment

            --------- points[1] ---------
           |             |               |
           |             |               |
        points[0]        |            points[3]
           |             |               |
           |             |               |
            --------- points[2] ---------

         */

        points = new Point[]{new Point(0,1), new Point(1, 2), new Point(1, 0), new Point(2, 1)};
        graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        graphTest.addConnection(points[0], points[1]);
        graphTest.addConnection(points[0], points[2]);

        graphTest.addConnection(points[1], points[0]);
        graphTest.addConnection(points[1], points[2]);
        graphTest.addConnection(points[1], points[3]);

        graphTest.addConnection(points[2], points[0]);
        graphTest.addConnection(points[2], points[1]);
        graphTest.addConnection(points[2], points[3]);

        graphTest.addConnection(points[3], points[1]);
        graphTest.addConnection(points[3], points[2]);

        assertEquals(List.of(points[1], points[2]), graphTest.getConnections(points[0]));
        assertEquals(List.of(points[0], points[2], points[3]), graphTest.getConnections(points[1]));
        assertEquals(List.of(points[0], points[1], points[3]), graphTest.getConnections(points[2]));
        assertEquals(List.of(points[1], points[2]), graphTest.getConnections(points[3]));
    }

    @Test
    void testAddConnectionExceptions()
    {
        UnweightedHashGraph<Point> testGraph = new UnweightedHashGraph<>();
        assertThrows(NullPointerException.class, () -> testGraph.addConnection(null, null));
        assertThrows(NullPointerException.class, () -> testGraph.addConnection(null, new Point(0, 9)));
        assertThrows(NullPointerException.class, () -> testGraph.addConnection(new Point(6, 9), null));
        assertThrows(NoSuchElementException.class, () -> testGraph.addConnection(new Point(0, 0), new Point(1, 1)));
    }

    @Test
    void testContains()
    {
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)};
        UnweightedHashGraph<Point> graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        assertTrue(graphTest.contains(points[0]));
        assertFalse(graphTest.contains(new Point(2, 2)));
        assertTrue(graphTest.contains(new Point(0, 0)));
    }

    @Test
    void testGetConnectionsExceptions()
    {
        UnweightedHashGraph<Point> testGraph = new UnweightedHashGraph<>();
        assertThrows(NullPointerException.class, () -> testGraph.getConnections(null));
        assertThrows(NoSuchElementException.class, () -> testGraph.getConnections(new Point(0, 9)));
    }

    @Test
    void testDegree()
    {
        /*
        Creating graph with points points and with connections as represented in this comment

        points[2]     points[3]
           |
           |
        points[0] --- points[1]

        points[3] is isolated
         */

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)};
        UnweightedHashGraph<Point> graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        graphTest.addConnection(points[0], points[1]);
        graphTest.addConnection(points[0], points[2]);

        graphTest.addConnection(points[1], points[0]);

        graphTest.addConnection(points[2], points[0]);

        assertEquals(2, graphTest.degree(points[0]));
        assertEquals(1, graphTest.degree(points[1]));
        assertEquals(1, graphTest.degree(points[2]));
        assertEquals(0, graphTest.degree(points[3]));
    }

    @Test
    void testDegreeExceptions()
    {
        UnweightedHashGraph<Point> testGraph = new UnweightedHashGraph<>();
        assertThrows(NullPointerException.class, () -> testGraph.degree(null));
        assertThrows(NoSuchElementException.class, () -> testGraph.degree(new Point(0, 9)));
    }

    @Test
    void testIterator()
    {
        //Points aren't iterated by insertion order, however we had points by their hashValue in ascending order to make tests easier
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 0)};
        UnweightedHashGraph<Point> graphTest = new UnweightedHashGraph<>();
        for (Point point : points)
            graphTest.addVertex(point);

        Iterator<Point> iterator = graphTest.iterator();

        int i = 0;
        while(iterator.hasNext())
        {
            assertTrue(iterator.hasNext());
            assertEquals(points[i++], iterator.next());
        }
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}