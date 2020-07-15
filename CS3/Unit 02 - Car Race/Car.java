/**
 *  Description: The Car class represents a single car with its own location, dimensions, and color.
 *  In this initial version, my car with wheels, roof, and headlines.
 *
 *  @author Mithul Manivannan
 *  Date: 9/13/19
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Car implements Racer
{

    private int x;
    private int InitialX;
    private int y;
    private Color color;
    private int h;
    private int w;

    /**
     * Initializes variables
     * @param x the left of car box outline
     * @param y the top of car box outline
     * @param color the color of the car
     * @param width the width of the car
     * @param height the height of the car
     */
    public Car(int x, int y, Color color, int width, int height) {
        this.x = x;
        InitialX = x;
        this.y = y;
        this.color = color;
        h = height / 50;
        w = width / 10;
    }

    /**
     * Draws the racer using the given graphics context
     *  @param gr the graphics context for drawing
     */
    @Override
    public void draw(Graphics2D gr) {
        gr.setColor(color);

        //Body
        Rectangle rect = new Rectangle(x, y + h / 2, w, h / 2);
        gr.fill(rect);
        gr.draw(rect);

        //Wheels
        gr.setColor(Color.BLACK);

        Ellipse2D.Double wheel1 = new Ellipse2D.Double(x + w / 5.0, y + h, w / 5.0,h / 5.0);
        gr.fill(wheel1);
        gr.draw(wheel1);

        Ellipse2D.Double wheel2 = new Ellipse2D.Double(x + w * 2 / 3.0, y + h, w / 5.0,h / 5.0);
        gr.fill(wheel2);
        gr.draw(wheel2);

        //Roof
        int[] xs = {(int) (x + w / 5.0), (int) (x + w * 2 / 5.0), (int) (x + w * 3 / 5.0), (int) (x + w * 4 / 5.0)};
        int[] ys = {y + h / 2, y, y, y + h / 2};
        gr.setColor(color);
        gr.fillPolygon(xs, ys, xs.length);

        //Headlights
        if(color != Color.YELLOW)
            gr.setColor(Color.YELLOW);
        else
            gr.setColor(Color.RED);

        Rectangle head = new Rectangle(x + w * 7 / 8, y + h * 5 / 8, w / 8, h / 4);
        gr.fill(head);
        gr.draw(head);

    }

    /**
     * Advances the racer forward in the race by amount
     *  @param amount the number of pixels to advance
     */
    @Override
    public void moveForward(int amount) {
        x += amount;
    }

    /**
     * Retrieves the shape's bounding rectangle
     *  @return the bounding rectangle that encompasses this shape
     */
    @Override
    public Rectangle getBox() {
        return new Rectangle(x, y, w, h);
    }

    //Helper methods

    /**
     * sets x
     * @param amount the amount for x to be set to
     */
    public void x(int amount){
        x = amount;
    }

    /**
     * sets y
     * @param amount the amount for y to be set to
     */
    public void y(int amount){
        y = amount;
    }

    /**
     * sets w
     * @param amount the amount for w to be set to
     */
    public void w(int amount){
        h = amount / 50;
    }

    /**
     * sets h
     * @param amount the amount for h to be set to
     */
    public void h(int amount){
        w = amount / 10;
    }

    /**
     * sets the x to starting position
     */
    public void restart(){
        x = InitialX;
    }
}