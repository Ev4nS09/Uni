package geometry.tests;

import geometry.*;
import util.UnweightedHashGraph;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest
{
    @Test
    void testInvariable()
    {
        Map map0 = new Map();
        map0.addObstacle(new Rectangle("1 0 2 0 2 2 1 2"));
        map0.addObstacle(new Triangle("4 0 6 0 6 2"));
        assertThrows(IllegalArgumentException.class, () -> map0.addObstacle(new Rectangle("1 0 2 0 2 2 1 2")));
        assertThrows(IllegalArgumentException.class, () -> map0.addObstacle(new Circumference("7 0 2")));
        assertThrows(IllegalArgumentException.class, () -> map0.addObstacle(new Triangle("2 2 3 3 4 5")));
    }

    @Test
    void testToGraph()
    {
        Map map0 = new Map();
        map0.addObstacle(new Rectangle("1 0 2 0 2 2 1 2"));
        map0.addObstacle(new Triangle("4 0 6 0 6 2"));
        UnweightedHashGraph<Point> mapGraph0 = map0.toGraph();

        //Creating expected graph
        UnweightedHashGraph<Point> expectedGraph0 = new UnweightedHashGraph<>();
        //Ordered by hash code to make tests easier
        Point[] anchorPoints0 = {new Point(0, 0), new Point(3, 3), new Point(7, 0), new Point(0, 3), new Point(3, 0), new Point(7, 3)};
        expectedGraph0.addVertex(anchorPoints0[0]);
        expectedGraph0.addVertex(anchorPoints0[1]);
        expectedGraph0.addVertex(anchorPoints0[2]);
        expectedGraph0.addVertex(anchorPoints0[3]);
        expectedGraph0.addVertex(anchorPoints0[4]);
        expectedGraph0.addVertex(anchorPoints0[5]);
        expectedGraph0.addConnection(anchorPoints0[0], anchorPoints0[3]);
        expectedGraph0.addConnection(anchorPoints0[1], anchorPoints0[3]);
        expectedGraph0.addConnection(anchorPoints0[1], anchorPoints0[4]);
        expectedGraph0.addConnection(anchorPoints0[1], anchorPoints0[5]);
        expectedGraph0.addConnection(anchorPoints0[2], anchorPoints0[5]);
        expectedGraph0.addConnection(anchorPoints0[3], anchorPoints0[0]);
        expectedGraph0.addConnection(anchorPoints0[3], anchorPoints0[1]);
        expectedGraph0.addConnection(anchorPoints0[3], anchorPoints0[5]);
        expectedGraph0.addConnection(anchorPoints0[4], anchorPoints0[1]);
        expectedGraph0.addConnection(anchorPoints0[4], anchorPoints0[5]);
        expectedGraph0.addConnection(anchorPoints0[5], anchorPoints0[1]);
        expectedGraph0.addConnection(anchorPoints0[5], anchorPoints0[2]);
        expectedGraph0.addConnection(anchorPoints0[5], anchorPoints0[3]);
        expectedGraph0.addConnection(anchorPoints0[5], anchorPoints0[4]);

        assertEquals(expectedGraph0.getVertices(), mapGraph0.getVertices());
        for (Point vertex : expectedGraph0)
            assertEquals(expectedGraph0.getConnections(vertex), mapGraph0.getConnections(vertex));


        Map map1 = new Map();
        map1.addObstacle(new Circumference("0 0 2"));
        UnweightedHashGraph<Point> mapGraph1 = map1.toGraph();

        //Creating expected graph
        UnweightedHashGraph<Point> expectedGraph1 = new UnweightedHashGraph<>();
        //Ordered by hash code to make tests easier
        Point[] anchorPoints1 = {new Point(0, 0), new Point(3, 3), new Point(0, 3), new Point(3, 0)};
        expectedGraph1.addVertex(anchorPoints1[0]);
        expectedGraph1.addVertex(anchorPoints1[1]);
        expectedGraph1.addVertex(anchorPoints1[2]);
        expectedGraph1.addVertex(anchorPoints1[3]);
        expectedGraph1.addConnection(anchorPoints1[1], anchorPoints1[2]);
        expectedGraph1.addConnection(anchorPoints1[1], anchorPoints1[3]);
        expectedGraph1.addConnection(anchorPoints1[2], anchorPoints1[1]);
        expectedGraph1.addConnection(anchorPoints1[2], anchorPoints1[3]);
        expectedGraph1.addConnection(anchorPoints1[3], anchorPoints1[1]);
        expectedGraph1.addConnection(anchorPoints1[3], anchorPoints1[2]);

        assertEquals(expectedGraph1.getVertices(), mapGraph1.getVertices());
        for (Point vertex : expectedGraph1)
            assertEquals(expectedGraph1.getConnections(vertex), mapGraph1.getConnections(vertex));
    }

    @Test
    void testHasInside()
    {
        Map map0 = new Map();
        map0.addObstacle(new Rectangle("2 1 6 1 6 5 2 5"));
        Point point0 = new Point(3, 4);
        assertTrue(map0.hasInside(point0));

        Map map1 = new Map();
        map1.addObstacle(new Rectangle("2 1 6 1 6 5 2 5"));
        map1.addObstacle(new Circumference("9 3 2"));
        Point point1 = new Point(5, 2);
        Point point2 = new Point(9, 0);
        assertTrue(map1.hasInside(point1));
        assertFalse(map1.hasInside(point2));

        Map map2 = new Map();
        map2.addObstacle(new Triangle("3 2 9 9 3 7"));
        map2.addObstacle(new Circumference("14 11 5"));
        map2.addObstacle(new Rectangle("8 2 12 2 12 6 8 6"));
        Point point3 = new Point(9, 8);
        Point point4 = new Point(8, 8);
        assertFalse(map2.hasInside(point3));
        assertTrue(map2.hasInside(point4));
    }

    @Test
    void testIsIntersectedBySegment()
    {
        Map map0 = new Map();
        map0.addObstacle(new Rectangle("3 4 5 4 5 7 3 7"));
        Segment segment0 = new Segment(new Point(2, 2), new Point(6, 8));
        assertTrue(map0.isIntersectedBy(segment0));

        Map map1 = new Map();
        map1.addObstacle(new Rectangle("2 0 4 0 4 2 2 2"));
        map1.addObstacle(new Rectangle("2 4 6 4 6 6 2 6"));
        map1.addObstacle(new Triangle("8 2 10 4 8 4"));
        map1.addObstacle(new Circumference("10 10 2"));
        Segment segment1 = new Segment(new Point(0, 0), new Point(6, 2));
        Segment segment2 = new Segment(new Point(6, 0), new Point(8, 6));
        assertTrue(map1.isIntersectedBy(segment1));
        assertFalse(map1.isIntersectedBy(segment2));

        Map map2 = new Map();
        map2.addObstacle(new Triangle("3 1 5 2 3 2"));
        map2.addObstacle(new Triangle("1 4 5 4 3 7"));
        map2.addObstacle(new Circumference("9 5 3"));
        Segment segment3 = new Segment(new Point(1, 0), new Point(7, 2));
        Segment segment4 = new Segment(new Point(6, 3), new Point(5, 7));
        assertFalse(map2.isIntersectedBy(segment3));
        assertFalse(map2.isIntersectedBy(segment4));
    }

    @Test
    void testIsIntersectedByPath()
    {
        Map map0 = new Map();
        map0.addObstacle(new Circumference("10 9 7"));
        Path path0 = new Path();
        path0.addPoint(new Point(1, 1));
        path0.addPoint(new Point(5, 3));
        path0.addPoint(new Point(11, 1));
        path0.addPoint(new Point(18, 5));
        assertFalse(map0.isIntersectedBy(path0));

        Map map1 = new Map();
        map1.addObstacle(new Triangle("2 2 12 4 2 8"));
        map1.addObstacle(new Rectangle("14 2 22 2 22 8 14 8"));
        Path path1 = new Path();
        path1.addPoint(new Point(2, 0));
        path1.addPoint(new Point(12, 2));
        path1.addPoint(new Point(14, 10));
        assertFalse(map1.isIntersectedBy(path1));

        Map map2 = new Map();
        map2.addObstacle(new Circumference("8 10 8"));
        map2.addObstacle(new Triangle("18 2 32 0 24 8"));
        map2.addObstacle(new Triangle("18 6 26 10 18 14"));
        Path path2 = new Path();
        path2.addPoint(new Point(4, 0));
        path2.addPoint(new Point(18, 4));
        path2.addPoint(new Point(24, 14));
        assertTrue(map2.isIntersectedBy(path2));
    }

    @Test
    void testIterator()
    {
        Map map = new Map();
        map.addObstacle(new Circumference("0 0 2"));
        map.addObstacle(new Rectangle("3 0 5 0 5 2 3 2"));
        map.addObstacle(new Triangle("2 2 0 4 2 4"));

        Iterator<Obstacle> obstacleIterator = map.iterator();

        assertTrue(obstacleIterator.hasNext());
        assertEquals("[000,000 ; 2]", obstacleIterator.next().toString());

        assertTrue(obstacleIterator.hasNext());
        assertEquals("[003,000 ; 005,000 ; 005,002 ; 003,002]", obstacleIterator.next().toString());

        assertTrue(obstacleIterator.hasNext());
        assertEquals("[002,002 ; 000,004 ; 002,004]", obstacleIterator.next().toString());

        assertFalse(obstacleIterator.hasNext());
        assertThrows(NoSuchElementException.class, obstacleIterator::next);
    }
}