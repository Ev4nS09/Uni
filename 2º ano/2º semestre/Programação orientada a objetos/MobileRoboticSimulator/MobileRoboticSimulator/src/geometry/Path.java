package geometry;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class stores a list of points that represent a path
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 22/04/2023
 * @inv All points of path must be different from each other
*/
public class Path implements Iterable<Point>
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid path point/points, all points must be different from each other";

    private final ArrayList<Point> points;

    /**
     * Creates new empty path
     */
    public Path()
    {
        this.points = new ArrayList<>();
    }

    /**
     * Creates a deep copy of original path
     * @param original Path
     */
    public Path(Path original)
    {
        this.points = new ArrayList<>(original.points);
    }

    /**
     * Adds point to path
     * @param p Point
    */
    public void addPoint(Point p)
    {
        if (this.points.contains(p))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.points.add(p);
    }

    /**
     * Gets the first point of path
     * @return First path's point
     */
    public Point getStart()
    {
        return this.points.get(0);
    }

    /**
     * Gets the last point of path
     * @return Last path's point
     */
    public Point getEnd()
    {
        return this.points.get(this.points.size() - 1);
    }

    /**
     * Checks if path is empty
     * @return True if path is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return this.points.size() == 0;
    }

    /**
     * Gets size of path
     * @return Int that is number of points path has
     */
    public int size()
    {
        return this.points.size();
    }

    /**
     * Calculates length of path
     * @return Double that represent the length of path
     */
    public double length()
    {
        double result = 0;
        for (int i = 0; i < this.points.size() - 1; i++)
            result += this.points.get(i).distanceTo(this.points.get(i + 1));
        return result;
    }

    /**
     * Check if a path intersects an obstacle
     * @param o Obstacle
     * @return True if path intersects obstacle, false otherwise
     */
    public boolean intersects(Obstacle o)
    {
        boolean result = false;
        for (int j = 0; j < this.points.size() - 1; j++)
        {
            Segment s = new Segment(this.points.get(j), this.points.get(j + 1));
            if (o.intersects(s))
            {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Creates an iterator for path's points
     * @return Iterator to be able to iterate over path's vertices
     */
    @Override
    public Iterator<Point> iterator()
    {
        return this.points.iterator();
    }

    /**
     * Converts path to String
     * @return String that represents path
     */
    @Override
    public String toString()
    {
        String result = "[";
        int length = this.points.size();
        if (length > 0)
        {
            for (int i = 0; i < length - 1; i++)
                result += this.points.get(i).toString() + " ; ";
            result += this.points.get(length - 1).toString();
        }
        result += "]";
        return result;
    }
}