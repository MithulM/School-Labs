/**
 * This application draws sequences of circles on a screen such that it
 * screensaver by limiting the number of circles that appear on the screen
 * at any one time in a FIFO manner.
 *
 * @author Mithul Manivannan
 * Collaborators:
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 1/31/20
 */


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class ScreenSaverComponent2 extends JComponent
{
   // Starting color for drawing
   private static final Color STARTING_COLOR = Color.MAGENTA;

   // Min and max values for translations of x and y
   private static final int MIN_CHANGE = 5;
   private static final int MAX_CHANGE = 50;
   private static final int COL_RANGE = 255;


   // These instance variables are instantiated
   private Color color;
   private int queueSize;
   private int diam;
   private int xDir;
   private int yDir;
   private int delX;
   private int delY;
   private Queue<Circle> queue;
   private Point prevPoint;

   /**
    * The constructor construcsts the variables
    * @param max The max
    * @param diam the diameter of the circles
    * @param chgX the change in x
    * @param the change in Y
    */
   public ScreenSaverComponent2(int max, int diam, int chgX, int chgY)
   {
       queueSize = max;
       this.diam = diam;
       delX = chgX;
       delY = chgY;
       xDir = 1;
       yDir = 1;
       prevPoint = null;
       queue = new LinkedList<>();
   }

   /** Add a new circle to be drawn and then draw all circles.
    *  @param g the Graphics object for drawing in this component
    */
   @Override
   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D gr2 = (Graphics2D) g;
       int maxWidth = getWidth();
       int maxHeight = getHeight();

       addCircle();
       drawAll(gr2);
   }

   /**
    * Draws all of the circles in the queue.
    */
   private void drawAll(Graphics2D gr)
   {
       Queue<Circle> copy = new LinkedList<>(queue);
       Deque<Circle> reverse = new LinkedList<>();
       while(!copy.isEmpty())
           reverse.push(copy.remove());
       if(reverse.isEmpty())
           return;
       Circle circ = reverse.pop();
       Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
       int red = circ.getColor().getRed();
       int blue = circ.getColor().getBlue();
       int green = circ.getColor().getGreen();
       gr.setColor(new Color(COL_RANGE - red, COL_RANGE - green, COL_RANGE - blue));
//            gr.setColor(Color.BLACK);
           gr.fill(rect);
       while(!reverse.isEmpty())
       {
           circ = reverse.poll();
           gr.setColor(circ.getColor());
           Point point = circ.getUpperLeft();
           Ellipse2D.Double circle = new Ellipse2D.Double(point.getX(), point.getY(), diam, diam);
           gr.fill(circle);
       }
   }

    /**
    * Creates the next circle to be drawn and adds it to the queue. If the queue has exceeded its
    * maximum size, the oldest circle is removed from the queue. If the new circle hits an edge,
    * the shift and color data needs to be updated. Whether or not an edge was hit, the point for
    * the next circle to be drawn is created.
    */
   private void addCircle()
   {
       int maxWidth = getWidth();
       int maxHeight = getHeight();
       //Checks if first point
       if (prevPoint == null)
       {
           prevPoint = new Point((maxWidth + diam) / 2, (maxHeight + diam) / 2);
           color = STARTING_COLOR;
           queue.add(new Circle(prevPoint, color));
       }
       else
       {
           if (queue.size() >= queueSize)
               queue.poll();
           if ((int)prevPoint.getX() + delX * xDir > getWidth() || (int)prevPoint.getX() + delX * xDir < 0)
           {
               xDir *= -1;
               delX = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
               int red = (int) (Math.random() * COL_RANGE);
               int green = (int) (Math.random() * COL_RANGE);
               int blue = (int) (Math.random() * COL_RANGE);
               int alpha = (int) (Math.random() * COL_RANGE / 2 + COL_RANGE/4);
               color = new Color(red, green, blue, alpha);
           }
           if ((int)prevPoint.getY() + delY * yDir > getHeight() || (int)prevPoint.getY() + delY * yDir < 0)
           {
               yDir *= -1;
               delY = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
               int red = (int) (Math.random() * COL_RANGE);
               int green = (int) (Math.random() * COL_RANGE);
               int blue = (int) (Math.random() * COL_RANGE);
               int alpha = (int) (Math.random() * COL_RANGE / 2 + COL_RANGE/4);
               color = new Color(red, green, blue, alpha);
           }
           prevPoint = new Point((int)prevPoint.getX() + delX * xDir, (int)prevPoint.getY() + delY * yDir);
           queue.add(new Circle(prevPoint, color));
       }
   }
}

