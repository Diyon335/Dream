import java.util.Set;

public class DreamMonster extends Moveable{

    public DreamMonster(DreamLocation location, int health) {
        super(location, health);

        addAttack(Attacks.SLASH);
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return 0;
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
        return new String[0];
    }

    /**
     * @return Returns a boolean indicating whether the object can move
     */
    @Override
    public boolean canMove() {
        return false;
    }

    /**
     * @return Returns a boolean indicating whether the object can fight
     */
    @Override
    public boolean canFight() {
        return false;
    }

    /**
     * @return Returns a boolean indicating whether the object can be damaged
     */
    @Override
    public boolean isDamageable() {
        return false;
    }
}
