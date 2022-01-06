package Objects;

import AbstractClasses.MiscObject;
import GameClasses.DreamLocation;

public class Picture extends MiscObject {
    /**
     * Constructor for the Dream Object
     *
     * @param location Dream location of the object
     */
    public Picture(DreamLocation location) {
        super(location);
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Picture";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "A picture of someone. You can't tell who it is because they have no face.. but they seem familiar";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return 'i';
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
