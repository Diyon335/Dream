import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Moveable extends DreamObject {

    private int health;
    private HashMap<String,Attacks> attacks = new HashMap<>();

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

    public Set<Attacks> getAttacks(){
        Set<Attacks> toReturn = new HashSet<>();
        for (Map.Entry<String,Attacks> entry : this.attacks.entrySet()){
            toReturn.add(entry.getValue());
        }
        return toReturn;
    }

    public void addAttack(Attacks attack){this.attacks.put(attack.getName(),attack);}

    public boolean containsAttack(Attacks attacks){
        for (Map.Entry<String,Attacks> entry : this.attacks.entrySet()){
            if(entry.getKey().equalsIgnoreCase(attacks.getName())){
                return true;
            }
        }
        return false;
    }

    public int getAttackDamage(Attacks attacks){
        return this.attacks.get(attacks.getName()).getDamage();
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
