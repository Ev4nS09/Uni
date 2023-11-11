package simulation;

import geometry.Map;
import geometry.Point;
import geometry.Obstacle;
import facility.RobotManager;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.util.InputMismatchException;

/**
 * Class that represents a robot simulation <br>
 * This simulation has two states, a pre start and a post start
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 08/05/2023
 */
public class MobileRoboticSimulation implements IMobileRoboticSimulation
{
    private static final String GEOMETRY_PACKAGE = "geometry.";
    private static final String UNKNOWN_OBSTACLE = "Unknown obstacle";
    private static final String UNKNOWN_CHARGING_POINT = "Unknown charging point";
    private static final String UNKNOWN_JOB = "Unknown job";

    private Map tempObstacles;
    private ArrayList<Point> tempChargingPoints;

    private RobotManager robotManager;
    private int elapsedSteps;
    private boolean hasStarted;

    /**
     * Creates a new simulation
     */
    public MobileRoboticSimulation()
    {
        this.tempObstacles = new Map();
        this.tempChargingPoints = new ArrayList<>();

        this.elapsedSteps = 0;
        this.hasStarted = false;
    }

    /**
     * {@inheritDoc}
     * @return RobotManager
     */
    @Override
    public RobotManager getManager()
    {
        return this.robotManager;
    }

    /**
     * {@inheritDoc}
     * @param s Formatted string, must be formatted like this "obstacle_type properties"
     */
    @Override
    public void addObstacle(String s)
    {
        if (!this.hasStarted)
        {
            Obstacle obstacle;
            try
            {
                String[] splitObstacle = s.split(" ", 2);
                Class<?> cl = Class.forName(GEOMETRY_PACKAGE + splitObstacle[0]);
                Constructor<?> constructor = cl.getConstructor(String.class);
                obstacle = (Obstacle) constructor.newInstance(splitObstacle[1]);
            }
            catch (Exception e)
            {
                throw new InputMismatchException(UNKNOWN_OBSTACLE);
            }
            this.tempObstacles.addObstacle(obstacle);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearObstacles()
    {
        if (!this.hasStarted)
            this.tempObstacles = new Map();
    }

    /**
     * {@inheritDoc}
     * @param s Formatted string, must be formatted like this "chargingPointX chargingPointY"
     */
    @Override
    public void addChargingPoint(String s)
    {
        if (!this.hasStarted)
        {
            int chargingPointX;
            int chargingPointY;
            try
            {
                String[] splitPoint = s.split(" ", 2);
                chargingPointX = Integer.parseInt(splitPoint[0]);
                chargingPointY = Integer.parseInt(splitPoint[1]);
            }
            catch (Exception e)
            {
                throw new InputMismatchException(UNKNOWN_CHARGING_POINT);
            }
            Point chargingPoint = new Point(chargingPointX, chargingPointY);
            this.tempChargingPoints.add(chargingPoint);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearChargingPoints()
    {
        if (!this.hasStarted)
            this.tempChargingPoints = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start()
    {
        if (!this.hasStarted)
        {
            if (tempChargingPoints.size() == 0)
                this.robotManager = new RobotManager(this.tempObstacles);
            else
                this.robotManager = new RobotManager(this.tempChargingPoints, this.tempObstacles);
            this.hasStarted = true;
        }
    }

    /**
     * {@inheritDoc}
     * @param start Formatted string of start point, must be formatted like this "startPointX startPointY"
     * @param end Formatted string of end point, must be formatted like this "endPointX endPointY"
     */
    @Override
    public void enqueueJob(String start, String end)
    {
        if (this.hasStarted)
        {
            int startPointX;
            int startPointY;
            int endPointX;
            int endPointY;
            try
            {
                String[] splitStart = start.split(" ", 2);
                String[] splitEnd = end.split(" ", 2);
                startPointX = Integer.parseInt(splitStart[0]);
                startPointY = Integer.parseInt(splitStart[1]);
                endPointX = Integer.parseInt(splitEnd[0]);
                endPointY = Integer.parseInt(splitEnd[1]);
            }
            catch (Exception e)
            {
                throw new InputMismatchException(UNKNOWN_JOB);
            }
            Point startPoint = new Point(startPointX, startPointY);
            Point endPoint = new Point(endPointX, endPointY);
            this.robotManager.enqueueJob(startPoint, endPoint);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {
        if (this.hasStarted)
        {
            robotManager.update();
            this.elapsedSteps++;
        }
    }

    /**
     * Converts simulation's current step to string
     * @return String that represents current simulation's step
     */
    @Override
    public String toString()
    {
        String result = this.elapsedSteps + ": ";
        if (this.hasStarted)
            result += this.robotManager.toString();
        return result;
    }
}
