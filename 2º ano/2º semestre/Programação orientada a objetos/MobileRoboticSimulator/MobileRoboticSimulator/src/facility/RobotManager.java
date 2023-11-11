package facility;

import geometry.Map;
import geometry.Point;
import util.ArrayDequeAsQueue;
import util.UnweightedHashGraph;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents a manager of robots
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.1 06/06/2023
 * @inv All charging points must be physically reachable by any robot, such as any enqueued job
 */
public class RobotManager
{
    private static final String CHARGING_POINT_INVARIANT_VIOLATION_MESSAGE = "Invalid charging point/points, all charging points must be physically reachable by any robot";
    private static final String JOB_INVARIANT_VIOLATION_MESSAGE = "Invalid job, all job points must be physically reachable by any robot";

    private static final Point[] DEFAULT_CHARGING_POINTS = new Point[]{new Point(0, 0), new Point(999, 0), new Point(999, 999), new Point(0, 999)};

    private final Map map;
    private final ArrayList<Robot> robots;
    private final ArrayDequeAsQueue<Job> jobs;

    /**
     * Creates a new robot manager with robots at given charging points and a given environment (obstacle map)
     * @param chargingPoints Robot's charging point (All points must be physically reachable by any robot)
     * @param map Obstacles map
     */
    public RobotManager(ArrayList<Point> chargingPoints, Map map)
    {
        this.map = map;
        this.robots = this.createRobots(chargingPoints, this.map);
        this.jobs = new ArrayDequeAsQueue<>();
    }

    /**
     * Creates a new robot manager with given environment (obstacle map) and default charging points, at each corner of the playground
     * @param map Obstacles map
     */
    public RobotManager(Map map)
    {
        this(new ArrayList<>(List.of(DEFAULT_CHARGING_POINTS)), map);
    }

    /**
     * Creates a list of robots
     * @param chargingPoints Robot's charging point
     * @param map Obstacles map
     * @return List of the created robots
     */
    private ArrayList<Robot> createRobots(ArrayList<Point> chargingPoints, Map map)
    {
        if (!this.areChargingPointsValid(chargingPoints))
            throw new IllegalArgumentException(CHARGING_POINT_INVARIANT_VIOLATION_MESSAGE);
        ArrayList<Robot> robotsTemp = new ArrayList<>(chargingPoints.size());
        UnweightedHashGraph<Point> mapGraph = map.toGraph();
        for (Point chargingPoint : chargingPoints)
            robotsTemp.add(new Robot(chargingPoint, map, mapGraph));
        return robotsTemp;
    }

    /**
     * Gets manager's robots
     * @return List of robots
     */
    public ArrayList<Robot> getRobots()
    {
        return this.robots;
    }

    /**
     * Gets manager's map of obstacles
     * @return Map
     */
    public Map getMap()
    {
        return this.map;
    }

    /**
     * Creates and adds a job to a queue of jobs for the robots to perform
     * @param jobStart Start point job
     * @param jobEnd End point job
     */
    public void enqueueJob(Point jobStart, Point jobEnd)
    {
        if (this.map.hasInside(jobStart) || this.map.hasInside(jobEnd))
            throw new IllegalArgumentException(JOB_INVARIANT_VIOLATION_MESSAGE);
        this.jobs.enqueue(new Job(jobStart, jobEnd));
    }

    /**
     * Calculates which robot is better to take a certain job
     * @param j Job the robot has to take
     * @return Robot best suited to accept given job
     */
    private Robot getIdealRobot(Job j)
    {
        Robot idealRobot = null;
        int minimumEnergyCost = Robot.MAX_BATTERY + 1; //Initial value that any returned value by acceptsJob() should be smaller
        int energyCost;
        for (Robot robot : this.robots)
            if (robot.isAvailable() && (energyCost = robot.acceptsJob(j)) != -1 && energyCost < minimumEnergyCost)
            {
                idealRobot = robot;
                minimumEnergyCost = energyCost;
            }
        return idealRobot;
    }

    /**
     * Assigns queued job to a robot, if there's any job to do <br>
     * If all robots refuse a job, the same is put at the end of the queue
     */
    private void assignsQueuedJob()
    {
        int nIterations = Math.min(this.jobs.size(), this.robots.size());
        for (int i = 0; i < nIterations; i++)
        {
            Job job = this.jobs.dequeue();
            Robot robot = this.getIdealRobot(job);
            if (robot != null)
                robot.takeJob();
        }
    }

    /**
     * Updates the manager and all it's robots by one step
     */
    public void update()
    {
        assignsQueuedJob();
        for (Robot robot : this.robots)
            robot.update();
    }

    /**
     * Checks if given charging points are valid, this is, they are reachable by any robot
     * @param chargingPoints list of charging points
     * @return True if they are valid, false otherwise
     */
    private boolean areChargingPointsValid(ArrayList<Point> chargingPoints)
    {
        boolean result = true;
        for (Point chargingPoint : chargingPoints)
            if (this.map.hasInside(chargingPoint))
            {
                result = false;
                break;
            }
        return result;
    }

    /**
     * Converts robots to string
     * @return String that represents all the robots
     */
    @Override
    public String toString()
    {
        String result = "";
        for (Robot robot : this.robots)
            result += robot.toString();
        return result;
    }
}
