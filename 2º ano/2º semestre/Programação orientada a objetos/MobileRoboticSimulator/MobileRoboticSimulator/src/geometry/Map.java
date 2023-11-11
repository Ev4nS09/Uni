package geometry;

import util.UnweightedHashGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class that stores a list of obstacles
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 3.0 04/05/2023
 * @inv All obstacles must not intersect each other
 */
public class Map implements Iterable<Obstacle>
{
    private static final String INVARIANT_VIOLATION_MESSAGE = "Invalid obstacle, all obstacles must not intersect each other";

    private final ArrayList<Obstacle> obstacles;

    /**
     * Creates a new empty map
     */
    public Map()
    {
        this.obstacles = new ArrayList<>();
    }

    /**
     * Adds an obstacle to map
     * @param o Obstacles
     */
    public void addObstacle(Obstacle o)
    {
        if (this.isIntersectedBy(o))
            throw new IllegalArgumentException(INVARIANT_VIOLATION_MESSAGE);
        this.obstacles.add(o);
    }

    /**
     * Gets map's obstacle
     * @return List of obstacles
     */
    public ArrayList<Obstacle> getObstacles()
    {
        return this.obstacles;
    }

    /**
     * Gets anchor points of all obstacles of map
     * @return List of generated anchor points by each obstacle
     */
    private ArrayList<Point> getAnchorPoints()
    {
        ArrayList<Point> anchorPoints = new ArrayList<>(4 * this.obstacles.size());
        for (Obstacle obstacle : obstacles)
            anchorPoints.addAll(List.of(obstacle.getAnchorPoints()));
        return anchorPoints;
    }

    /**
     * Adds all anchor points to a graph
     * @param graph Graph
     */
    private void addAnchorPointsToGraph(UnweightedHashGraph<Point> graph)
    {
        ArrayList<Point> anchorPoints = this.getAnchorPoints();
        for (Point anchorPoint : anchorPoints)
            graph.addVertex(anchorPoint);
    }

    /**
     * Adds all connections between points to a graph
     * @param graph Graph
     */
    private void addConnectionsToGraph(UnweightedHashGraph<Point> graph)
    {
        for (Point origin : graph)
            for (Point end : graph)
                if (!origin.equals(end))
                {
                    Segment s = new Segment(origin, end);
                    if (!this.isIntersectedBy(s))
                        graph.addConnection(origin, end);
                }
    }

    /**
     * Converts a list of obstacles to a graph, to later on be used for pathfinding calculations
     * @return Graph that represents anchor points and paths that don't intersect any of the obstacles
     */
    public UnweightedHashGraph<Point> toGraph()
    {
        UnweightedHashGraph<Point> graph = new UnweightedHashGraph<>();
        this.addAnchorPointsToGraph(graph);
        this.addConnectionsToGraph(graph);
        return graph;
    }

    /**
     * Checks if obstacle has point inside it's area
     * @param p Point
     * @return True if point is inside map, false otherwise
     */
    public boolean hasInside(Point p)
    {
        boolean result = false;
        for (Obstacle obstacle : this.obstacles)
            if (obstacle.hasInside(p))
            {
                result = true;
                break;
            }
        return result;
    }

    /**
     * Checks if a map is intersected by an obstacle
     * @param o Obstacle
     * @return True if it's intersected by obstacle, false otherwise
     */
    private boolean isIntersectedBy(Obstacle o)
    {
        boolean result = false;
        for (Obstacle obstacle : this.obstacles)
            if (obstacle.intersects(o))
            {
                result = true;
                break;
            }
        return result;
    }

    /** Checks if a map is intersected by a segment
     * @param s Segment
     * @return True if it's intersected by segment, false otherwise
     */
    public boolean isIntersectedBy(Segment s)
    {
        boolean result = false;
        for (Obstacle obstacle : this.obstacles)
            if (obstacle.intersects(s))
            {
                result = true;
                break;
            }
        return result;
    }

    /**
     * Checks if a map is intersected by a path
     * @param p Path
     * @return True if it's intersected by path, false otherwise
     */
    public boolean isIntersectedBy(Path p) //Somehow this method rendered useless ???
    {
        boolean result = false;
        for (Obstacle obstacle : this.obstacles)
            if (p.intersects(obstacle))
            {
                result = true;
                break;
            }
        return result;
    }

    /**
     * Creates an iterator for map's obstacles
     * @return Iterator to be able to iterate over map's obstacles
     */
    @Override
    public Iterator<Obstacle> iterator()
    {
        return this.obstacles.iterator();
    }
}
