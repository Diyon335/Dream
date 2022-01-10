package AbstractClasses;

import GameClasses.DreamLocation;

/**
 * Abstract class for Miscellaneous Objects that extend DreamObjects
 */
public abstract class MiscObject extends DreamObject{
    /**
     * Constructor for Miscellaneous Objects
     *
     * @param location Dream location of the object
     */
    public MiscObject(DreamLocation location) {
        super(location);
    }
}
