package simulation;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * A UI for a robot simulation
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 20/05/2023
 */
public class MobileRoboticSimulationUI
{
    private static final String READ_OBSTACLES_PROMPT = "Insert obstacles: to exit press enter without typing anything";
    private static final String READ_CHARGING_POINTS_PROMPT = "Insert charging points: to exit press enter without typing anything\n" +
            "If no new points are inserted, it will use the default charging points (0,0 ; 999,0 ; 999,999 ; 0, 999)";
    private static final String START_SIMULATION_PROMPT = "Starting simulation: to enqueue a job press enter without typing anything";
    private static final String READ_JOB_PROMPT = "Insert job's start and end point";
    private static final String JOB_START_PROMPT = "Start: ";
    private static final String JOB_END_PROMPT = "End: ";
    private static final String STOP_PROMPT = "Stopping simulation";

    private static final InputStreamReader stdin = new InputStreamReader(System.in);
    private static final int PAUSE_TIME_MILLIS = 1000;

    private final IMobileRoboticSimulation simulation;
    private final Scanner scanner;
    private SimulationScanner simulationScanner;

    /**
     * Creates a new UI for given simulation
     * @param simulation Simulation
     */
    public MobileRoboticSimulationUI(IMobileRoboticSimulation simulation)
    {
        this.simulation = simulation;
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.simulationScanner = new SimulationScanner(stdin);
    }

    /**
     * Enters a reading state, reading obstacles from user and printing helpful prompts <br>
     * If an empty line is read it ends this method's execution
     */
    private void readObstacles()
    {
        System.out.println(READ_OBSTACLES_PROMPT);
        String obstacle;
        while(!(obstacle = this.scanner.nextLine()).isEmpty())
        {
            try
            {
                this.simulation.addObstacle(obstacle);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Enters a reading state, reading charging points from user and printing helpful prompts <br>
     * If an empty line is read it ends this method's execution
     */
    private void readChargingPoints()
    {
        System.out.println(READ_CHARGING_POINTS_PROMPT);
        String chargingPoint;
        while(!(chargingPoint = this.scanner.nextLine()).isEmpty())
        {
            try
            {
              this.simulation.addChargingPoint(chargingPoint);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Represents the phase before starting a simulation, ending when successfully started
     */
    private void preSimulation()
    {
        while (true)
        {
            this.readObstacles();
            this.readChargingPoints();
            try
            {
                this.simulation.start();
                System.out.println(START_SIMULATION_PROMPT);
                break; //If no exceptions are thrown break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage() + "\n");
                this.simulation.clearObstacles();
                this.simulation.clearChargingPoints();
            }
        }
    }

    /**
     * Reads a job from user, printing any helpful prompts
     */
    private void readJob()
    {
        while (true)
        {
            System.out.println(READ_JOB_PROMPT);
            System.out.print(JOB_START_PROMPT);
            String jobStart = this.scanner.nextLine();
            System.out.print(JOB_END_PROMPT);
            String jobEnd = this.scanner.nextLine();
            try
            {
                this.simulation.enqueueJob(jobStart, jobEnd);
                break; //If no exceptions are thrown break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Simulates a simulation after starting, printing a step per second <br>
     * If enter is pressed it reads a job from user, if EOF inputted it stops this function
     */
    private void simulate() throws InterruptedException
    {
        this.simulationScanner.start();
        //Step 0
        System.out.println(this.simulation);
        while (!this.simulationScanner.wasEOFInputted())
        {
            Thread.sleep(PAUSE_TIME_MILLIS);
            if (this.simulationScanner.wasEnterPressed())
            {
                this.readJob();
                this.simulationScanner = new SimulationScanner(stdin);
                this.simulationScanner.start();
            }
            if (!this.simulationScanner.wasEOFInputted())
            {
                this.simulation.update();
                System.out.println(this.simulation);
            }
        }
    }

    /**
     * Only function that needs to be called by client <br>
     * It starts the simulation going through all phases and stopping once it is over
     */
    public void startSimulation() throws InterruptedException
    {
        this.preSimulation();
        this.simulate();

        //Stopped simulating
        System.out.print(STOP_PROMPT);
        this.scanner.close();
        this.simulationScanner.close();
        System.exit(0);
    }

}
