package facility.tests;

import facility.Job;
import geometry.Point;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobTest
{
    @Test
    void testConstructorsAndGetters()
    {
        Job a = new Job(new Point(1, 1), new Point(2, 3));
        Point startA = a.getStart();
        Point endA = a.getEnd();
        assertEquals(new Point(1, 1), startA);
        assertEquals(new Point(2, 3), endA);

        Job b = new Job(new Point(3, 9), new Point(5, 1));
        Point startB = b.getStart();
        Point endB = b.getEnd();
        assertEquals(new Point(3, 9), startB);
        assertEquals(new Point(5, 1), endB);

        Job c = new Job(new Point(6, 5), new Point(8, 4));
        Point startC = c.getStart();
        Point endC = c.getEnd();
        assertEquals(new Point(6, 5), startC);
        assertEquals(new Point(8, 4), endC);
    }
}