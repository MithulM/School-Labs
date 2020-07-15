/* The TriangleComponent class has the structor for the 
 * creation of triangles using recursion and adds 
 * them to the paintComponent to be shown by the 
 * TriangleViewer class
 *
 *@author Mithul Manivannan
 *Teacher: Mrs. Garg
 *Period: 3th
 *Due Date 3/19/2019
 *
 */
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JComponent;

public class TriangleComponent extends JComponent
{
	
	// constant class array to hold progression of colors
	private static final Color LAVENDER = new Color(204, 153, 255);
	private static final Color PURPLE = new Color(153, 0, 255);
	private static final Color[] colors = {LAVENDER, Color.cyan,
	Color.darkGray,Color.blue,PURPLE,Color.pink,Color.black,Color.lightGray};
	private final int offset = 5;	
	// Graphics object used for drawing
	private Graphics2D gr;
	
	/** Draw the triangles
	 *  @param g the Graphics object for drawing in this component
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		gr = (Graphics2D) g;
		drawTriangle(getWidth()/2,offset,offset,getHeight()-offset,getWidth()-offset,getHeight()-offset,0);
		
	}	
	/* Draws the triangles with given coordinates according to frame using recursion
	 * @param x1 the first X coordinate
	 * @param x2 the second X coordinate
	 * @param x3 the third X coordinate
	 * @param y1 the first Y coordinate
	 * @param y2 the second Y coordinate
	 * @param y3 the third Y coodrinate
	 */
	
	private void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int colorIndex)
	{
		
		if(colorIndex == colors.length - 1){
			colorIndex = 0;
		}
		int[] xValues = {x1,x2,x3};
		int[] yValues = {y1,y2,y3};
		Polygon triangle = new Polygon(xValues, yValues, 3);
			
		gr.setColor(colors[colorIndex]);
		gr.fill(triangle);
		
		//Draws the big triangle
		if((y1+y2) / 2 != y1){
			drawTriangle(x1, y1,(x1+x2)/2,(y1 + y2)/2,(x1 + x3)/2, (y1 + y3)/2,colorIndex + 1);
		}
		
		//Draws Triangle from the right
		if((x2 + x3) / 2 != x2){
			drawTriangle((x1 + x2)/2, (y1 + y2)/2, x2, y2,(x2 + x3)/2, (y2 + y3)/2,colorIndex+1);
		}
		
		//Draws the Triangle from the left
		if((x2 + x3) / 2 != x2){
			drawTriangle((x1 + x3)/2,(y1 + y3)/2, (x2 + x3)/2, (y2 + y3)/2,x3,y3,colorIndex + 1);
		}

	}
}
