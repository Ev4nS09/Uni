package facility;

import geometry.Point;

/**
 * Class that stores the start and end of the job
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 02/05/2023
 */
public class Job
{
    private final Point start;
    private final Point end;

    /**
     * Creates the start and end points of the job
     * @param start Point
     * @param end Point
     */
    public Job(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates start point of job
     * @return Point that represents start point of given job
     */
    public Point getStart()
    {
        return this.start;
    }

    /**
     * Creates end point of job
     * @return Point that represents end point of given job
     */
    public Point getEnd()
    {
        return this.end;
    }
}
