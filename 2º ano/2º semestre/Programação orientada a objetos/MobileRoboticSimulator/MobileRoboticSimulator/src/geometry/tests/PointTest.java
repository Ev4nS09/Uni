package geometry.tests;

import geometry.Point;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest
{
    @Test
    void testConstructorAndToString()
    {
        assertEquals("001,001", new Point(1, 1).toString());
        assertEquals("000,000", new Point(0, 0).toString());
        assertEquals("072,072", new Point(72, 72).toString());
    }

    @Test
    void testInvariant()
    {
        assertThrows(IllegalArgumentException.class, () -> new Point(5, 1001));
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, -5));
        assertThrows(IllegalArgumentException.class, () -> new Point(1001, 1001));
    }

    @Test
    void testGetters()
    {
        Point point1 = new Point(5, 5);
        assertEquals(5, point1.getX());
        assertEquals(5, point1.getY());
        Point point2 = new Point(10, 1);
        assertEquals(10, point2.getX());
        assertEquals(1, point2.getY());
        Point point3 = new Point(12, 6);
        assertEquals(12, point3.getX());
        assertEquals(6, point3.getY());
    }

    @Test
    void testDistanceTo()
    {
        Point point = new Point(0, 1);
        assertEquals(4.123105625617661, point.distanceTo(new Point(1, 5)));
        assertEquals(6.324555320336759, point.distanceTo(new Point(2, 7)));
        assertEquals(10.04987562112089, point.distanceTo(new Point(10, 2)));
    }

    @Test
    void testEquals()
    {
        Point point1 = new Point(0, 1);
        assertEquals(point1, new Point(0, 1));
        assertNotEquals(point1, new Point(5, 10));
        Point point2 = new Point(5, 1);
        assertNotEquals(point2, new Point(8, 1));
        assertEquals(point2, new Point(5, 1));
        Point point3 = new Point(7, 12);
        assertEquals(point3, new Point(7, 12));
    }

    @Test
    void testHashCode()
    {
        Point p0 = new Point(1, 2);
        Point p1 = new Point(2, 1);
        assertNotEquals(p0.hashCode(), p1.hashCode());
        assertEquals(p0.hashCode(), new Point(1, 2).hashCode());

        Point p2 = null;
        assertThrows(NullPointerException.class, () -> p2.hashCode());
    }
}