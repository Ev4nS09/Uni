package geometry;

/**
 * Class stores 4 points that represent a rectangle
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.1 22/04/2023
 * @inv 4 points must be different from each other and form right angles
*/
public class Rectangle extends Polygon
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid rectangle vertex/vertices, all vertices must be different form right angles";

    /**
     * Creates a rectangle
     * @param a Vertex
     * @param b Vertex
     * @param c Vertex
     * @param d Vertex
    */
    public Rectangle(Point a, Point b, Point c, Point d)
    {
        super();
        if (!formsRectangle(a, b, c, d))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.addVertex(a);
        this.addVertex(b);
        this.addVertex(c);
        this.addVertex(d);
    }

    /**
     * Creates a rectangle from a formatted String
     * @param s String that represent all four vertices of a rectangle
     */
    public Rectangle(String s)
    {
        this(
                new Point(Integer.parseInt(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1])),
                new Point(Integer.parseInt(s.split(" ")[2]), Integer.parseInt(s.split(" ")[3])),
                new Point(Integer.parseInt(s.split(" ")[4]), Integer.parseInt(s.split(" ")[5])),
                new Point(Integer.parseInt(s.split(" ")[6]), Integer.parseInt(s.split(" ")[7]))
        );
    }

    /**
     * Check if the angle abc is right
     * @param a Point
     * @param b Point
     * @param c Point
     * @return True if the angle formed is right, false otherwise
    */
    private static boolean isOrthogonal(Point a, Point b, Point c)
    {
        return !a.equals(b) && !a.equals(c) && !b.equals(c) &&
                (b.getX() - a.getX()) * (c.getX() - b.getX()) + (b.getY() - a.getY()) * (c.getY() - b.getY()) == 0;
    }

    /**
     * Check if the shape [abcd] is a rectangle
     * @param a Vertex
     * @param b Vertex
     * @param c Vertex
     * @param d Vertex
     * @return True if the shape formed is a rectangle, false otherwise
    */
    private static boolean formsRectangle(Point a, Point b, Point c, Point d)
    {
        return isOrthogonal(a, b, c) && isOrthogonal(b, c, d) && isOrthogonal(c, d, a);
    }
}
