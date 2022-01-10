package AbstractClasses;

import Enums.Attack;
import GameClasses.DreamLocation;

import java.util.*;

/**
 * Abstract class for Moveable, extends Dream object
 */
public abstract class Moveable extends DreamObject {

    private int health;
    private ArrayList<Attack> attacks = new ArrayList<>();

    /**
     * Constructor for moveable object
     * @param location Dream location of object
     * @param health Health of object
     */
    public Moveable(DreamLocation location, int health) {
        super(location);
        this.health = health;
    }

    /**
     *
     * @return Returns an integer indicating health
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Deducts HP from an object's health
     * @param damage Integer indicating damage
     */
    public void deductHealth(int damage){
        this.health-=damage;
    }

    /**
     * Adds HP to an object's health
     * @param hp Integer indicating health to be added
     */
    public void addHealth(int hp){
        this.health+=hp;
    }

    /**
     * Sets an objects health
     * @param health Integer indicating new HP value
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     *
     * @return Returns a set of attacks the object knows
     */
    public ArrayList<Attack> getAttacks(){
        return this.attacks;
    }

    /**
     * Adds an attack to the object's known set of attacks
     * @param attack Attack to add
     */
    public void addAttack(Attack attack){this.attacks.add(attack);}

    /**
     *
     * @return Returns a random attack from all known attacks
     */
    public Attack getRandomAttack(){
        return this.attacks.get(new Random().nextInt(this.attacks.size()));
    }

    /**
     *
     * @param attack Attack enum
     * @return Returns a boolean indicating if object knows an attack
     */
    public boolean containsAttack(String attack){

        for (Attack att : this.attacks){
            if (att.getName().equalsIgnoreCase(attack)){
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param attack Attack enum
     * @return Returns an integer indicating the damage of the attack
     */
    public int getAttackDamage(String attack){

        for (Attack att : this.attacks){
            if (att.getName().equalsIgnoreCase(attack)){
                return att.getDamage();
            }
        }

        return 0;
    }

    /**
     *
     * @return Returns an array of strings with the object's dialogue, if any
     */
    public abstract String[] getDialogue();
}
