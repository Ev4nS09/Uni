package geometry.tests;

import geometry.Rectangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest
{
    @Test
    void testConstructor()
    {
        assertEquals("[004,004 ; 006,004 ; 006,006 ; 004,006]", new Rectangle("4 4 6 4 6 6 4 6").toString());
        assertEquals("[015,002 ; 018,005 ; 016,007 ; 013,004]", new Rectangle("15 2 18 5 16 7 13 4").toString());
        assertEquals("[000,000 ; 000,004 ; 004,004 ; 004,000]", new Rectangle("0 0 0 4 4 4 4 0").toString());
    }

    @Test
    void testInvariant()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle("1 0 0 0 5 3 2 3"));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle("0 0 9 3 1 5 6 0"));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle("8 3 1 6 1 2 6 8"));
    }
}