package geometry.tests;

import geometry.Triangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest
{
    @Test
    void testConstructor()
    {
        assertEquals("[004,004 ; 006,004 ; 006,006]", new Triangle("4 4 6 4 6 6").toString());
        assertEquals("[015,002 ; 018,005 ; 016,007]", new Triangle("15 2 18 5 16 7").toString());
        assertEquals("[000,000 ; 000,004 ; 004,004]", new Triangle("0 0 0 4 4 4").toString());
    }

    @Test
    void testInvariant()
    {
        assertThrows(IllegalArgumentException.class, () -> new Triangle("1 0 2 0 3 0"));
        assertThrows(IllegalArgumentException.class, () -> new Triangle("0 0 0 0 0 0"));
        assertThrows(IllegalArgumentException.class, () -> new Triangle("1 0 0 0 1 0"));
    }
}