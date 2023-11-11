package facility.tests;

import facility.Job;
import facility.Robot;
import geometry.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RobotTest
{
    @Test
    void testConstructor()
    {
        Robot robot = new Robot(new Point(0, 0), new Map(), new Map().toGraph());
        assertEquals("(000,000,100.00,*)", robot.toString());
        robot = new Robot(new Point(727, 727), new Map(), new Map().toGraph());
        assertEquals("(727,727,100.00,*)", robot.toString());
        robot = new Robot(new Point(10, 20), new Map(), new Map().toGraph());
        assertEquals("(010,020,100.00,*)", robot.toString());
    }

    @Test
    void testAcceptsJob()
    {
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        Robot robot = new Robot(new Point(0, 0), map, map.toGraph());
        assertEquals(20, robot.acceptsJob(new Job(new Point(0, 0), new Point(11, 2))));
        assertEquals(40, robot.acceptsJob(new Job(new Point(11, 2), new Point(0, 3))));

        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        robot = new Robot(new Point(0, 0), map, map.toGraph());
        assertEquals(40, robot.acceptsJob(new Job(new Point(5, 7), new Point(2, 1))));

        map.addObstacle(new Circumference("7 6 2"));
        robot = new Robot(new Point(5, 0), map, map.toGraph());
        assertEquals(50, robot.acceptsJob(new Job(new Point(8, 8), new Point(5, 4))));

        assertEquals(-1, robot.acceptsJob(new Job(new Point(8, 8), new Point(7, 6)))); //In reality this won't happen, but it makes it easier to test it this way

        //Tests where it won't accept a job by battery level will be made in another testing function
    }

    //In the following tests we will test the dynamic behavior of a robot
    @Test
    void testRobotDynamicBehavior0()
    {
        //Creating playground
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        map.addObstacle(new Circumference("7 6 2"));

        //Creating robot
        Robot robot = new Robot(new Point(0, 0), map, map.toGraph());
        assertEquals("(000,000,100.00,*)", robot.toString());

        assertEquals(50, robot.acceptsJob(new Job(new Point(8, 8), new Point(5, 4))));

        //Testing takes and doing job
        robot.takeJob();
        assertFalse(robot.isAvailable());
        robot.update();
        assertEquals("(008,000,99.90,*)", robot.toString());
        robot.update();
        assertEquals("(010,009,99.80,*)", robot.toString());
        robot.update();
        assertEquals("(008,008,99.70,*)", robot.toString());
        robot.update();
        assertEquals("(004,009,99.60,*)", robot.toString());
        robot.update();
        assertEquals("(005,004,99.50,*)", robot.toString());
        assertTrue(robot.isAvailable());
        robot.update();
        assertEquals("(005,004,99.49,*)", robot.toString());
        robot.update();
        assertEquals("(005,004,99.48,*)", robot.toString());
    }

    @Test
    void testRobotDynamicBehavior1()
    {
        //Creating playground
        Map map = new Map();
        map.addObstacle(new Rectangle("3 1 7 1 7 3 3 3"));
        map.addObstacle(new Triangle("2 5 4 7 4 4"));
        map.addObstacle(new Circumference("7 6 2"));

        //Creating robot
        Robot robot = new Robot(new Point(0, 0), map, map.toGraph());

        //Decay battery
        for (int i = 0; i < 9839; i++)
            robot.update();
        assertEquals("(000,000,1.61,*)", robot.toString());

        assertEquals(20, robot.acceptsJob(new Job(new Point(0, 0), new Point(5, 4))));
        robot.takeJob();
        assertFalse(robot.isAvailable());
        robot.update();
        assertEquals("(001,003,1.51,*)", robot.toString());
        robot.update();
        assertEquals("(005,004,1.41,*)", robot.toString());
        assertTrue(robot.isAvailable());

        //Testing refusing a job by low energy levels, returning to charging point and charging
        assertEquals(-1, robot.acceptsJob(new Job(new Point(5, 4), new Point(5, 5))));
        robot.update();
        assertFalse(robot.isAvailable());
        assertEquals("(005,004,1.40,-)", robot.toString());
        robot.update();
        assertEquals("(001,003,1.30,-)", robot.toString());
        robot.update();
        assertEquals("(000,000,1.20,-)", robot.toString());
        robot.update();
        assertEquals("(000,000,2.20,-)", robot.toString());

        //Charging battery to maximum
        for (int i = 0; i < 98; i++)
            robot.update();
        assertEquals("(000,000,100.00,*)", robot.toString());
        assertTrue(robot.isAvailable());

        robot.update();
        assertEquals("(000,000,99.99,*)", robot.toString());
    }
}