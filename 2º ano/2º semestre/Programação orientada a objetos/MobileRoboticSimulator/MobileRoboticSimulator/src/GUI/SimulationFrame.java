package GUI;

import facility.Robot;
import simulation.IMobileRoboticSimulation;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Main frame where a simulation occurs, once stopped it will end the program
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 21/05/2023
 */
public class SimulationFrame extends JFrame
{
    private static final int GAP = 10; //Slight pixel offset from window border and other components
    private static final int WINDOW_WIDTH = 1300;
    private static final int WINDOW_HEIGHT = 1000;
    private static final int SIDE_UI_WIDTH = 300;
    private static final int COMPONENT_WIDTH = 200;
    private static final int COMPONENT_HEIGHT = 25;
    private static final int BUTTON_WIDTH = COMPONENT_WIDTH / 2;
    private static final int PAUSE_TIME_MILLIS = 1500;

    private static final String JOB_START = "Job start:";
    private static final String JOB_END = "Job end:";
    private static final String SUBMIT_JOB = "Submit job";
    private static final String STOP = "Stop";

    private final IMobileRoboticSimulation simulation;

    private FacilityPanel facilityPanel;
    private JLabel[] robotLabels;
    private JLabel jobStartLabel, jobEndLabel;
    private JTextField jobStartTextField, jobEndTextField;
    private JButton stopButton, submitJobButton;
    private ExceptionFrame exceptionFrame;
    private Timer timer;

    /**
     * Creates a new simulation frame
     * @param simulation Simulation
     */
    public SimulationFrame(IMobileRoboticSimulation simulation)
    {
        this.simulation = simulation;

        this.startWindow();
    }

    /**
     * Creates the robot labels
     */
    private void createRobotLabels()
    {
        ArrayList<Robot> robots = this.simulation.getManager().getRobots();
        this.robotLabels = new JLabel[robots.size()];
        for (int i = 0; i < this.robotLabels.length; i++)
        {
            this.robotLabels[i] = new JLabel(i + 1 + ": " + robots.get(i).toString());
            this.robotLabels[i].setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - COMPONENT_WIDTH / 2, GAP + i * COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
            this.robotLabels[i].setHorizontalAlignment(JLabel.CENTER);
            this.robotLabels[i].setVerticalAlignment(JLabel.CENTER);
        }
    }

    /**
     * Updates the robot labels
     */
    private void updateRobotLabels()
    {
        ArrayList<Robot> robots = this.simulation.getManager().getRobots();
        for (int i = 0; i < this.robotLabels.length; i++)
            this.robotLabels[i].setText(i + 1 + ": " + robots.get(i).toString());
    }

    /**
     * Creates the job start label
     */
    private void createJobStartLabel()
    {
        this.jobStartLabel = new JLabel(JOB_START);
        this.jobStartLabel.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - COMPONENT_WIDTH / 2, WINDOW_HEIGHT / 2 - 3 * COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        this.jobStartLabel.setHorizontalAlignment(JLabel.LEFT);
        this.jobStartLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Creates the job start text field
     */
    private void createJobStartTextField()
    {
        this.jobStartTextField = new JTextField();
        this.jobStartTextField.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - COMPONENT_WIDTH / 2, WINDOW_HEIGHT / 2 - 2 * COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
    }

    /**
     * Creates the job end label
     */
    private void createJobEndLabel()
    {
        this.jobEndLabel = new JLabel(JOB_END);
        this.jobEndLabel.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - COMPONENT_WIDTH / 2, WINDOW_HEIGHT / 2 - COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        this.jobEndLabel.setHorizontalAlignment(JLabel.LEFT);
        this.jobEndLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Creates the job end text field
     */
    private void createJobEndTextField()
    {
        this.jobEndTextField = new JTextField();
        this.jobEndTextField.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - COMPONENT_WIDTH / 2, WINDOW_HEIGHT / 2, COMPONENT_WIDTH, COMPONENT_HEIGHT);
    }

    /**
     * Creates the submit job button
     */
    private void createSubmitJobButton()
    {
        this.submitJobButton = new JButton(SUBMIT_JOB);
        this.submitJobButton.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - BUTTON_WIDTH / 2, WINDOW_HEIGHT / 2 + GAP + COMPONENT_HEIGHT, BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.submitJobButton.setFocusable(true);

        this.submitJobButton.addActionListener((actionEvent) -> {
            try
            {
                this.simulation.enqueueJob(this.jobStartTextField.getText(), this.jobEndTextField.getText());
            }
            catch (Exception exception)
            {
                if (this.exceptionFrame != null)
                    this.exceptionFrame.dispose();
                this.exceptionFrame = new ExceptionFrame(exception);
            }
            this.jobStartTextField.setText("");
            this.jobEndTextField.setText("");
        });
    }


    /**
     * Creates the stop button
     */
    private void createStopButton()
    {
        this.stopButton = new JButton(STOP);
        this.stopButton.setBounds(WINDOW_WIDTH - SIDE_UI_WIDTH / 2 - BUTTON_WIDTH / 2, WINDOW_HEIGHT - GAP - COMPONENT_HEIGHT, BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.stopButton.setFocusable(true);

        this.stopButton.addActionListener((actionEvent) -> {
            this.dispose();
            System.exit(0);
        });
    }

    /**
     * Creates all frame's components
     */
    private void createAllComponents()
    {
        this.facilityPanel = new FacilityPanel(this.simulation);
        this.createRobotLabels();
        this.createJobStartLabel();
        this.createJobStartTextField();
        this.createJobEndLabel();
        this.createJobEndTextField();
        this.createSubmitJobButton();
        this.createStopButton();
    }

    /**
     * Adds to frame all it's components
     */
    private void addAllComponents()
    {
        this.add(this.facilityPanel);
        for (JLabel label : this.robotLabels)
            this.add(label);
        this.add(this.jobStartLabel);
        this.add(this.jobStartTextField);
        this.add(this.jobEndLabel);
        this.add(this.jobEndTextField);
        this.add(this.submitJobButton);
        this.add(this.stopButton);
    }

    /**
     * Starts the frame
     */
    private void startWindow()
    {
        this.createAllComponents();

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null); //Centers frame on screen
        this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);

        this.addAllComponents();

        this.timer = new Timer(PAUSE_TIME_MILLIS, (actionEvent) -> {
            this.simulation.update();
            this.updateRobotLabels();
            this.facilityPanel.repaint();
        });
        this.timer.start();
    }
}