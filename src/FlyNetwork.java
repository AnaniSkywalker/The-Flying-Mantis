/*Anani Assoutovi
 * February 1st, 2016
 * */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FlyNetwork 
{
    private final FlySurface flySurface;

    private List<FlyNode> flyNodes,
        visualFields, serie;

    private final FlyWindow flyWindow;

    private int iteration;

    /**
     * Qualified default class constructor.
     */
    public FlyNetwork(FlySurface flySurface, FlyWindow flyWindow) 
    {
        this.flySurface = flySurface;
        this.flyWindow = flyWindow;

        this.flyNodes = new LinkedList<FlyNode>();
        this.visualFields= new LinkedList<FlyNode>();

        this.serie = new LinkedList<FlyNode>();
    }

    public void setSight(int sight) 
    {
        flyWindow.getFlySimulation().setSight(sight);
    }

    public int getSight() 
    {
        return flyWindow.getFlySimulation().getSight();
    }

    public int getVisualField() 
    {
        return flyWindow.getFlySimulation().getVisualField();
    }

    public void setVisualField(int visualField) 
    {
        flyWindow.getFlySimulation().setVisualField(visualField);
    }

    /**
     * Gets the network total node size.
     * @return Might return a valid network node size while the current network has been initialized or <code>0</code>
     * otherwise.
     */
    public int getTotalVisualFieldCount() 
    {
        return (null != flyNodes)? flyNodes.size() : 0;
    }

    public List<FlyNode> getAllFlyingNodeList() 
    {
        return (null != flyNodes)? Collections.unmodifiableList(flyNodes)
                : Collections.<FlyNode>emptyList();
    }

    public FlyNode peek() 
    {
        if(iteration == flyWindow.getFlySimulation().getIteration()) return null;
        else 
        {
            FlyNode flyNode;
            serie = (null == serie)? new LinkedList<FlyNode>(flyNodes) : serie;
            if(serie.isEmpty()) 
            {
                serie.addAll(flyNodes);
                iteration = 0;
            }

            if(1 == serie.size()) flyNode = serie.remove(0);
            else 
            {
                final int index = FlyUtils.randInt(1, serie.size());
                flyNode = serie.remove(index==serie.size()? index-1: index);
            }
            iteration++;
            return flyNode;
        }
    }

    public void reset() 
    {
        this.iteration = 0;
        if(null != flyNodes) flyNodes.clear();
        if(null != visualFields) visualFields.clear();
        if(null != serie) serie.clear();
    }

    public void add(FlyNode flyNode) 
    {
        if(null != flyNode) 
        {
        	//Bind the fly node
            flyNodes = (null == flyNodes)? new LinkedList<FlyNode>() : flyNodes;
            flyNodes.add(flyNode); //Bind the fly node
        }
    }
}