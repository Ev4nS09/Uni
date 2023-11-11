package GUI;

import simulation.IMobileRoboticSimulation;

import javax.swing.*;

/**
 * Initial frame that user puts the desired configurations from a simulation <br>
 * Once everything is done, upon closing this frame it opens a simulation frame
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 21/05/2023
 */
public class PreSimulationFrame extends JFrame
{
    private static final int GAP = 10; //Slight pixel offset from window border and other components
    private static final int COMPONENT_WIDTH = 200;
    private static final int COMPONENT_HEIGHT = 25;
    private static final int BUTTON_WIDTH = COMPONENT_WIDTH / 2;
    private static final int WINDOW_WIDTH = 4 * GAP + 2 * COMPONENT_WIDTH + BUTTON_WIDTH;
    private static final int WINDOW_HEIGHT = 3 * GAP + 4 * COMPONENT_HEIGHT;

    private static final String SELECT_OBSTACLE_TYPE = "Select obstacle type";
    private static final String[] COMBO_BOX_OBSTACLE_TYPES = new String[]{"Circumference", "Rectangle", "Triangle"};
    private static final String INSERT = "Insert";
    private static final String INSERT_CHARGING_POINT = "Insert charging point";
    private static final String START = "Start";

    private final IMobileRoboticSimulation simulation;

    private JLabel obstacleLabel, chargingPointLabel;
    private JComboBox<String> obstacleComboBox;
    private JTextField obstacleTextField, chargingPointTextField;
    private JButton obstacleButton, chargingPointButton, startButton;
    private ExceptionFrame exceptionFrame;

    /**
     * Creates a new pre simulation frame
     * @param simulation Simulation
     */
    public PreSimulationFrame(IMobileRoboticSimulation simulation)
    {
        this.simulation = simulation;
        this.startWindow();
    }

    /**
     * Creates the frame's obstacle label
     */
    private void createObstacleLabel()
    {
        this.obstacleLabel = new JLabel(SELECT_OBSTACLE_TYPE);
        this.obstacleLabel.setBounds(GAP / 2, 0, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        this.obstacleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.obstacleLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Creates the frame's obstacle dropdown text/box
     */
    private void createObstacleComboBox()
    {
        this.obstacleComboBox = new JComboBox<>(COMBO_BOX_OBSTACLE_TYPES);
        this.obstacleComboBox.setBounds(GAP, COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
    }

    /**
     * Creates the frame's obstacle text field
     */
    private void createObstacleTextField()
    {
        this.obstacleTextField = new JTextField();
        this.obstacleTextField.setBounds(2 * GAP + COMPONENT_WIDTH, COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
    }

    /**
     * Creates the frame's obstacle button
     */
    private void createObstacleButton()
    {
        this.obstacleButton = new JButton(INSERT);
        this.obstacleButton.setBounds(3 * GAP + 2 * COMPONENT_WIDTH, COMPONENT_HEIGHT, BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.obstacleButton.setFocusable(true);

        this.obstacleButton.addActionListener((actionEvent) -> {
            try
            {
                this.simulation.addObstacle(this.obstacleComboBox.getSelectedItem() + " " +
                        this.obstacleTextField.getText());
            }
            catch (Exception exception)
            {
                if (this.exceptionFrame != null)
                    this.exceptionFrame.dispose();
                this.exceptionFrame = new ExceptionFrame(exception);
            }
            this.obstacleTextField.setText("");
        });
    }

    /**
     * Creates the frame's charging point label
     */
    private void createChargingPointLabel()
    {
        this.chargingPointLabel = new JLabel(INSERT_CHARGING_POINT);
        this.chargingPointLabel.setBounds(GAP / 2, GAP + 2 * COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        this.chargingPointLabel.setHorizontalAlignment(JLabel.CENTER);
        this.chargingPointLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Creates the frame's charging point text field
     */
    private void createChargingPointTextField()
    {
        this.chargingPointTextField = new JTextField();
        this.chargingPointTextField.setBounds(2 * GAP + COMPONENT_WIDTH, GAP + 2 * COMPONENT_HEIGHT, COMPONENT_WIDTH, COMPONENT_HEIGHT);
    }

    /**
     * Creates the frame's charging point button
     */
    private void createChargingPointButton()
    {
        this.chargingPointButton = new JButton(INSERT);
        this.chargingPointButton.setBounds(3 * GAP + 2 * COMPONENT_WIDTH, GAP + 2 * COMPONENT_HEIGHT, BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.chargingPointButton.setFocusable(true);

        this.chargingPointButton.addActionListener((actionEvent) -> {
            try
            {
                this.simulation.addChargingPoint(this.chargingPointTextField.getText());
            }
            catch (Exception exception)
            {
                if (this.exceptionFrame != null)
                    this.exceptionFrame.dispose();
                this.exceptionFrame = new ExceptionFrame(exception);
            }
            this.chargingPointTextField.setText("");
        });
    }

    /**
     * Creates the frame's start button
     */
    private void createStartButton()
    {
        this.startButton = new JButton(START);
        this.startButton.setBounds((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 2 * GAP + 3 * COMPONENT_HEIGHT, BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.startButton.setFocusable(true);

        this.startButton.addActionListener((actionEvent) -> {
            try
            {
                this.simulation.start();
                this.dispose();
                new SimulationFrame(this.simulation);
            }
            catch (Exception exception)
            {
                this.simulation.clearObstacles();
                this.simulation.clearChargingPoints();
                if (this.exceptionFrame != null)
                    this.exceptionFrame.dispose();
                this.exceptionFrame = new ExceptionFrame(exception);
            }
        });
    }

    /**
     * Creates all frame's components
     */
    private void createAllComponents()
    {
        this.createObstacleLabel();
        this.createObstacleComboBox();
        this.createObstacleTextField();
        this.createObstacleButton();
        this.createChargingPointLabel();
        this.createChargingPointTextField();
        this.createChargingPointButton();
        this.createStartButton();
    }

    /**
     * Adds to frame all it's components
     */
    private void addAllComponents()
    {
        this.add(this.obstacleLabel);
        this.add(this.obstacleComboBox);
        this.add(this.obstacleTextField);
        this.add(this.obstacleButton);
        this.add(this.chargingPointLabel);
        this.add(this.chargingPointTextField);
        this.add(this.chargingPointButton);
        this.add(this.startButton);
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
    }
}
