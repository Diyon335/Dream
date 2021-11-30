import java.util.Set;

public class Player extends Moveable {

    private String name;
    private Bag bag;
    private int souls;
    /**
     * Constructor for the player object
     * @param name Name of the player
     */
    public Player(String name, DreamLocation location, int health, int souls) {
        super(location,health);
        this.name = name;
        this.souls = souls;
        this.bag = new Bag();

        addAttack(Attacks.TACKLE);
    }

    public Bag getBag(){
        return this.bag;
    }

    public int getSouls(){
        return this.souls;
    }

    public void takeSouls(int cost){
        this.souls-=cost;
    }

    public void addSouls(int souls){
        this.souls+=souls;
    }

    public void setSouls(int souls){
        this.souls = souls;
    }

    /**
     * Gets the name of the player
     * @return Returns a string with the name of the player
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets a description of the player
     * @return Returns a string with the description of the player
     */
    @Override
    public String getDescription() {
        return "The player, aka: You";
    }

    /**
     * Gets the object's symbol
     * @return Returns a string with the object's symbol
     */
    @Override
    public char getSymbol() {
        return 'p';
    }

    /**
     * Gets the dialogue (if any)
     * @return Returns an array of string containing the object's dialogue. In the player's case, this is null
     */
    @Override
    public String[] getDialogue() {
        return null;
    }

    /**
     *
     * @return Returns a boolean indicating if the player is pickable
     */
    @Override
    public boolean isPickable() {
        return false;
    }

    /**
     *
     * @return Returns a boolean indicating whether the object can be eaten
     */
    @Override
    public boolean isEdible() {
        return false;
    }

    /**
     *
     * @return Returns a boolean indicating whether the object can be damaged
     */
    @Override
    public boolean isDamageable() {
        return true;
    }

    /**
     *
     * @return Returns a boolean indicating whether the object can move
     */
    @Override
    public boolean canMove() {
        return true;
    }

    /**
     *
     * @return Returns a boolean indicating whether the object can fight
     */
    @Override
    public boolean canFight(){
        return true;
    }


}
