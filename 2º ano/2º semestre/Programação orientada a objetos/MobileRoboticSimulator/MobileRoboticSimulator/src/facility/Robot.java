package facility;

import geometry.Map;
import geometry.Point;
import geometry.Path;

import java.util.Iterator;
import util.ArrayDequeAsQueue;
import util.UnweightedHashGraph;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.text.DecimalFormat;

/**
 * Class that represents a robot and it's behavior
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 05/05/2023
 */
public class Robot
{
    //Package private constant
    static final int MAX_BATTERY = 10000;

    private static final int MOVE_ENERGY_COST = 10;
    private static final int RECHARGE_ENERGY_GAIN = 100;

    private final PathFinder pathFinder;
    private final Point chargingPoint;
    private final int minimumBatteryLevel;

    private Point currentPosition;
    private int currentBatteryLevel;
    private Iterator<Point> currentPath;
    private ArrayDequeAsQueue<Path> currentJobPaths;

    private boolean isWorking;
    private boolean isReturning;
    private boolean isCharging;

    /**
     * Creates a new robot with maximum battery level and with initial position as its own charging point
     * @param chargingPoint Robot's charging point
     * @param map Obstacles map
     * @param mapGraph Map's graph
     */
    public Robot(Point chargingPoint, Map map, UnweightedHashGraph<Point> mapGraph, int currentBatteryLevel)
    {
        this.pathFinder = new PathFinder(map, mapGraph);
        this.chargingPoint = chargingPoint;
        this.currentPosition = chargingPoint;
        this.currentBatteryLevel = currentBatteryLevel;
        this.minimumBatteryLevel = 0;
        this.isWorking = false;
        this.isReturning = false;
        this.isCharging = false;
    }

    /**
     * Gets robot's current position
     * @return Current's robot position
     */
    public Point getCurrentPosition()
    {
        return this.currentPosition;
    }

    public int getcurrentBatteryLevel()
    {
        return this.currentBatteryLevel;
    }

    /**
     * Checks if a robot is available
     * @return True if robot is available, false otherwise
     */
    public boolean isAvailable()
    {
        return !this.isWorking && !this.isReturning && !this.isCharging;
    }

    /**
     * Checks if a robot has low battery
     * @return True if robot has low battery, false otherwise
     */
    private boolean hasLowBattery()
    {
        return this.currentBatteryLevel <= this.minimumBatteryLevel;
    }

    /**
     * Checks if a robot has maximum battery level
     * @return True if robot has maximum battery level, false otherwise
     */
    private boolean hasMaxBattery()
    {
        return this.currentBatteryLevel == MAX_BATTERY;
    }

    /**
     * Calculates the energy cost of a path
     * @param path
     * @return Int that is a path's energy cost
     */
    private int getPathEnergyCost(Path path)
    {
        int energyCost = 0;
        if (path.size() > 0)
            energyCost = (path.size() - 1) * MOVE_ENERGY_COST;
        return energyCost;
    }

    /**
     * Takes path, assuring it is a valid path, this is, it is not empty and robot's current position is the same as the start of path
     * @param path Path
     */
    private void takePath(Path path)
    {
        if (!path.isEmpty() && path.getStart().equals(this.currentPosition))
        {
            this.currentPath = path.iterator();
            this.currentPath.next();
        }
    }

    /**
     * Calculates a path from current position to destination, and takes that exact same path
     * @param destination Destination point
     */
    private void takePathTo(Point destination)
    {
        Path toDestination = this.pathFinder.calculatePath(this.currentPosition, destination);
        takePath(toDestination);
    }

    /**
     * Makes robot go to next position, if there is one to go, otherwise it will stay at its current position
     */
    private void moveToNextPosition()
    {
        if (this.currentPath.hasNext())
        {
            this.currentPosition = this.currentPath.next();
            this.currentBatteryLevel -= MOVE_ENERGY_COST;
        }
    }

    /**
     * Checks if a robot accepts a job <br>
     * To do that, the robot calculate preemptively the paths needed to perform a job and stores them in memory if it is accepted <br>
     * If a robot accepted a job, and the method takesJob() is called, a robot will take the last accepted job, stored in memory <br>
     * Everytime this method is called, any previously calculated paths that weren't taken will be cleared and lost
     * @param j Job
     * @return A positive (including zero) integer that is job's energy cost if it accepts, returns -1 if it refuses
     */
    public int acceptsJob(Job j)
    {
        this.currentJobPaths = new ArrayDequeAsQueue<>();

        if (!this.isAvailable())
            return -1;

        Path fromCurrentPositionToStart = this.pathFinder.calculatePath(this.currentPosition, j.getStart());
        Path fromStartToEnd = this.pathFinder.calculatePath(j.getStart(), j.getEnd());
        if (fromCurrentPositionToStart.isEmpty() || fromStartToEnd.isEmpty())
            return -1;

        int jobEnergyCost = this.getPathEnergyCost(fromCurrentPositionToStart) + this.getPathEnergyCost(fromStartToEnd);
        if (this.currentBatteryLevel - jobEnergyCost < this.minimumBatteryLevel)
            return -1;

        this.currentJobPaths.enqueue(fromCurrentPositionToStart);
        this.currentJobPaths.enqueue(fromStartToEnd);
        return jobEnergyCost;
    }

    /**
     * Takes previously accepted Job by acceptsJob() method
     */
    public void takeJob()
    {
        this.takePath(this.currentJobPaths.dequeue());
        this.isWorking = true;
    }

    /**
     * Updates a robot by one step when it's available
     * A robot must be available to perform this action
     */
    private void idle()
    {
        this.currentBatteryLevel--;
        if (this.hasLowBattery())
        {
            this.takePathTo(this.chargingPoint);
            this.isReturning = true;
        }
    }

    /**
     * Updates a robot by one step when it's working
     * A robot must be working to perform this action
     */
    private void doJob()
    {
        if (!this.currentPath.hasNext())
            this.takePath(this.currentJobPaths.dequeue());
        this.moveToNextPosition();

        if (!this.currentPath.hasNext() && this.currentJobPaths.isEmpty())
            this.isWorking = false;
    }

    /**
     * Updates a robot by one step when it's returning to it's charging point
     * A robot must be returning to it's charging point to perform this action
     */
    private void returnToChargingPoint()
    {
        this.moveToNextPosition();
        if (!this.currentPath.hasNext() && this.currentPosition.equals(this.chargingPoint))
        {
            this.isReturning = false;
            this.isCharging = true;
        }
    }

    /**
     * Charges a robot by one step, if it's at is charging point <br>
     * A robot must be charging to perform this action
     */
    private void charge()
    {
        if (this.currentPosition.equals(this.chargingPoint))
        {
            this.currentBatteryLevel += Math.min(RECHARGE_ENERGY_GAIN, MAX_BATTERY - this.currentBatteryLevel);
            if (this.hasMaxBattery())
                this.isCharging = false;
        }
    }

    /**
     * Updates a robot by one step
     */
    public void update()
    {
        if (this.isAvailable())
            this.idle();
        else if (this.isWorking)
            this.doJob();
        else if (this.isReturning)
            this.returnToChargingPoint();
        else if (this.isCharging)
            this.charge();
    }

    /**
     * Converts robot to string
     * @return String that represents robot
     */
    @Override
    public String toString()
    {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
        DecimalFormat batteryDecimalFormat = new DecimalFormat("0.00", dfs); //Prints two decimal cases with "." instead of ","
        String result = "(" + this.currentPosition.toString() + "," + batteryDecimalFormat.format((double) this.currentBatteryLevel / 100) + ",";
        if (this.isCharging || this.isReturning)
            result += "-)";
        else
            result += "*)";
        return result;
    }
}
