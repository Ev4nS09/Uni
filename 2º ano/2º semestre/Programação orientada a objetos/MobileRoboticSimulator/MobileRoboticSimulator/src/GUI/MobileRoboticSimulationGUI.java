package GUI;

import simulation.IMobileRoboticSimulation;

/**
 * Main GUI class that needs to be called by client to start a simulation
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 21/05/2023
 */
public class MobileRoboticSimulationGUI
{
    private final IMobileRoboticSimulation simulation;

    /**
     * Creates a new mobile robotic simulation GUI
     * @param simulation Simulation
     */
    public MobileRoboticSimulationGUI(IMobileRoboticSimulation simulation)
    {
        this.simulation = simulation;
    }

    /**
     * Starts the GUI
     */
    public void startSimulation()
    {
        new PreSimulationFrame(this.simulation);
    }
}
