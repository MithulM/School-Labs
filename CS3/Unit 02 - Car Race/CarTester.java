///**
// *  CarTester.java tests the individual methods of the Car class, making
// *  sure the car can be drawn and moved forward.
// *  @author Tracy Ishman
// *  Date: 12 Sep 16
// */
//
//import javax.swing.JFrame;
//import javax.swing.JComponent;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
//
//public class CarTester extends JComponent
//{
//	private static final int WIDTH = 500;
//	private static final int HEIGHT = 800;
//
//	@Override
//	public void paintComponent(Graphics g)
//	{
//		Graphics2D g2 = (Graphics2D) g;
//
//		// Create a Car object and draw it
//		Racer pinto = new Car(50, 100, Color.CYAN);
//		System.out.println("Pinto: " + pinto.getBox());
//		pinto.draw(g2);
//		System.out.println("drawn: " + pinto.getBox());
//		System.out.println();
//
//		// Create another Car and draw it
//		Racer yugo = new Car(50, 200, Color.GREEN);
//		System.out.println("Yugo:    " + yugo.getBox());
//		yugo.moveForward(100);
//		yugo.draw(g2);
//		System.out.println("mvd 100: " + yugo.getBox());
//		System.out.println();
//
//		// Recheck first car
//		System.out.println("Pinto recheck: " + pinto.getBox());
//		System.out.println();
//	}
//
//
//	public static void main(String[] args)
//	{
//		// Create a frame for drawing and add this component
//		JFrame frame = new JFrame("Car Tester");
//		frame.setSize(WIDTH, HEIGHT);
//		frame.add(new CarTester());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
//}