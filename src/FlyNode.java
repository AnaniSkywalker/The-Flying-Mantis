/*Anani Assoutovi
 * February 1st, 2016
 * */

import java.awt.*;

public class FlyNode 
{
    private final long id;
    private boolean select, target;
    private final Rectangle rectangle;

    /**
     * Check whether, how many time this node has been visited.
     */
    private int iteration;

    public FlyNode(int x, int y) 
    {
        this(x, y, (x*y));
    }

    /**
     * Qualified default class constructor.
     */
    public FlyNode(int x, int y, long id) 
    {
        this(new Rectangle(x, y, 0, 0), id);

        /*this.x= x;
        this.y = y;
        this.id= id;*/
    }

    public FlyNode(Rectangle rectangle, long id) 
    {
        this.rectangle = rectangle;
        this.id = id;
    }

    public FlyNode(Rectangle rectangle, long id, boolean well, boolean corner) 
    {
        this.rectangle = rectangle;
        this.id = id;
    }

    /**
     * Gets the node unique identifier.
     * @return a valid flying node unique identifier
     */
    public long getId() 
    {
        return id;
    }

    public boolean isTarget() 
    {
        return target;
    }

    public void markAsTargert() 
    {
        this.target = true;
    }

    /**
     * Mark the current node which'll mark it as a visula field of it hasn't selected already.
     */
    public void select() 
    {
        if(!select) select = true;
    }

    /**
     * Check whether the current node is part of the selected visual field.
     * @return Might return <code>true</code> while the current node is part of visual field or <code>false</code> otherwise
     */
    public boolean isSelected() 
    {
        return select;
    }

    public int getX() 
    {
        return rectangle.x;
    }

    public int getY() 
    {
        return rectangle.y;
    }

    public Rectangle getRectangle() 
    {
        return rectangle;
    }

    /**
     * Mark the current as visiting. It's might cause the visitation incrementation.
     */
    public void visit() 
    {
        this.iteration++;
        this.target = false;
    }

    /**
     * Check whether, the current node has been visited at least once.
     * @return Might return <code>true</code> while the current node has been visited at least one or <code>false</code>
     * otherwise.
     */
    public boolean isVisited() 
    {
        return (0 < iteration);
    }

    @java.lang.Override
    public String toString() 
    {
        final StringBuffer sb = new StringBuffer("Fly Node{");
        sb.append('}');
        return sb.toString();
    }
}