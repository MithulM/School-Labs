/**
 *  Description: RaceComponent draws the cars and the finish line.
 *
 *
 *  @author Mithul Manivannan
 *  Date: 9/13/19
 */

import javax.swing.*;
import java.awt.*;

public class RaceComponent extends JComponent {

    private Car[] cars;
    private int height;
    private int width;
    private int k;

    /**
     * initializes the array of Cars and color
     * @param numCars the number cars the frame will have
     */
    public RaceComponent(int numCars){
        height = 771;
        width = 1494;
        cars = new Car[numCars];
        k = 0;
        Color[] colors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130),
                new Color(127, 0, 255)
        };

        for(int i = 0; i < cars.length; i++)
            cars[i] = new Car(
                    width / 50,
                    ((height - 10) / (cars.length)) * i + ((height - 10) / (cars.length)) / 2 + 5,
                    colors[i],
                    width,
                    height);


    }

    /**
     * moves the car a random pixel from 1 through 5
     */
    public void advanceCars(){
        for(Car car : cars)
            car.moveForward((int) (Math.random() * 5 + 1));
        repaint();
    }

    /**
     * checks if one of the cars is touching the finish line
     * @return true if the gaame ended
     */
    public boolean isRaceOver(){
        for(Car car : cars)
            if(car.getBox().getWidth() + car.getBox().x > (int) (width * .9)) {
                restart();
                return true;
            }
        return false;
    }

    private void restart() {
        for(Car car : cars)
            car.restart();
    }

    /**
     * draws the car and the finish
     * @param g graphics to paint the cars onto the screen
     */
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        k++;
        height = getHeight();
        width = getWidth();
        if(k==1) {
            System.out.println(k);
            for (int i = 0; i < cars.length; i++) {
                cars[i].x(width / 50);
                cars[i].y(( (height - 10) / (cars.length)) * i + ((height - 10) / (cars.length)) / 2 + 5 );
                cars[i].h(height);
                cars[i].w(width);
            }
        }

        g2.setBackground(new Color(131, 131, 131));

        for(int i = 0; i < cars.length; i++)
            cars[i].draw(g2);
        g2.setColor(Color.RED);

        int FinLin1 = (int) (width * .9);

        g2.setStroke(new BasicStroke(10));
        g2.drawLine(FinLin1, 0, FinLin1, height);

    }

}
