package geometry.tests;

import geometry.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTest
{
    @Test
    void testConstructor()
    {
        Segment segment1 = new Segment(new Point(1, 0), new Point(2, 1));
        assertEquals("[001,000 ; 002,001]", segment1.toString());
        Segment segment2 = new Segment(new Point(0, 0), new Point(1, 1));
        assertEquals("[000,000 ; 001,001]", segment2.toString());
        Segment segment3 = new Segment(new Point(1, 0), new Point(3, 2));
        assertEquals("[001,000 ; 003,002]", segment3.toString());
    }

    @Test
    void testInvariant()
    {
        assertThrows(IllegalArgumentException.class, () -> new Segment(new Point(1, 1), new Point(1, 1)));
        assertThrows(IllegalArgumentException.class, () -> new Segment(new Point(5, 5), new Point(5, 5)));
        assertThrows(IllegalArgumentException.class, () -> new Segment(new Point(2, 2), new Point(2, 2)));
    }

    @Test
    void testGetters()
    {
        Segment segment1 = new Segment(new Point(2, 3), new Point(5, 8));
        assertEquals(new Point(2, 3).toString(), segment1.getStart().toString());
        assertEquals(new Point(5, 8).toString(), segment1.getEnd().toString());
        assertEquals(5, segment1.getMaxXValue());
        assertEquals(2, segment1.getMinXValue());
        assertEquals(8, segment1.getMaxYValue());
        assertEquals(3, segment1.getMinYValue());

        Segment segment2 = new Segment(new Point(5, 2), new Point(3, 7));
        assertEquals(new Point(5, 2).toString(), segment2.getStart().toString());
        assertEquals(new Point(3, 7).toString(), segment2.getEnd().toString());
        assertEquals(5, segment2.getMaxXValue());
        assertEquals(3, segment2.getMinXValue());
        assertEquals(7, segment2.getMaxYValue());
        assertEquals(2, segment2.getMinYValue());

        Segment segment3 = new Segment(new Point(0, 0), new Point(2, 0));
        assertEquals(new Point(0, 0).toString(), segment3.getStart().toString());
        assertEquals(new Point(2, 0).toString(), segment3.getEnd().toString());
        assertEquals(2, segment3.getMaxXValue());
        assertEquals(0, segment3.getMinXValue());
        assertEquals(0, segment3.getMaxYValue());
        assertEquals(0, segment3.getMinYValue());
    }

    @Test
    void testIntersectsSegment()
    {
        Segment segment1 = new Segment(new Point(0, 0), new Point(1, 1));
        Segment segment2 = new Segment(new Point(1, 0), new Point(0, 1));
        assertTrue(segment1.intersects(segment2));

        Segment segment3 = new Segment(new Point(2, 0), new Point(2, 1));
        Segment segment4 = new Segment(new Point(3, 0), new Point(3, 1));
        assertFalse(segment3.intersects(segment4));

        Segment segment5 = new Segment(new Point(5, 1), new Point(4, 2));
        Segment segment6 = new Segment(new Point(6, 3), new Point(3, 1));
        assertTrue(segment5.intersects(segment6));
    }
}