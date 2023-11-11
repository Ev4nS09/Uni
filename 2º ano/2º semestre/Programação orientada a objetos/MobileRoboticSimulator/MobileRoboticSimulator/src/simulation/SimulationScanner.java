package simulation;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Class that is a simulation scanner, that runs concurrently with a simulation, waiting for input
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 2.0 08/05/2023
 */
public class SimulationScanner extends Thread
{
    private final Scanner sc;
    private boolean enterPressed;
    private boolean EOFInputted;

    /**
     * Creates a new simulation scanner with given input stream
     * @param in InputStreamReader
     */
    public SimulationScanner(InputStreamReader in)
    {
        this.sc = new Scanner(in);
        this.enterPressed = false;
        this.EOFInputted = false;
    }

    /**
     * Checks if the enter key was pressed
     * @return True if the key was pressed, false otherwise
     */
    public boolean wasEnterPressed()
    {
        return this.enterPressed;
    }

    /**
     * Checks if EOF was inputted
     * @return True if EOF was inputted, false otherwise
     */
    public boolean wasEOFInputted()
    {
        return this.EOFInputted;
    }

    /**
     * Closes this scanner
     */
    public void close()
    {
        this.sc.close();
    }

    /**
     * Updates scanner state depending on the input received
     */
    @Override
    public void run()
    {
        try
        {
            while (!this.sc.nextLine().isEmpty())
                ;
            this.enterPressed = true;
        }
        catch (Exception e)
        {
            this.EOFInputted = true;
        }
    }
}
