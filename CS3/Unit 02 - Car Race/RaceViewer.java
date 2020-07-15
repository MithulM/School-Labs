/**
 *  Description: Creates the Jframe and draws the cars and finish. It also
 *  has a timer the move the cars randomly every .05 seconds.
 *
 *  @author Mithul Manivannan
 *  Date: 9/13/19
 */

import java.util.Scanner;
import javax.swing.*;


public class RaceViewer
{

	private static final int WIDTH = 1500;
	private static final int HEIGHT = 800;
	private static final int speed = 50;


	public static void main(String[] args)
	{

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of cars from 1 through 7: ");
        String numCars = in.next();
        while(!numCars.matches("[1-7]")) {
            System.out.print("Enter the number of cars from 1 through 7: ");
            numCars = in.next();
        }
        int realNum = Integer.parseInt(numCars);

        JFrame frame = new JFrame("Race");
        RaceComponent game = new RaceComponent(realNum);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);

        Timer t = new Timer(speed, new TimeListener(game));
        t.start();
	}
}