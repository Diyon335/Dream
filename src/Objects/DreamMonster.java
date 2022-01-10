package Objects;

import AbstractClasses.Moveable;
import Enums.Attack;
import GameClasses.DreamLocation;

public class DreamMonster extends Moveable {

    public DreamMonster(DreamLocation location, int health) {
        super(location, health);

        addAttack(Attack.SLASH);
        addAttack(Attack.TACKLE);
        addAttack(Attack.SCARE);
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Monster";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "A basic monster in the Dream World";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return 'm';
    }

    /**
     * @return Returns a boolean indicating whether the object can be picked up
     */
    @Override
    public boolean isPickable() {
        return false;
    }

    /**
     * @return Returns a boolean indicating whether the object can be eaten
     */
    @Override
    public boolean isEdible() {
        return false;
    }

    /**
     * @return Returns an array of strings with the object's dialogue, if any
     */
    @Override
    public String[] getDialogue() {
        return new String[]{"Don't think you'll see your family again!","I am going to defeat you","So you will be asleep forever!!"};
    }
}
