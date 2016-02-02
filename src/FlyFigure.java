/*Anani Assoutovi
 * February 1st, 2016
 * */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Formatter;

/**
 * The flying ball.
 */
public class FlyFigure 
{
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    float speedX, speedY; // Ball's speed per step in x and y (package access)
    float radius;         // Ball's radius (package access)
    private Color color;  // Ball's color

    private FlyNode active,target;
    private FlyNetwork network;

    // Re-use to build the formatted string for toString()
    private StringBuilder sb = new StringBuilder();
    private Formatter formatter = new Formatter(sb);

    int[] polygonXs = {-12, 0, 10, 0};
    int[] polygonYs = {12, 10, 15, -10};
    private final Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);

    float x, y;
    private Direction face;
    private FlyArea flyArea;

    public enum Direction 
    {
        EAST(0), NORTH(90), WEST(-180), SOUTH(180); //Might want to change EAST to (0), maybe // Per Anani

        private final int angle;

        /**
         * Qualified default class constructor.
         */
        Direction(int angle) 
        {
            this.angle = angle;
        }

        public int getAngle() 
        {
            return angle;
        }

        static void update(FlyArea area, FlyFigure figure) 
        {
            float planeMaxX = area.minX + area.maxX;
            float planeMaxY = area.minY + area.maxY;

            if(figure.x < (int) planeMaxX) figure.x++;
        }

        public static Direction toDirection(FlyNode active, FlyNode target, FlyArea area) 
        {
            if(null == active) throw new NullPointerException("Unexpecting for null fly node instance.");
            else 
            {
                if(active.getX() == target.getX()) 
                {
                    return (active.getY() < target.getY())? EAST : WEST;
                }
                else if(active.getX() < target.getX()) 
                {
                    return EAST;
                }
                else if(active.getX() > target.getX()) 
                {
                    return WEST;
                }
            }
            return null;
        }
    }

    private Direction direction;
    private int pad;

    /**
     * Qualified default class constructor.
     */
    public FlyFigure(FlyNetwork network, FlyArea flyArea, float radius, float speed, float angleInDegree, Color color) 
    {
        this.network = network;
        this.active = network.peek();
        this.target= network.peek();

        target.markAsTargert();

        this.flyArea = flyArea;
        this.direction = Direction.toDirection(active, target, flyArea);

        System.out.println("Direction= "+ direction);

        x = active.getRectangle().x;
        y = active.getRectangle().y;

        // Convert (speed, angle) to (x, y), with y-axis inverted
        this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
        this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));

        this.radius = radius;
        this.color = color;

        System.out.println("Shape= " + shape.getBounds() + ", Rectangle= "+ network.getAllFlyingNodeList().get(0).getRectangle());
    }

    public FlyNode getActive() 
    {
        return active;
    }

    public float getSpeed() 
    {
        return (float) Math.sqrt(speedX * speedX + speedY * speedY);
    }

    public float getMoveAngle() 
    {
        return (float) Math.toDegrees(Math.atan2(-speedY, speedX));
    }

    public float getMass() 
    {
        return radius * radius * radius / 1000f;  // Normalize by a factor
    }

    public float getKineticEnergy() 
    {
        return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
    }

    public void draw(Graphics g) 
    {
        drawPlane(g); //draw the flying plane.
    }

    void drawPlane(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;

        // Save the active transform of the graphics contexts.
        AffineTransform transform = g2d.getTransform();

        // Create an identity affine transform, and apply to the Graphics2D context
        AffineTransform identity = new AffineTransform();
        g2d.setTransform(identity);

        // Paint Shape (with identity transform), centered at (0, 0) as defined.
        g2d.setColor(color);
        g2d.fill(shape);

        // Translate to the initial (x, y) position, scale, and paint
        g2d.translate(x, y);
        g2d.scale(1.2, 1.2);

        g2d.translate(50.0, 5.0);  // translates by (50, 5)
        g2d.rotate(Math.toRadians(90.0));
        //g2d.rotate(Math.toRadians(direction.angle));  //Might want to comment out this // Per Anani
        g2d.fill(shape);

        // Restore original transform before returning
        g2d.setTransform(transform);
    }

    /**
     * Make one move, check for collision and react accordingly if collision occurs.
     *
     * @param area: the container (obstacle) for this ball.
     */
    public void move(FlyArea area) 
    {
        float planeMaxX = area.minX + area.maxX;
        float planeMaxY = area.minY + area.maxY;

        //if(x < (int) planeMaxX) x++;

        Direction.update(area, this);

        System.out.println("Shape= "+ shape.getBounds() + ", X= "
            + x + ", Y= "+ y + ", planeMaxX= "+ planeMaxX + ", planeMaxY= "+ planeMaxY);
    }

    /** Describe itself. */
    public String toString() 
    {
        sb.delete(0, sb.length());
        formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) S=%4.1f \u0398=%4.0f KE=%3.0f",
            getActive().getX(), getActive().getY(), radius, speedX, speedY, getSpeed(), getMoveAngle(),
            getKineticEnergy()); 

        return sb.toString();
    }
}