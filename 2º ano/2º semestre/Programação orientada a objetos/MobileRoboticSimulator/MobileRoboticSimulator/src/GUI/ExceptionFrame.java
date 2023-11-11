package GUI;

import javax.swing.*;

/**
 * A simple frame that shows an exception message and a simple OK button, that if pressed close's the frame
 * @author Alexandre Rodrigues, Miguel Cabrita and Afonso Rio
 * @version 1.0 21/05/2023
 */
public class ExceptionFrame extends JFrame
{
    private static final int GAP = 10; //Slight pixel offset from window border and other components
    private static final int WINDOW_WIDTH = 540;
    private static final int WINDOW_HEIGHT = 130;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 25;

    private static final String OK = "OK";

    private final Exception exception;
    private JLabel exceptionLabel;
    private JButton okButton;

    /**
     * Creates a new exception frame
     * @param e Exception
     */
    public ExceptionFrame(Exception e)
    {
        this.exception = e;
        this.startWindow();
    }

    /**
     * Creates the frame's exception label
     */
    private void createExceptionLabel()
    {
        this.exceptionLabel = new JLabel(this.exception.getMessage());
        this.exceptionLabel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.exceptionLabel.setHorizontalAlignment(JLabel.CENTER);
        this.exceptionLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Creates the frame's OK button
     */
    private void createOkButton()
    {
        this.okButton = new JButton(OK);
        this.okButton.setBounds((WINDOW_WIDTH - BUTTON_WIDTH) / 2, WINDOW_HEIGHT - GAP - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.okButton.setFocusable(true);

        this.okButton.addActionListener((actionEvent) -> this.dispose());
    }

    /**
     * Creates all frame's components
     */
    private void createAllComponents()
    {
        this.createExceptionLabel();
        this.createOkButton();
    }

    /**
     * Adds to frame all it's components
     */
    private void addAllComponents()
    {
        this.add(this.exceptionLabel);
        this.add(this.okButton);
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
