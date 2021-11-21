public abstract class Moveable extends DreamObject {

    private int health;

    public Moveable(DreamLocation location, int health) {
        super(location);
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeHealth(int damage){
        this.health-=damage;
    }

    public void addHealth(int hp){
        this.health+=hp;
    }

    public void setHealth(int health){
        this.health = health;
    }

    /**
     *
     * @return Returns an array of strings with the object's dialogue, if any
     */
    public abstract String[] getDialogue();

    /**
     *
     * @return Returns a boolean indicating whether the object can move
     */
    public abstract boolean canMove();

    /**
     *
     * @return Returns a boolean indicating whether the object can fight
     */
    public abstract boolean canFight();


    /**
     *
     * @return Returns a boolean indicating whether the object can be damaged
     */
    public abstract boolean isDamageable();
}
