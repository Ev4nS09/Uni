package geometry;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class stores coordinates of a point on the xOy plane
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.2 22/04/2023
 * @inv Point must belong to interval (x >= 0 && x <= 1001) && (y >= 0 && y <= 999)
 */
public class Point
{
    //Package private constants
    static final double MAX_X_COORDINATE = 999;
    static final double MIN_X_COORDINATE = 0;
    static final double MAX_Y_COORDINATE = 999;
    static final double MIN_Y_COORDINATE = 0;

    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid point coordinates, both coordinates must be within range [0, 999]";

    private final double x;
    private final double y;

    /**
     * Create a point
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y)
    {
        if (x < MIN_X_COORDINATE || x > MAX_X_COORDINATE || y < MIN_Y_COORDINATE || y > MAX_Y_COORDINATE)
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.x = x;
        this.y = y;
    }

    /**
     * Gives x coordinate of a point
     * @return Double that represents x coordinate of a point
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * Gives y coordinate of a point
     * @return Double that is the y coordinate of a point
     */
    public double getY()
    {
        return this.y;
    }

    /**
     * Calculates distance between two points
     * @param p Point
     * @return Double that is the calculated distance between two points
     */
    public double distanceTo(Point p)
    {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if object is equal to point
     * @param o Object
     * @return True if object is equals to point, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        return o instanceof Point && this.x == ((Point) o).x && this.y == ((Point) o).y;
    }

    /**
     * Returns a hash value for point
     * @return Int that is point's hash value
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    /**
     * Converts point to String
     * @return String that represents point
     */
    @Override
    public String toString()
    {
        DecimalFormat pointDecimalFormat = new DecimalFormat("000");
        return pointDecimalFormat.format((int) this.x) + "," + pointDecimalFormat.format((int) this.y);
    }
}
