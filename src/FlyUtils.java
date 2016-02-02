/*Anani Assoutovi
 * February 1st, 2016
 * */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public final class FlyUtils 
{

    /**
     * Qualified default internal class constructor.
     */
    private FlyUtils() 
    {
        throw new IllegalAccessError("FlyUtils: class cannot be instantiate.");
    }


    public static void fullscreen(boolean fullscreen, FlyWindow flyWindow) 
    {
        final JFrame window = flyWindow.getWindow();

        // Get the default graphic device and try full screen mode
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (fullscreen && device.isFullScreenSupported()) 
        { //@TODO: Go for full-screen mode
            window.setUndecorated(true);//@TODO: Don't show title and border
            window.setResizable(false);
            //this.setIgnoreRepaint(true);     //@TODO: Ignore OS re-paint request
            device.setFullScreenWindow(window);
        } 
        else 
        {
            //@TODO: Run in windowed mode if full screen is not supported
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            window.setSize(dim.width, dim.height - 40); // minus task bar
            window.setResizable(true);
        }
    }

    public static Point center(int width, int height) 
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // calculate the new location of the window
        int x = (dim.width - width) / 2;
        int y = (dim.height - height) / 2;

        return new Point(x, y);
    }

    public static void center(JFrame frame) 
    {
        frame.setLocation(center(frame.getWidth(), frame.getHeight()));
    }

    public static void paintBg(Graphics g, Color bg, Rectangle r) 
    {
        g.setColor(bg);
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    public static JTextField createNumericField(String name, Class<Number> type) 
    {
        JTextField textField = null;

        return textField;
    }

    public static boolean getRandomBoolean() 
    {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) 
    {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static boolean isWall(Rectangle r, FlyArea area) 
    {
        return isTopWall(r, area) || isLeftWall(r, area)
                || isBottomWall(r, area) || isRightWall(r, area);
    }

    public static boolean isTopWall(Rectangle r, FlyArea area) 
    {
        return r.x == area.minX;
    }

    public static boolean isLeftWall(Rectangle r, FlyArea area) 
    {
        return r.y == area.minY;
    }

    public static boolean isBottomWall(Rectangle r, FlyArea area) 
    {
        return r.x == area.maxX;
    }

    public static boolean isRightWall(Rectangle r, FlyArea area) 
    {
        return r.y == area.maxY;
    }

    public static boolean isTopLeftCorner(Rectangle r, FlyArea area) 
    {
        return r.x == area.minX && r.y == area.minY;
    }

    public static boolean isTopRightCorner(Rectangle r, FlyArea area) 
    {

        return r.x == area.maxX && r.y == area.maxY;
    }

    public static boolean isBottomLeftCorner(Rectangle r, FlyArea area) 
    {
        return r.x == area.minX && r.y == area.maxY;
    }

    public static boolean isBottomRightCorner(Rectangle r, FlyArea area) 
    {
        return r.x == area.maxX && r.y == area.maxY;
    }

    public static boolean isCorner(Rectangle r, FlyArea area) 
    {
        return isTopLeftCorner(r, area) || isTopRightCorner(r, area)
                || isBottomLeftCorner(r, area) || isBottomRightCorner(r, area);
    }
}