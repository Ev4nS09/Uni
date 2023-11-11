package simulation;

import facility.RobotManager;

/**
 * Interface that provides a blueprint for a mobile robotic simulation
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 08/05/2023
 */
public interface IMobileRoboticSimulation
{
    /**
     * Gets simulation's manager
     * @return RobotManager
     */
    RobotManager getManager();

    /**
     * Adds an obstacle from a formatted string to a simulation <br>
     * Can only be used in the pre start state, otherwise calling this method will do nothing
     * @param s Formatted string, must be formatted like this "obstacle_type properties"
     */
    void addObstacle(String s);

    /**
     * Clears all obstacles from a simulation <br>
     * Can only be used in the pre start state, otherwise calling this method will do nothing
     */
    void clearObstacles();

    /**
     * Adds a charging point from a formatted string to a simulation <br>
     * Can only be used in the pre start state, otherwise calling this method will do nothing
     * @param s Formatted string, must be formatted like this "chargingPointX chargingPointY"
     */
    void addChargingPoint(String s);

    /**
     * Clears all charging points from a simulation <br>
     * Can only be used in the pre start state, otherwise calling this method will do nothing
     */
    void clearChargingPoints();

    /**
     * Starts a simulation, switching state if succeeding
     * Can only be used in the pre start state, otherwise calling this method will do nothing
     */
    void start();

    /**
     * Enqueues a job from two formatted strings to a simulation <br>
     * Can only be used in the post start state, otherwise calling this method will do nothing
     * @param start Formatted string of start point, must be formatted like this "startPointX startPointY"
     * @param end Formatted string of end point, must be formatted like this "endPointX endPointY"
     */
    void enqueueJob(String start, String end);

    /**
     * Updates a simulation by one step
     * Can only be used in the post start state, otherwise calling this method will do nothing
     */
    void update();
}

