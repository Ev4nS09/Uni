package GUI;

import facility.Robot;
import geometry.Circumference;
import geometry.Point;
import geometry.Polygon;
import geometry.Obstacle;
import simulation.IMobileRoboticSimulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel where the facility robots and obstacles are drawn
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.1 21/05/2023
 */
public class FacilityPanel extends JPanel //User friendly
{
    private static final int PANEL_WIDTH = 1000;
    private static final int PANEL_HEIGHT = 1000;
    private static final int ROBOT_HALF_SIZE = 10;

    private final IMobileRoboticSimulation simulation;

    /**
     * Creates a new facility panel
     * @param simulation Simulation
     */
    public FacilityPanel(IMobileRoboticSimulation simulation)
    {
        this.simulation = simulation;

        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(Color.LIGHT_GRAY);
    }

    /**
     * Draw's a single circumference on panel
     * @param g2d Graphics2D
     * @param c Circumference
     */
    private void paintCircumference(Graphics2D g2d, Circumference c)
    {
        Point circumferenceCenter = c.getCenter();
        int circumferenceRadius = (int) c.getRadius();
        g2d.fillOval((int) circumferenceCenter.getX() - circumferenceRadius, PANEL_HEIGHT - (int) circumferenceCenter.getY() - circumferenceRadius,
                2 * circumferenceRadius, 2 * circumferenceRadius);
    }

    /**
     * Draw's a single polygon on panel
     * @param g2d Graphics2D
     * @param p Polygon
     */
    private void paintPolygon(Graphics2D g2d, Polygon p)
    {
        ArrayList<Point> vertices = p.getVertices();
        int nVertices = vertices.size();
        int[] xCoordinates = new int[nVertices];
        int[] yCoordinates = new int[nVertices];
        for (int i = 0; i < nVertices; i++)
        {
            xCoordinates[i] = (int) vertices.get(i).getX();
            yCoordinates[i] = PANEL_HEIGHT - (int) vertices.get(i).getY();
        }
        g2d.fillPolygon(xCoordinates, yCoordinates, nVertices);
    }

    /**
     * Draws the entirety of a simulation's map on panel
     * @param g2d Graphics2D
     */
    private void paintMap(Graphics2D g2d)
    {
        g2d.setColor(Color.DARK_GRAY);
        for (Obstacle obstacle : this.simulation.getManager().getMap())
            if (obstacle instanceof Circumference)
                this.paintCircumference(g2d, (Circumference) obstacle);
            else if (obstacle instanceof Polygon)
                this.paintPolygon(g2d, (Polygon) obstacle);
    }

    /**
     * Draws a single robot on panel
     * @param g2d Graphics2D
     * @param r Robot
     * @param id Robot's ID
     */
    private void paintRobot(Graphics2D g2d, Robot r, int id)
    {
        int x = (int) r.getCurrentPosition().getX();
        int y = (int) r.getCurrentPosition().getY();
        int[] xCoordinates = {x - ROBOT_HALF_SIZE, x + ROBOT_HALF_SIZE, x + ROBOT_HALF_SIZE, x - ROBOT_HALF_SIZE};
        int[] yCoordinates = {PANEL_HEIGHT - y - ROBOT_HALF_SIZE, PANEL_HEIGHT - y - ROBOT_HALF_SIZE,
                PANEL_HEIGHT - y + ROBOT_HALF_SIZE, PANEL_HEIGHT - y + ROBOT_HALF_SIZE};

        if (r.isAvailable())
            g2d.setColor(Color.GREEN);
        else
            g2d.setColor(Color.RED);
        g2d.fillPolygon(xCoordinates, yCoordinates, 4);
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(id), x - ROBOT_HALF_SIZE / 2, PANEL_HEIGHT - y + ROBOT_HALF_SIZE / 2); //Kinda centered???
    }

    /**
     * Draws all robots of a simulation on panel
     * @param g2d Graphics2D
     */
    private void paintRobots(Graphics2D g2d)
    {
        ArrayList<Robot> robots = this.simulation.getManager().getRobots();
        for (int i = 0; i < robots.size(); i++) //Cheat code
            paintRobot(g2d, robots.get(i), i + 1);
    }

    /**
     * {@inheritDoc}
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        this.paintRobots(g2d);
        this.paintMap(g2d);
    }
}
