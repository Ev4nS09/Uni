package facility.tests;

import facility.PathFinder;
import geometry.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest
{
    @Test
    void testCalculatePath()
    {
        Map map = new Map();
        PathFinder pf = new PathFinder(map, map.toGraph());

        Path calculatedPath = pf.calculatePath(new Point(0, 0), new Point(0, 0));
        assertEquals("[000,000]", calculatedPath.toString());
        assertFalse(map.isIntersectedBy(calculatedPath));

        calculatedPath = pf.calculatePath(new Point(1, 1), new Point(10, 10));
        assertEquals("[001,001 ; 010,010]", calculatedPath.toString());
        assertFalse(map.isIntersectedBy(calculatedPath));

        map.addObstacle(new Rectangle("2 0 3 0 3 2 2 2"));
        map.addObstacle(new Triangle("5 1 6 2 5 3"));
        pf = new PathFinder(map, map.toGraph());

        calculatedPath = pf.calculatePath(new Point(0, 5), new Point(6, 1));
        assertEquals("[000,005 ; 007,004 ; 006,001]", calculatedPath.toString());
        assertFalse(map.isIntersectedBy(calculatedPath));

        calculatedPath = pf.calculatePath(new Point(1, 0), new Point(7, 0));
        assertEquals("[001,000 ; 001,003 ; 007,004 ; 007,000]", calculatedPath.toString());
        assertFalse(map.isIntersectedBy(calculatedPath));

        calculatedPath = pf.calculatePath(new Point(1, 0), new Point(5, 1));
        assertEquals("[]", calculatedPath.toString());
        assertFalse(map.isIntersectedBy(calculatedPath));
    }
}