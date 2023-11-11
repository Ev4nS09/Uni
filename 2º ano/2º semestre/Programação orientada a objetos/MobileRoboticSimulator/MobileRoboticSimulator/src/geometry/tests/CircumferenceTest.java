package geometry.tests;

import geometry.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircumferenceTest
{
    @Test
    void testConstructor()
    {
        assertEquals("[002,002 ; 1]", new Circumference("2 2 1").toString());
        assertEquals("[015,015 ; 5]", new Circumference("15 15 5").toString());
        assertEquals("[078,056 ; 26]", new Circumference("78 56 26").toString());
    }

    @Test
    void testInvariant()
    {
        assertThrows(IllegalArgumentException.class, () -> new Circumference("0 0 -1"));
        assertThrows(IllegalArgumentException.class, () -> new Circumference("56 0 -23"));
        assertThrows(IllegalArgumentException.class, () -> new Circumference("234 123 -1034"));
    }

    @Test
    void testGetAnchorPoints()
    {
        Point[] points = new Point[]{new Point(1, 1), new Point(1, 9), new Point(9, 1), new Point(9 , 9)};
        assertArrayEquals(points, new Circumference("5 5 3").getAnchorPoints());
        points = new Point[]{ new Point(0, 0), new Point(0, 2), new Point(4, 0), new Point(4, 2)};
        assertArrayEquals(points , new Circumference("2 0 1").getAnchorPoints());
        points = new Point[]{ new Point(1, 1), new Point(1, 5), new Point(5, 1), new Point(5, 5)};
        assertArrayEquals(points , new Circumference("3 3 1").getAnchorPoints());
    }

    @Test
    void testHasInside()
    {
        assertTrue(new Circumference("2 2 1").hasInside(new Point(2, 2)));
        assertFalse(new Circumference("2 2 1").hasInside(new Point(10, 10)));
        assertTrue(new Circumference("2 2 1").hasInside(new Point(2, 1)));
    }

    @Test
    void testIntersectsSegment()
    {
        assertTrue(new Circumference("2 2 1").intersects(new Segment(new Point(0, 0), new Point(5, 5))));
        assertFalse(new Circumference("2 2 1").intersects(new Segment(new Point(0, 0), new Point(5, 0))));
        assertTrue(new Circumference("2 2 1").intersects(new Segment(new Point(2, 10), new Point(2, 0))));
    }

    @Test
    void testIntersectsObstacle()
    {
        assertTrue(new Circumference("2 2 1").intersects(new Circumference("3 3 1")));
        assertFalse(new Circumference("5 5 3").intersects(new Circumference("5 10 1")));
        assertTrue(new Circumference("2 2 1").intersects(new Rectangle("0 0 2 0 2 2 0 2")));
        assertFalse(new Circumference("5 5 1").intersects(new Triangle("0 0 1 0 1 1")));
    }
}
