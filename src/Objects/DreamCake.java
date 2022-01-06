package Objects;

import AbstractClasses.Food;
import GameClasses.DreamLocation;

public class DreamCake extends Food {

    public DreamCake(DreamLocation location) {
        super(location);
    }

    @Override
    public int reviveHP() {
        return 5;
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Cake";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "A little cake made out of Dreams. Gives you "+reviveHP()+" HP";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return 'c';
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
        return true;
    }
}
