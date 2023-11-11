package facility;

import geometry.Map;
import geometry.Path;
import geometry.Point;
import geometry.Segment;
import util.ArrayDequeAsQueue;
import util.UnweightedHashGraph;
import java.util.LinkedList;

/**
 * Class that does all the calculations to find a path from point a to point b
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 04/05/2023
 */
public class PathFinder
{
    private final Map map;
    public final UnweightedHashGraph<Point> mapGraph;

    /**
     * Creates a new PathFinder
     * @param map Obstacle map
     * @param mapGraph Map's anchor points graph
     */
    public PathFinder(Map map, UnweightedHashGraph<Point> mapGraph)
    {
        this.map = map;
        this.mapGraph = mapGraph;
    }

    /**
     * Calculates initial paths from start to any point in graph
     * @param start Point
     * @return Queue of initial paths
     */
    private ArrayDequeAsQueue<Path> getPathsToGraph(Point start)
    {
        Path initialPath = new Path();
        initialPath.addPoint(start);
        ArrayDequeAsQueue<Path> paths = new ArrayDequeAsQueue<>();
        if (this.mapGraph.contains(start))
            paths.enqueue(initialPath);
        else
        {
            for (Point vertex : mapGraph)
            {
                Segment s = new Segment(start, vertex);
                if (!this.map.isIntersectedBy(s))
                {
                    Path copy = new Path(initialPath);
                    copy.addPoint(vertex);
                    paths.enqueue(copy);
                }
            }
        }
        return paths;
    }

    /**
     * Calculates a path from start to end, without intersecting any obstacle
     * @param start Point
     * @param end Point
     * @return Path from point start to point end, in case no path was found it returns an empty path
     */
    public Path calculatePath(Point start, Point end)
    {
        Path possiblePath = new Path();
        possiblePath.addPoint(start);
        if (start.equals(end))
            return possiblePath;

        Segment s = new Segment(start, end);
        if (!this.map.isIntersectedBy(s))
        {
            possiblePath.addPoint(end);
            return possiblePath;
        }
        return modifiedBreadthFirstSearch(end, this.getPathsToGraph(start));
    }

    /**
     * Enqueues to a queue of possible paths all adjacent paths from path
     * @param path Path
     * @param possiblePaths Queue of paths (must all end in a point that the graph contains)
     * @param visited List of already visited points
     */
    private void enqueueAdjacentPaths(Path path, ArrayDequeAsQueue<Path> possiblePaths, LinkedList<Point> visited)
    {
        LinkedList<Point> connections = mapGraph.getConnections(path.getEnd());
        for (Point connection : connections)
            if (!visited.contains(connection))
            {
                Path copy = new Path(path);
                copy.addPoint(connection);
                visited.add(connection);
                possiblePaths.enqueue(copy);
            }
    }

    /**
     * Performs a breadth first search through graph from given paths end points <br>
     * It is modified, so it stops as soon as it finds one valid path that reaches end point
     * @param end Point
     * @param possiblePaths Queue of paths (must all end in a point that the graph contains)
     * @return First path to reach end point, in case no path was found it returns an empty path
     */
    private Path modifiedBreadthFirstSearch(Point end, ArrayDequeAsQueue<Path> possiblePaths)
    {
        //Marks already visited points, as it could be multiple, and stores them
        LinkedList<Point> visited = new LinkedList<>();
        for (Path path : possiblePaths)
            visited.add(path.getEnd());

        Path foundPath = new Path();
        while (!possiblePaths.isEmpty())
        {
            Path possiblePath = possiblePaths.dequeue();
            Segment s = new Segment(possiblePath.getEnd(), end);
            if (!this.map.isIntersectedBy(s))
            {
                possiblePath.addPoint(end);
                foundPath = possiblePath;
                break;
            }
            this.enqueueAdjacentPaths(possiblePath, possiblePaths, visited);
        }
        return foundPath;
    }
}