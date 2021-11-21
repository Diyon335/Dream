public enum Attacks {

    TACKLE("Tackle", 1),
    SLASH("Slash", 5);

    private String name;
    private int damage;

    /**
     * Constructor for the Attacks enum
     * @param name Name of the attack
     * @param damage The amount of damage from the attack
     */
    Attacks(String name, int damage){
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
