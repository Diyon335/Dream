package Objects;

import AbstractClasses.MiscObject;
import GameClasses.DreamLocation;

public class Watch extends MiscObject {
    /**
     * Constructor for the Dream Object
     *
     * @param location Dream location of the object
     */
    public Watch(DreamLocation location) {
        super(location);
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Watch";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "A pocket watch. It's strange since it has no hands, and tells no time";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return 'w';
    }

    /**
     * @return Returns a boolean indicating whether the object can be picked up
     */
    @Override
    public boolean isPickable() {
        return true;
    }

    /**
     * @return Returns a boolean indicating whether the object can be eaten
     */
    @Override
    public boolean isEdible() {
        return false;
    }
}
