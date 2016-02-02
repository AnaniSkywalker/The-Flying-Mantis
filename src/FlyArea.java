/*	Anani Assoutovi
 * February 1st, 2016
 * */

import java.awt.*;

/**
 * A rectangular container box, containing the bouncing ball.  
 */
public class FlyArea 
{
    private Color colorFilled;
    int minX, maxX, minY, maxY;

    private Color colorBorder;

    public FlyArea(Rectangle r, Color colorFilled, Color colorBorder) 
    {
        this(r.x, r.y, r.width - r.x, r.height - r.y, colorFilled, colorBorder);
    }

    /** Constructors */
    public FlyArea(int x, int y, int width, int height, Color filled, Color border) 
    {
        minX = x; minY = y;

        this.colorFilled = filled;
        this.colorBorder = border;
        set(x, y, width, height);
    }

    /** Set or reset the boundaries of the box. */
    public void set(int x, int y, int width, int height) 
    {
        minX = x; minY = y;

        maxX = (x + width);
        maxY = (y + height);
    }
}