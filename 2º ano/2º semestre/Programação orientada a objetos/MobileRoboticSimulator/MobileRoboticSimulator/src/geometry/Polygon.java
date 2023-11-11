package geometry;

import java.util.ArrayList;

/**
 * Class stores list of points that represent a polygon
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 3.3 28/04/2023
 * @inv All vertices must be different from each other
 */
public class Polygon extends Obstacle
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid polygon vertex/vertices, all vertices must be different from each other";

    protected final ArrayList<Point> vertices;

    /**
     * Creates and empty Polygon
     */
    public Polygon()
    {
        this.vertices = new ArrayList<>();
    }
    
    /**
     * Adds points as a vertex to Polygon
     * @param p Point
     */
    public void addVertex(Point p)
    {
        if(this.vertices.contains(p))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.vertices.add(p);
    }

    /**
     * Gets polygon's vertices
     * @return List of vertices
     */
    public ArrayList<Point> getVertices()
    {
        return this.vertices;
    }

    /**
     * Calculates the maximum value of x that a vertex from polygon takes
     * @return Double that is polygon's max x value
     */
    private double getMaxXValue()
    {
        double result = this.vertices.get(0).getX();
        for (int i = 1; i < this.vertices.size(); i++)
            if (this.vertices.get(i).getX() > result)
                result = this.vertices.get(i).getX();
        return result;
    }

    /**
     * Calculates the maximum value of y that a vertex from polygon takes
     * @return Double that is polygon's max y value
     */
    private double getMaxYValue()
    {
        double result = this.vertices.get(0).getY();
        for (int i = 1; i < this.vertices.size(); i++)
            if (this.vertices.get(i).getY() > result)
                result = this.vertices.get(i).getY();
        return result;
    }

    /**
     * Calculates the minimum value of x that a vertex from polygon takes
     * @return Double that is polygon's minimum x value
     */
    private double getMinXValue()
    {
        double result = this.vertices.get(0).getX();
        for (int i = 1; i < this.vertices.size(); i++)
            if (this.vertices.get(i).getX() < result)
                result = this.vertices.get(i).getX();
        return result;
    }

    /**
     * Calculates the minimum value of y that a vertex from polygon takes
     * @return Double that is polygon's minimum y value
     */
    private double getMinYValue()
    {
        double result = this.vertices.get(0).getY();
        for (int i = 1; i < this.vertices.size(); i++)
            if (this.vertices.get(i).getY() < result)
                result = this.vertices.get(i).getY();
        return result;
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of polygon type
     * @return Array of points that are polygon's anchor points
     */
    @Override
    public Point[] getAnchorPoints() //TODO: 30/04/2023 Polygon's anchor points position to be more effective
    {
        double AnchorPointsMinX = this.getMinXValue() - 1;
        double AnchorPointsMinY = this.getMinYValue() - 1;
        double AnchorPointsMaxX = this.getMaxXValue() + 1;
        double AnchorPointsMaxY = this.getMaxYValue() + 1;
        return this.fixAnchorPointsCoordinates(AnchorPointsMinX, AnchorPointsMinY, AnchorPointsMaxX, AnchorPointsMaxY);
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of polygon type
     * @param p Point
     * @return True if point is inside polygon, false otherwise
     */
    @Override
    public boolean hasInside(Point p)
    {
        if (this.vertices.contains(p))
            return true;

        boolean result = true;
        Point[] anchorPoints = this.getAnchorPoints();
        for (Point anchorPoint : anchorPoints)
        {
            if (anchorPoint.equals(p))
            {
                result = false;
                break;
            }

            Segment s = new Segment(anchorPoint, p);
            if (!this.intersects(s))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of polygon type
     * @param s Segment
     * @return True if it's intersected by segment, false otherwise
     */
    @Override
    public boolean intersects(Segment s)
    {
        boolean result = false;
        int nVertices = this.vertices.size();
        for (int i = 0; i < nVertices; i++)
            if (s.intersects(new Segment(this.vertices.get(i), this.vertices.get((i + 1) % nVertices))))
            {
                result = true;
                break;
            }
        return result;
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of polygon type
     * @param o Obstacle
     * @return True if it's intersected by Obstacle, false otherwise
     */
    @Override
    public boolean intersects(Obstacle o)
    {
        boolean result = o.intersects(new Segment(this.vertices.get(0), this.vertices.get(this.vertices.size() - 1)));
        if (!result)
        {
            Path polygonPath = this.toPath();
            result = polygonPath.intersects(o);
        }
        return result;
    }

    /**
     * Converts a polygon to a path that goes by each vertex <br>
     * Later used to see if a polygon intersects an obstacle
     * @return Path that represents polygon
     */
    private Path toPath()
    {
        Path result = new Path();
        for (Point vertex : this.vertices)
            result.addPoint(vertex);
        return result;
    }

    /**
     * Converts polygon to String
     * @return String that represents polygon
     */
    @Override
    public String toString()
    {
        String result = "[";
        if (this.vertices.size() > 0)
        {
            for (int i = 0; i < this.vertices.size() - 1; i++)
                result += this.vertices.get(i).toString() + " ; ";
            result += this.vertices.get(this.vertices.size() - 1).toString();
        }
        result += "]";
        return result;
    }
}
