package facility.tests;

import facility.RobotManager;
import geometry.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RobotManagerTest
{
    @Test
    void testConstructor()
    {
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        map.addObstacle(new Circumference("7 6 2"));

        RobotManager rm = new RobotManager(map);
        assertEquals("(000,000,100.00,*)(999,000,100.00,*)(999,999,100.00,*)(000,999,100.00,*)", rm.toString());

        ArrayList<Point> chargingPoints = new ArrayList<>();
        chargingPoints.add(new Point(1, 2));
        chargingPoints.add(new Point(4, 9));
        chargingPoints.add(new Point(10, 11));
        chargingPoints.add(new Point(12, 1));

        rm = new RobotManager(chargingPoints, map);
        assertEquals("(001,002,100.00,*)(004,009,100.00,*)(010,011,100.00,*)(012,001,100.00,*)", rm.toString());

        chargingPoints.add(new Point(727, 727));

        rm = new RobotManager(chargingPoints, map);
        assertEquals("(001,002,100.00,*)(004,009,100.00,*)(010,011,100.00,*)(012,001,100.00,*)(727,727,100.00,*)", rm.toString());
    }

    @Test
    void testInvariant()
    {
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        map.addObstacle(new Circumference("7 6 2"));

        ArrayList<Point> chargingPoints = new ArrayList<>();
        chargingPoints.add(new Point(3, 1));
        chargingPoints.add(new Point(7, 1));
        chargingPoints.add(new Point(7, 3));
        chargingPoints.add(new Point(3, 3));

        assertThrows(IllegalArgumentException.class, () -> new RobotManager(chargingPoints, map));

        RobotManager rm = new RobotManager(map);

        assertThrows(IllegalArgumentException.class, () -> rm.enqueueJob(new Point(3, 1), new Point(10, 10)));
        assertThrows(IllegalArgumentException.class, () -> rm.enqueueJob(new Point(11, 0), new Point(8, 6)));
        assertThrows(IllegalArgumentException.class, () -> rm.enqueueJob(new Point(3, 1), new Point(8, 6)));
    }

    //In the following tests we will test the dynamic behavior of a robot manager
    @Test
    void testRobotDynamicBehavior0()
    {
        //Creating playground
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        map.addObstacle(new Circumference("7 6 2"));

        //Creating manager
        ArrayList<Point> chargingPoints = new ArrayList<>();
        chargingPoints.add(new Point(0, 0));
        chargingPoints.add(new Point(10, 0));
        chargingPoints.add(new Point(10, 9));
        chargingPoints.add(new Point(0, 9));
        RobotManager rm = new RobotManager(chargingPoints, map);
        assertEquals("(000,000,100.00,*)(010,000,100.00,*)(010,009,100.00,*)(000,009,100.00,*)", rm.toString());
        rm.update();
        assertEquals("(000,000,99.99,*)(010,000,99.99,*)(010,009,99.99,*)(000,009,99.99,*)", rm.toString());

        //Enqueues two jobs
        rm.enqueueJob(new Point(8, 8), new Point(5, 4));
        rm.enqueueJob(new Point(8, 8), new Point(5, 4));
        rm.update();
        assertEquals("(000,000,99.98,*)(010,000,99.98,*)(008,008,99.89,*)(008,008,99.89,*)", rm.toString());
        rm.update();
        assertEquals("(000,000,99.97,*)(010,000,99.97,*)(004,009,99.79,*)(004,009,99.79,*)", rm.toString());
        rm.update();
        assertEquals("(000,000,99.96,*)(010,000,99.96,*)(005,004,99.69,*)(005,004,99.69,*)", rm.toString());
        rm.update();
        assertEquals("(000,000,99.95,*)(010,000,99.95,*)(005,004,99.68,*)(005,004,99.68,*)", rm.toString());
    }
}