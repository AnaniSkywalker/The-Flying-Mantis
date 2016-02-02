/*Anani Assoutovi
 * February 1st, 2016
 * */

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

/*
 * This displays a framed area. As the user moves the cursor over the area, a
 * label displays the cursor's location. When the user clicks, the area displays
 * a 7x7 dot at the click location.
 */
public class FlyWindow 
{

    private FlyExperiment anani;
    private FlySurface flySurface;

    private FlyEnv flyEnv;
    private FlySimulation flySimulation;

    private JFrame window;

    private class FullScreenEventHandler extends KeyAdapter 
    {
        public FullScreenEventHandler() 
        {
            FlyWindow.this.getWindow().setFocusable(true); //Now We're making this to able to
                // handle the any such a ken event
        }

        public void keyPressed(KeyEvent e) 
        {
        	/* Contact the Author: Anani Assoutovi
        	 * Email: anania101@gmail.com
        	 * */
        }
    }

    private class WindowExitListener extends WindowAdapter 
    {
        public WindowExitListener() 
        {
        	
        }

        public void windowClosing(WindowEvent e) 
        {
            System.exit(0);
        }
    }

    public FlyWindow(String ... argv) 
    {
    	/* Contact the Author: Anani Assoutovi
    	 * Email: anania101@gmail.com
    	 * */
    }

    public JFrame getWindow() 
    {
        return window;
    }

    public FlyExperiment getExperimentDialog() 
    {
        return anani;
    }

    public FlySurface getFlySurface() 
    {
        return flySurface;
    }

    public FlyEnv getFlyEnv() 
    {
        return flyEnv;
    }

    public FlySimulation getFlySimulation() 
    {
        return flySimulation;
    }

    private void start() 
    {
        final FlyWindow this$window = this;
        if(EventQueue.isDispatchThread()) 
        {
        	/* Contact the Author: Anani Assoutovi
        	 * Email: anania101@gmail.com
        	 * */
        }
        else 
        {
        	/* Contact the Author: Anani Assoutovi
        	 * Email: anania101@gmail.com
        	 * */
        }
    }

    private void buildGUI()
    {
    	/* Contact the Author: Anani Assoutovi
    	 * Email: anania101@gmail.com
    	 * */
    }

    void  show() 
    {
        if(null != window) 
        {
        	/* Contact the Author: Anani Assoutovi
        	 * Email: anania101@gmail.com
        	 * */
        }
    }

    static void parse(FlyWindow window, String ... argv) 
    {
    	/* Contact the Author: Anani Assoutovi
    	 * Email: anania101@gmail.com
    	 * */
    }

    static void bindCommandLine(String arg) 
    {
    	/* Contact the Author: Anani Assoutovi
    	 * Email: anania101@gmail.com
    	 * */
    }

    public static void main(String[] args)
    {
    	/* Contact the Author: Anani Assoutovi
    	 * Email: anania101@gmail.com
    	 * */
    }
}