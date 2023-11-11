package geometry;

/**
 * Class stores two points that represent a line segment
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 3.0 22/04/2023
 * @inv Points must be different (start != end)
*/
public class Segment
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid segment points, points must be different";

    private final Point start;
    private final Point end;

    /**
     * Creates a line segment
     * @param start Point
     * @param end Point
    */
    public Segment(Point start, Point end)
    {
        if (start.equals(end))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets start point of segment
     * @return Point that represents start point of given segment
     */
    public Point getStart()
    {
        return this.start;
    }

    /**
     * Gets end point of segment
     * @return Point that represents end point of given segment
     */
    public Point getEnd()
    {
        return this.end;
    }

    /**
     * Gets max x value of segment
     * @return Double that represent max x value of segment
     */
    public double getMaxXValue()
    {
        return Math.max(this.start.getX(), this.end.getX());
    }

    /**
     * Gets min x value of segment
     * @return Double that represent min x value of segment
     */
    public double getMinXValue()
    {
        return Math.min(this.start.getX(), this.end.getX());
    }

    /**
     * Gets max y value of segment
     * @return Double that represent max y value of segment
     */
    public double getMaxYValue()
    {
        return Math.max(this.start.getY(), this.end.getY());
    }

    /**
     * Gets min y value of segment
     * @return Double that represent min y value segment
     */
    public double getMinYValue()
    {
        return Math.min(this.start.getY(), this.end.getY());
    }

    /**
     * Calculates the orientation of 3 points, two belonging to a segment and another point
     * @param p Point
     * @return 0 if collinear, 1 if clockwise, -1 if counterclockwise
    */
    private int orientation(Point p)
    {
        int result;
        double slopeSubtraction = (this.end.getY() - this.start.getY()) * (p.getX() - this.end.getX()) - (p.getY() - this.end.getY()) * (this.end.getX() - this.start.getX());
        if (slopeSubtraction == 0)
            result = 0;
        else if (slopeSubtraction > 0)
            result = 1;
        else
            result = -1;
        return result;
    }

    /**
     * Check if two lines intersect, using orientation of points
     * @param s Segment
     * @return True if line segments intersect, return false otherwise
    */
    public boolean intersects(Segment s)
    {
        double o1 = this.orientation(s.start);
        double o2 = this.orientation(s.end);
        double o3 = s.orientation(this.start);
        double o4 = s.orientation(this.end);
        if (o1 != o2 & o3 != o4)
            return true;
        else if (o1 == 0 && o1 == o2 && this.start.getX() == s.start.getX())
            return Math.max(this.getMinYValue(), s.getMinYValue()) <= Math.min(this.getMaxYValue(), s.getMaxYValue());
        else if (o1 == 0 && o1 == o2 && this.start.getY() == s.start.getY())
            return Math.max(this.getMinXValue(), s.getMinXValue()) <= Math.min(this.getMaxXValue(), s.getMaxXValue());
        else
            return false;
    }

    /**
     * Converts segment to String
     * @return String that represents segment
     */
    @Override
    public String toString()
    {
        return "[" + this.start.toString() + " ; " + this.end.toString() + "]";
    }
}