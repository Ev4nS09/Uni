package geometry.tests;

import geometry.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PolygonTest
{
    @Test
    void testAddVertex()
    {
        Polygon polygon = new Rectangle("0 0 2 0 2 2 0 2");

        polygon.addVertex(new Point(1, 3));
        assertEquals("[000,000 ; 002,000 ; 002,002 ; 000,002 ; 001,003]", polygon.toString());

        polygon.addVertex(new Point(1, 0));
        assertEquals("[000,000 ; 002,000 ; 002,002 ; 000,002 ; 001,003 ; 001,000]", polygon.toString());

        polygon.addVertex(new Point(5, 6));
        assertEquals("[000,000 ; 002,000 ; 002,002 ; 000,002 ; 001,003 ; 001,000 ; 005,006]", polygon.toString());
    }

    @Test
    void testInvariant()
    {
        Polygon rectangle = new Rectangle("0 0 2 0 2 2 0 2");
        assertThrows(IllegalArgumentException.class, () -> rectangle.addVertex(new Point(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> rectangle.addVertex(new Point(2, 0)));
        assertThrows(IllegalArgumentException.class, () -> rectangle.addVertex(new Point(2, 2)));
    }

    @Test
    void testGetAnchorPoints()
    {
        Point[] points = new Point[]{new Point(0, 0), new Point(0, 3), new Point(3, 0), new Point(3 , 3)};
        assertArrayEquals(points , new Rectangle("1 1 1 2 2 2 2 1").getAnchorPoints());
        points = new Point[]{ new Point(0, 0), new Point(0, 4), new Point(5, 0), new Point(5, 4)};
        assertArrayEquals(points , new Rectangle("2 0 4 2 3 3 1 1").getAnchorPoints());
        points = new Point[]{ new Point(2, 1), new Point(2, 6), new Point(8, 1), new Point(8, 6)};
        assertArrayEquals(points , new Triangle("5 5 7 2 3 3").getAnchorPoints());
    }

    @Test
    void testHasInside()
    {
        Polygon rectangle = new Rectangle("0 0 2 0 2 2 0 2");
        assertTrue(rectangle.hasInside(new Point(1, 1)));
        assertTrue(rectangle.hasInside(new Point(2, 2)));
        assertFalse(rectangle.hasInside(new Point(3, 3)));

        Polygon triangle = new Triangle("1 1 2 2 1 2");
        assertTrue(triangle.hasInside(new Point(1, 1)));
        assertTrue(triangle.hasInside(new Point(2, 2)));
        assertFalse(triangle.hasInside(new Point(3, 3)));
    }

    @Test
    void testIntersectsSegment()
    {
        Polygon rectangle = new Rectangle("1 0 3 0 3 2 1 2");
        Polygon triangle = new Triangle("0 0 2 0 2 2");
        assertTrue(rectangle.intersects(new Segment(new Point(0, 1), new Point(1, 1))));
        assertTrue(triangle.intersects(new Segment(new Point(0, 0), new Point(5, 5))));
        assertFalse(triangle.intersects(new Segment(new Point(5, 5), new Point(3, 3))));
    }

    @Test
    void testIntersectsObstacle()
    {
        assertTrue(new Rectangle("0 0 1 0 1 1 0 1").intersects(new Triangle("0 0 1 0 0 1")));
        assertTrue(new Rectangle("0 0 2 0 2 2 0 2").intersects(new Circumference("2 2 1")));
        assertFalse(new Triangle("0 0 1 0 1 1").intersects(new Rectangle("5 5 6 5 6 6 5 6")));
    }
}
