/**
 *  Description: Timer for the the advanceCars and checking if the race is over.
 *
 *  @author Mithul Manivannan
 *  Date: 9/13/19
 */

import java.awt.event.*;

public class TimeListener implements ActionListener {


    private RaceComponent car;

    /**
     * initializes the variables
     * @param car RaceComponent car
     */
    public TimeListener(RaceComponent car) {
        this.car = car;
    }

    /**
     * runs the code after x seconds
     * @param e the Timer class
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        car.advanceCars();
        car.isRaceOver();
    }
}
