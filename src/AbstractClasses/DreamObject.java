package AbstractClasses;

import GameClasses.DreamLocation;

/**
 * Abstract class for all objects in the Dream world
 */
public abstract class DreamObject {

    private DreamLocation location;

    /**
     * Constructor for the Dream Object
     * @param location Dream location of the object
     */
    public DreamObject(DreamLocation location){
        this.location = location;
    }

    /**
     *
     * @return Returns a string containing the name of the object
     */
    public abstract String getName();

    /**
     *
     * @return Returns a string with a description of the object
     */
    public abstract String getDescription();

    /**
     *
     * @return Returns a string containing the symbol that represents the object
     */
    public abstract char getSymbol();

    /**
     *
     * @return Returns a boolean indicating whether the object can be picked up
     */
    public abstract boolean isPickable();

    /**
     *
     * @return Returns a boolean indicating whether the object can be eaten
     */
    public abstract boolean isEdible();

    /**
     *
     * @return Returns the location in the dream world, in terms of row and column
     */
    public int[] getObjectLocation(){
        return this.location.getLocation();
    }

    /**
     *
     * @return Returns the location in the dream world
     */
    public DreamLocation getDreamLocation(){return this.location;}

    /**
     * Changes the location of the object
     */
    public void changeDreamLocation(int rowChange, int colChange){
        this.location.changeLocation(rowChange,colChange);
    }

    /**
     * Sets the location of a dream object
     * @param location New dream location of the object
     */
    public void setLocation(DreamLocation location){
        this.location = location;
    }

}
