package Objects;

import AbstractClasses.Moveable;
import Enums.Attack;
import GameClasses.DreamLocation;

public class DreamBoss extends Moveable {

    public DreamBoss(DreamLocation location, int health) {
        super(location, health);

        addAttack(Attack.HAUNT);
        addAttack(Attack.SLASH);
    }

    /**
     * @return Returns an array of strings with the object's dialogue, if any
     */
    @Override
    public String[] getDialogue() {
        return new String[]{"Do you think you have what it takes","To defeat me? Think again"};
    }

    /**
     * @return Returns a boolean indicating whether the object can move
     */
    @Override
    public boolean canMove() {
        return true;
    }

    /**
     * @return Returns a boolean indicating whether the object can fight
     */
    @Override
    public boolean canFight() {
        return true;
    }

    /**
     * @return Returns a boolean indicating whether the object can be damaged
     */
    @Override
    public boolean isDamageable() {
        return true;
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Nightmare";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "The final boss of the dream world";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return '*';
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
}
