package AbstractClasses;

import GameClasses.DreamLocation;

/**
 * Abstract class food that extends Dream Object
 */
public abstract class Food extends DreamObject {

    /**
     * Constructor for the Food
     * @param location Dream location
     */
    public Food(DreamLocation location) {
        super(location);
    }

    /**
     *
     * @return Returns an integer indicating the amount of revivable HP
     */
    public abstract int reviveHP();


}
