package geometry.tests;

import geometry.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathTest
{
    @Test
    void testConstructor()
    {
        assertEquals("[]", new Path().toString());

        Path path1 = new Path();
        path1.addPoint(new Point(1, 1));
        path1.addPoint(new Point(2, 2));
        assertEquals("[001,001 ; 002,002]", path1.toString());

        Path path2 = new Path();
        path2.addPoint(new Point(1, 1));
        path2.addPoint(new Point(2, 2));
        path2.addPoint(new Point(5, 4));
        path2.addPoint(new Point(6, 9));
        path2.addPoint(new Point(69, 69));
        assertEquals("[001,001 ; 002,002 ; 005,004 ; 006,009 ; 069,069]", path2.toString());
    }

    @Test
    void testInvariant()
    {
        Path path1 = new Path();
        path1.addPoint(new Point(1, 1));
        assertThrows(IllegalArgumentException.class, () -> path1.addPoint(new Point(1, 1)));

        Path path2 = new Path();
        path2.addPoint(new Point(1, 1));
        path2.addPoint(new Point(2, 2));
        path2.addPoint(new Point(2, 3));
        assertThrows(IllegalArgumentException.class, () -> path2.addPoint(new Point(2, 2)));

        Path path3 = new Path();
        path3.addPoint(new Point(6, 9));
        path3.addPoint(new Point(69, 69));
        assertThrows(IllegalArgumentException.class, () -> path3.addPoint(new Point(6, 9)));
    }

    @Test
    void testGetSize()
    {
        Path path1 = new Path();
        path1.addPoint(new Point(0, 0));
        assertEquals(1, path1.size());

        Path path2 = new Path();
        path2.addPoint(new Point(0, 0));
        path2.addPoint(new Point(1, 1));
        path2.addPoint(new Point(1, 2));
        assertEquals(3, path2.size());

        Path path3 = new Path();
        path3.addPoint(new Point(0, 0));
        path3.addPoint(new Point(0, 1));
        path3.addPoint(new Point(0, 2));
        path3.addPoint(new Point(1, 0));
        path3.addPoint(new Point(3, 5));
        assertEquals(5, path3.size());
    }

    @Test
    void testGetLength()
    {
        Path path1 = new Path();
        path1.addPoint(new Point(0, 0));
        path1.addPoint(new Point(1, 1));
        assertEquals(1.4142135623730951, path1.length());

        Path path2 = new Path();
        path2.addPoint(new Point(3, 2));
        path2.addPoint(new Point(5, 1));
        path2.addPoint(new Point(6, 7));
        path2.addPoint(new Point(1, 5));
        assertEquals(13.703995314932513, path2.length());

        Path path3 = new Path();
        path3.addPoint(new Point(6, 8));
        path3.addPoint(new Point(4, 1));
        path3.addPoint(new Point(7, 9));
        path3.addPoint(new Point(1, 7));
        path3.addPoint(new Point(5, 12));
        path3.addPoint(new Point(10, 2));
        path3.addPoint(new Point(6, 21));
        assertEquals(59.1486209188142, path3.length());
    }

    @Test
    void testIntersectsObstacle()
    {
        Path path1 = new Path();
        Obstacle obs1 = new Rectangle("1 2 3 2 3 5 1 5");
        path1.addPoint(new Point(1, 0));
        path1.addPoint(new Point(3, 5));
        assertTrue(path1.intersects(obs1));

        Path path2 = new Path();
        Obstacle obs2 = new Triangle("2 3 2 7 4 5");
        Obstacle obs3 = new Triangle("4 1 4 3 8 4");
        path2.addPoint(new Point(1, 1));
        path2.addPoint(new Point(3, 3));
        path2.addPoint(new Point(5, 2));
        assertFalse(path2.intersects(obs2));
        assertTrue(path2.intersects(obs3));

        Path path3 = new Path();
        Obstacle obs4 = new Circumference("4 5 2");
        Obstacle obs5 = new Triangle("2 0 2 3 6 1");
        Obstacle obs6 = new Rectangle("7 4 8 4 8 9 7 9");
        path3.addPoint(new Point(0, 2));
        path3.addPoint(new Point(8, 3));
        path3.addPoint(new Point(10, 9));
        assertFalse(path3.intersects(obs4));
        assertTrue(path3.intersects(obs5));
        assertFalse(path3.intersects(obs6));
    }

    @Test
    void testIterator()
    {
        Path path = new Path();
        Point point0 = new Point(6, 8);
        Point point1 = new Point(4, 1);
        Point point2 = new Point(7, 9);
        Point point3 = new Point(1, 7);
        path.addPoint(point0);
        path.addPoint(point1);
        path.addPoint(point2);
        path.addPoint(point3);
        Iterator<Point> iterator = path.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(point0, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(point1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(point2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(point3, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}