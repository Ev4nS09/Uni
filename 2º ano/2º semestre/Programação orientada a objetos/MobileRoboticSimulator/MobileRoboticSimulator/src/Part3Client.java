import GUI.MobileRoboticSimulationGUI;
import simulation.MobileRoboticSimulation;

/**
 * Class where client code for part 3 is
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 19/05/2023
 */
public class Part3Client
{
    public static void main(String[] args)
    {
        new MobileRoboticSimulationGUI(new MobileRoboticSimulation()).startSimulation();
    }
}
