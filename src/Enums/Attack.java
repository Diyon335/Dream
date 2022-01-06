package Enums;

/**
 * Attack enumerations
 */
public enum Attack {

    TACKLE("Tackle", 1),
    PUNCH("Punch", 5),
    SCARE("Scare", 2),
    HAUNT("Haunt",10),
    SLASH("Slash", 5);

    private String name;
    private int damage;

    /**
     * Constructor for the Enums.Attacks enum
     * @param name Name of the attack
     * @param damage The amount of damage from the attack
     */
    Attack(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    /**
     * Gets the name of the attack
     * @return Returns a string with the name of the attack
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the damage of the attack
     * @return Returns an integer with the damage of the attack
     */
    public int getDamage() {
        return damage;
    }
}
