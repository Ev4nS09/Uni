package geometry;

/**
 * Class stores a point and a double that represent the center and radius of a circumference, respectively.
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 3.1 28/04/2023
 * @inv radius >= 0
 */
public class Circumference extends Obstacle
{
    private static final double MIN_RADIUS = 0;
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid circumference radius, radius must be positive";

    private final Point center;
    private final double radius;

    /**
     * Creates a circumference
     * @param center Circumference's center
     * @param radius Circumference's radius
     */
    public Circumference(Point center, double radius)
    {
        if (radius < MIN_RADIUS)
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Creates a circumference from a formatted String
     * @param s String that represents a circumference's center x and y coordinates and radius
     */
    public Circumference(String s)
    {
        this(new Point(Integer.parseInt(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1])), Integer.parseInt(s.split(" ")[2]));
    }

    /**
     * Gets the circumference's center
     * @return Point that is circumference's center
     */
    public Point getCenter()
    {
        return this.center;
    }

    /**
     * Gets the circumference's radius
     * @return Double that is circumference's radius
     */
    public double getRadius()
    {
        return this.radius;
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of circumference type
     * @return Array of points that are circumference's anchor points
     */
    @Override
    public Point[] getAnchorPoints()
    {
        double AnchorPointsMinX = this.center.getX() - this.radius - 1;
        double AnchorPointsMinY = this.center.getY() - this.radius - 1;
        double AnchorPointsMaxX = this.center.getX() + this.radius + 1;
        double AnchorPointsMaxY = this.center.getY() + this.radius + 1;
        return this.fixAnchorPointsCoordinates(AnchorPointsMinX, AnchorPointsMinY, AnchorPointsMaxX, AnchorPointsMaxY);
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of circumference type
     * @param p Point
     * @return True if point is inside circumference, false otherwise
     */
    @Override
    public boolean hasInside(Point p)
    {
        return p.distanceTo(this.center) <= this.radius;
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of circumference type
     * @param s Segment
     * @return True if it's intersected by segment, false otherwise
     */
    @Override
    public boolean intersects(Segment s)
    {
        //Line equation: y = mx + b
        double slope = (s.getEnd().getY() - s.getStart().getY()) / (s.getEnd().getX() - s.getStart().getX()); // m value
        double lineConstant; // b value
        //Quadratic equation: ax^2 + bx + c = 0
        double a;
        double b;
        double c;
        if (Double.isInfinite(slope)) //Get values a, b and c by plugging the x constant of a vertical line on the equation of a circumference and reducing it to a quadratic equation
        {
            a = 1;
            b = - 2 * this.center.getY();
            c = Math.pow(this.center.getX(), 2) + Math.pow(this.center.getY(), 2) + Math.pow(s.getStart().getX(), 2) -
                    (2 * s.getStart().getX() * this.center.getX()) - Math.pow(this.radius, 2);
        }
        else //Get values a, b and c by plugging the line equation on the equation of a circumference and reducing it to a quadratic equation
        {
            lineConstant = s.getStart().getY() - slope * s.getStart().getX();
            a = 1 + Math.pow(slope, 2);
            b = - (2 * this.center.getX()) + (2 * (lineConstant - this.center.getY()) * slope);
            c = Math.pow(this.center.getX(), 2) + Math.pow(lineConstant - this.center.getY(), 2) - Math.pow(this.radius, 2);
        }
        double delta = b * b - 4 * a * c;
        if (delta < 0) //Line doesn't intersect anywhere, henceforth segment does not intersect
            return false;
        else //Check if intersection point is within segment
        {
            double positivePoint = (-b + Math.sqrt(delta)) / (2 * a);
            double negativePoint = (-b - Math.sqrt(delta)) / (2 * a);
            if (Double.isInfinite(slope))
                return (positivePoint <= s.getMaxYValue() && positivePoint >= s.getMinYValue()) ||
                        (negativePoint <= s.getMaxYValue() && negativePoint >= s.getMinYValue());
            else
                return (positivePoint <= s.getMaxXValue() && positivePoint >= s.getMinXValue()) ||
                        (negativePoint <= s.getMaxXValue() && negativePoint >= s.getMinXValue());
        }
    }

    /**
     * {@inheritDoc} <br>
     * Implementation for obstacles of circumference type
     * @param o Obstacle
     * @return True if it's intersected by Obstacle, false otherwise
     */
    @Override
    public boolean intersects(Obstacle o)
    {
        boolean result = false;
        if (o instanceof Circumference)
            result = this.center.distanceTo(((Circumference) o).center) <= this.radius + ((Circumference) o).radius;
        else if (o instanceof Polygon)
            result = o.intersects(this);
        return result;
    }

    /**
     * Converts circumference to String
     * @return String that represents circumference
     */
    @Override
    public String toString()
    {
        return "[" + this.center.toString() + " ; " + (int)this.radius + "]";
    }
}
