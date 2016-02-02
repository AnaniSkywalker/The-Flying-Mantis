/*Anani Assoutovi
 * February 1st, 2016
 * */

/**
 * Fly Simulation parameters
 */
public class FlySimulation 
{
    private String name;
    private int iteration;
    private int randomTurnAngle;
    private int visualField;
    private int sight;

    public static final int DEFAULT_SIGHT = 60; // Might want to make this 40 // Per Anani
    public static final int DEFAULT_VISUALCOUNT = 60; // Might want to make this 60 // Per Anani
    public static final int DEFAULT_TURNANGLE = 0;  // Might want to make this 0 // Per Anani
    public static final int DEFAULT_ITERATION = 500;
    public static final String DEFAULT_NAME_PREFIX = "Default-";

    /**
     * Qualified default class constructor.
     */
    public FlySimulation() 
    {
    	
    }

    public String getName() 
    {
        if(null == name) 
        {
            setName(String.format("%s%s", DEFAULT_NAME_PREFIX, System.currentTimeMillis()));
        }
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getIteration() 
    {
        return (0 < iteration)? iteration : DEFAULT_ITERATION;
    }

    public void setIteration(int iteration) 
    {
        this.iteration = iteration;
    }

    public int getRandomTurnAngle() 
    {
        return (0 < randomTurnAngle)? randomTurnAngle : DEFAULT_TURNANGLE;
    }

    public void setRandomTurnAngle(int randomTurnAngle) 
    {
        this.randomTurnAngle = randomTurnAngle;
    }

    public int getVisualField() 
    {
        return (0 < visualField)? visualField : DEFAULT_VISUALCOUNT;
    }

    public void setVisualField(int visualField) 
    {
        this.visualField = visualField;
    }

    public int getSight() 
    {
        return (0 < sight)? sight : DEFAULT_SIGHT;
    }

    public void setSight(int sight) 
    {
        this.sight = sight;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlySimulation that = (FlySimulation) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() 
    {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() 
    {
        final StringBuffer sb = new StringBuffer("Fly Simulation{");
        sb.append("name='").append(name).append('\'');
        sb.append(", iteration=").append(iteration);
        sb.append(", randomTurnAngle=").append(randomTurnAngle);
        sb.append(", visualField=").append(visualField);
        sb.append(", sight=").append(sight);
        sb.append('}');
        return sb.toString();
    }
}