/*Anani Assoutovi
 * February 1st, 2016
 * */

public class FlyRunner implements Runnable 
{
    private final FlyWindow window;


    /**
     * Qualified default class constructor.
     */
    public FlyRunner(FlyWindow window) 
    {
        this.window= window;
    }

    public FlyWindow getWindow() 
    {
        return window;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @java.lang.Override
    public void run() 
    {
        this.getWindow().getFlySurface().startNavigation();
    }
}