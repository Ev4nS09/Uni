package geometry;

/**
 * Class stores 3 points that represent a triangle
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.1 22/04/2023
 * @inv Point a != Point b && Point a != Point c && Point b != Point c && Points aren't collinear
 */
public class Triangle extends Polygon
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid triangle vertex/vertices, all vertices must be different from each other and not collinear";

    /**
     * Creates a triangle
     * @param a Vertex
     * @param b Vertex
     * @param c Vertex
     */
    public Triangle(Point a, Point b, Point c)
    {
        super();
        if (!formsTriangle(a, b, c))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.addVertex(a);
        this.addVertex(b);
        this.addVertex(c);
    }

    /**
     * Creates a triangle from a formatted String
     * @param s String that represents all points of a triangle
     */
    public Triangle(String s)
    {
        this(
                new Point(Integer.parseInt(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1])),
                new Point(Integer.parseInt(s.split(" ")[2]), Integer.parseInt(s.split(" ")[3])),
                new Point(Integer.parseInt(s.split(" ")[4]), Integer.parseInt(s.split(" ")[5]))
        );
    }

    /**
     * Check points [abc] form a triangle
     * @param a Point
     * @param b Point
     * @param c Point
     * @return True if abc forms a triangle, false otherwise
     */
    private static boolean formsTriangle(Point a, Point b, Point c)
    {
        return !a.equals(b) && !a.equals(c) && !b.equals(c) &&
                (b.getY() - a.getY()) * (c.getX() - b.getX()) - (c.getY() - b.getY()) * (b.getX() - a.getX()) != 0;
    }
}
