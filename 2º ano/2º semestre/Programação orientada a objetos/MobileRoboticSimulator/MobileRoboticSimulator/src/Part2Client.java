import simulation.MobileRoboticSimulation;
import simulation.MobileRoboticSimulationUI;

/**
 * Class where client code for part 2 is
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.1 19/05/2023
 */
public class Part2Client
{
    public static void main(String[] args) throws InterruptedException
    {
        new MobileRoboticSimulationUI(new MobileRoboticSimulation()).startSimulation();
    }
}
