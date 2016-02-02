/*Anani Assoutovi
 * February 1st, 2016
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;

public class FlySurface extends JPanel 
{
    private static final int UPDATE_RATE = 20;  // Frames per second (fps)

    private FlyFigure figure;         // A single bouncing Ball's instance
    private FlyArea area;  // The container rectangular area

    private FlyWindow flyWindow;

    private GridCanvas gridCanvas;
    private SurfaceMenuCanvas labelCanvas;

    private FlyNetwork flyNetwork;

    private int speed, angleInDegree, radius;

    /**
     * Constructor to create the UI components and init the game objects.
     * Set the drawing labelCanvas to fill the screen (given its canvasWidth and canvasHeight).
     */
    public FlySurface(FlyWindow flyWindow) 
    {
        this.flyWindow = flyWindow;

        Random rand = new Random();
        this.radius = 200;

        this.speed = UPDATE_RATE;
        this.angleInDegree = rand.nextInt(360);

        // Init the custom drawing panel for drawing the game
        labelCanvas = new SurfaceMenuCanvas();
        gridCanvas = new GridCanvas();

        this.setLayout(new BorderLayout());

        this.add(labelCanvas, BorderLayout.NORTH);
        this.add(gridCanvas);

        // Handling window resize.
        final FlySurface this$surface = this;
        this.addComponentListener(new ComponentAdapter() 
        {
            public void componentResized(ComponentEvent e) 
            {
                this$surface.getFlyNetwork().reset(); // Might want to comment out // Per Anani

                Component c = (Component) e.getSource();
                Dimension dim = c.getSize();

                if(null != area) 
                {
                    final Rectangle drawPoint = gridCanvas.calculateDrawPoint(this$surface.getSize());
                    area.set(drawPoint.x, drawPoint.y, drawPoint.width, drawPoint.height);
                }
            }
        });
    }

    /**
     * If the <code>preferredSize</code> has been set to a
     * non-<code>null</code> value just returns it.
     * If the UI delegate's <code>getPreferredSize</code>
     * method returns a non <code>null</code> value then return that;
     * otherwise defer to the component's layout manager.
     *
     * @return the value of the <code>preferredSize</code> property
     * @see #setPreferredSize
     * @see javax.swing.plaf.ComponentUI
     */
    @Override
    public Dimension getPreferredSize() 
    {
        final JFrame window= flyWindow.getWindow();
        return new Dimension(window.getWidth(), window.getHeight());
    }

    /**
     * If the minimum size has been set to a non-<code>null</code> value
     * just returns it.  If the UI delegate's <code>getMinimumSize</code>
     * method returns a non-<code>null</code> value then return that; otherwise
     * defer to the component's layout manager.
     *
     * @return the value of the <code>minimumSize</code> property
     * @see #setMinimumSize
     * @see javax.swing.plaf.ComponentUI
     */
    @Override
    public Dimension getMinimumSize() 
    {
        final JFrame window= flyWindow.getWindow();
        return new Dimension(window.getWidth(), window.getHeight());
    }

    public FlyNetwork getFlyNetwork() 
    {
        return flyNetwork;
    }

    public FlyWindow getFlyWindow() 
    {
        return flyWindow;
    }

    /** Start the figure bouncing. */
    public void startNavigation() 
    {
        if(null == flyNetwork) 
        {
            flyNetwork = new FlyNetwork(this, flyWindow);
            this.gridCanvas.initialize();

            final Rectangle drawPoint = gridCanvas.calculateDrawPoint();

            System.out.println("DrawPoint= "+ drawPoint);

            area = new FlyArea(drawPoint, Color.BLACK, Color.BLACK);
            figure = new FlyFigure(flyNetwork, area,  radius, speed, angleInDegree, Color.GREEN);
        }

        Thread navigator = new Thread() 
        {
            public void run() 
            {
                while (true) 
                {
                    executeMovement(); //TODO: Execute one time-step for the game

                    repaint(); //TODO: Refresh the display
                    try 
                    {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } 
                    catch (InterruptedException ex) 
                    {
                        ex.printStackTrace();
                    }
                }
           }
        };

        try 
        {
            navigator.start();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void executeMovement() 
    {
        figure.move(area);
    }

    class SurfaceMenuCanvas extends JPanel 
    {
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);    // Paint background
            setBackground(Color.RED);

            // Display figure's information
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.PLAIN, 12));
            g.drawString("THE FLY FIGURE (PRESS ESC) to quit -> " /*+
                ((null != figure)? figure.toString() : "")*/, 20, 30);
        }

        /**
         * Called back to get the preferred size of the component.
         */
        public Dimension getPreferredSize() 
        {
            return (new Dimension(getFlyWindow().getWindow().getWidth(), 50));
        }
    }

    class GridCanvas extends JPanel 
    {
        private final int DEFAULT_COUNT = 10;

        /**
         * Creates a new <code>JPanel</code> with a double buffer
         * and a flow layout.
         */
        public GridCanvas() 
        {
            //FIXME: Maybe I should use a resize listener??? Per Anani
        }

        public void initialize() 
        {
            if (getFlyNetwork().getAllFlyingNodeList().isEmpty()) 
            {
                final Dimension s = getSize();

                final Rectangle drawPoint = calculateDrawPoint(s);
                final FlySimulation flySimul = getFlyWindow().getFlySimulation();

                final int sight = flySimul.getSight();
                int cols = drawPoint.width / sight;
                int rows = drawPoint.height / sight;

                System.out.println("Size= "+ s + ", Cols= " + drawPoint.width / sight
                        + ", Rows= "+ drawPoint.height / sight);

                for (int row = 0; row < rows+1; row++) 
                {
                    for (int col = 0; col < cols+1; col++) 
                    {

                        int x = (drawPoint.x + row * sight);
                        int y = (drawPoint.y + col * sight);

                        Rectangle rectangle = new Rectangle(x, y, sight, sight);
                        getFlyNetwork().add(new FlyNode(rectangle, System.currentTimeMillis()));
                    }
                }
            }
        }

        public Rectangle calculateDrawPoint() 
        {
            return calculateDrawPoint(this.getSize());
        }

        public Rectangle calculateDrawPoint(Dimension componentSize) 
        {
            final FlyEnv flyEnv = FlySurface.this.flyWindow.getFlyEnv();
            final FlySimulation flySimulation = FlySurface.this.flyWindow.getFlySimulation();

            Rectangle rectangle = null;
            if(flyEnv.isRectangle()) {
                int width = flyEnv.getWidth();
                int height = flyEnv.getHeight();

                width = (width > 0)? width : (DEFAULT_COUNT * flySimulation.getSight());
                height = (height > 0)? height : (DEFAULT_COUNT * flySimulation.getSight());

                int x = (int) ((componentSize.getWidth() > width)? ((componentSize.getWidth() - width) / 2) : 0);
                int y = (int) ((componentSize.getHeight() > height)? ((componentSize.getHeight() - height) / 2) : 0);

                rectangle = new Rectangle(x, y, width, height);
            }
            return rectangle;
        }

        /**
         * Calls the UI delegate's paint method, if the UI delegate
         * is non-<code>null</code>.  We pass the delegate a copy of the
         * <code>Graphics</code> object to protect the rest of the
         * paint code from irrevocable changes
         * (for example, <code>Graphics.translate</code>).
         * <p/>
         * If you override this in a subclass you should not make permanent
         * changes to the passed in <code>Graphics</code>. For example, you
         * should not alter the clip <code>Rectangle</code> or modify the
         * transform. If you need to do these operations you may find it
         * easier to create a new <code>Graphics</code> from the passed in
         * <code>Graphics</code> and manipulate it. Further, if you do not
         * invoker super's implementation you must honor the opaque property,
         * that is
         * if this component is opaque, you must completely fill in the background
         * in a non-opaque color. If you do not honor the opaque property you
         * will likely see visual artifacts.
         * <p/>
         * The passed in <code>Graphics</code> object might
         * have a transform other than the identify transform
         * installed on it.  In this case, you might get
         * unexpected results if you cumulatively apply
         * another transform.
         *
         * @param g the <code>Graphics</code> object to protect
         * @see #paint
         * @see javax.swing.plaf.ComponentUI
         */
        @java.lang.Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            if(null != getFlyNetwork()) 
            {
                Graphics2D g2d = (Graphics2D) g;
                final Dimension s = getSize();

                FlyUtils.paintBg(g, Color.BLACK, new Rectangle(0, 0, s.width, s.height));

                for (FlyNode flyNode : getFlyNetwork().getAllFlyingNodeList()) 
                {
                    g2d.setColor(Color.RED);
                    final Rectangle r = flyNode.getRectangle();

                    g2d.draw(r);
                    drawVisualPoint(g, r.x, r.y, flyNode.isTarget()? 12 : 6, Color.WHITE);
                }

                if(null != figure) figure.draw(g);
            }
        }

        void drawVisualPoint(Graphics g, int x, int y, int width, Color color) 
        {
            if(null != g) 
            {
                Graphics2D g2d = (Graphics2D) g;
                color = (null == color)? Color.WHITE : color;

                g2d.setColor((null == color)? Color.WHITE : color);
                g2d.fillRect(x-(width / 2), y-(width / 2), width, width);
            }
        }
    }
}