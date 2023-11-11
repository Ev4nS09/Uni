package geometry;

/**
 * Abstract class that serves to abstract all obstacle types into one class
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 3.1 28/04/2023
 */
public abstract class Obstacle
{
    /**
     * Fixes a set of anchor points coordinates so that it does not break Point's invariable condition, yet they are still created
     * @param AnchorPointsMinX Minimum value of x
     * @param AnchorPointsMinY Minimum value of y
     * @param AnchorPointsMaxX Maximum value of x
     * @param AnchorPointsMaxY Maximum value of y
     * @return Array of points that are obstacle's anchor points
     */
    protected Point[] fixAnchorPointsCoordinates(double AnchorPointsMinX, double AnchorPointsMinY, double AnchorPointsMaxX, double AnchorPointsMaxY)
    {
        Point[] anchorPoints = new Point[4];
        if (AnchorPointsMinX < Point.MIN_X_COORDINATE)
            AnchorPointsMinX = Point.MIN_X_COORDINATE;
        if (AnchorPointsMinY < Point.MIN_Y_COORDINATE)
            AnchorPointsMinY = Point.MIN_Y_COORDINATE;
        if (AnchorPointsMaxX > Point.MAX_X_COORDINATE)
            AnchorPointsMaxX = Point.MAX_X_COORDINATE;
        if (AnchorPointsMaxY > Point.MAX_Y_COORDINATE)
            AnchorPointsMaxY = Point.MAX_Y_COORDINATE;
        anchorPoints[0] = new Point(AnchorPointsMinX, AnchorPointsMinY);
        anchorPoints[1] = new Point(AnchorPointsMinX, AnchorPointsMaxY);
        anchorPoints[2] = new Point(AnchorPointsMaxX, AnchorPointsMinY);
        anchorPoints[3] = new Point(AnchorPointsMaxX, AnchorPointsMaxY);
        return anchorPoints;
    }

    /**
     * Calculates a set of outside points relative to the obstacle's position <br>
     * These points can be added to a path to make it go around an obstacle
     * @return Array of points that are obstacle's anchor points
     */
    public abstract Point[] getAnchorPoints();

    /**
     * Checks if obstacle has point inside it's area
     * @param p Point
     * @return True if point is inside obstacle, false otherwise
     */
    public abstract boolean hasInside(Point p);

    /**
     * Checks if an obstacle is intersected by segment
     * @param s Segment
     * @return True if it's intersected by segment, false otherwise
     */
    public abstract boolean intersects(Segment s);

    /**
     * Checks if an obstacle is intersected by obstacle
     * @param o Obstacle
     * @return True if it's intersected by obstacle, false otherwise
     */
    public abstract boolean intersects(Obstacle o);
}
