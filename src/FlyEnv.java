/* Anani Assoutovi
 * February 1st, 2016
 * */

public class FlyEnv 
{
    private int radius;
    private int width, height;

    /**
     * Default FlyEnv class constructor.
     */
    public FlyEnv() 
    {
    	
    }

    FlyEnv(int radius) 
    {
        this.radius = radius;
    }

    FlyEnv(int width, int height) 
    {
        this.width = width;
        this.height = height;
    }

    public FlyEnv createCircle(int radius) 
    {
        return new FlyEnv(radius);
    }

    public FlyEnv createRectangle(int width, int height) 
    {
        return new FlyEnv(width, height);
    }

    public boolean isCircle() 
    {
        return (0 < radius);
    }

    public boolean isRectangle() 
    {
        return !isCircle(); //(0 < width && 0 < height);
    }

    public int getRadius() 
    {
        return radius;
    }

    public void setRadius(int radius) 
    {
        this.radius = radius;
    }

    public int getWidth() 
    {
        return width;
    }

    public void setWidth(int width) 
    {
        this.width = width;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setHeight(int height) 
    {
        this.height = height;
    }
}