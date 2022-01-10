package Objects;

import AbstractClasses.DreamObject;
import GameClasses.DreamWorld;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for the player's bag
 */
public class Bag {

    private Set<DreamObject> items = new HashSet<>();

    /**
     * Constructor for the bag
     */
    public Bag(){
    }

    /**
     * Adds an item to the bag
     * @param object A dream object to be added
     */
    public void addItem(DreamObject object){
        this.items.add(object);
    }

    /**
     * Removes an item from the bag
     * @param object A dream object to be removed
     */
    public void removeItem(DreamObject object){
        this.items.remove(object);
    }

    /**
     *
     * @return Returns a set of dream objects in the bag
     */
    public Set<DreamObject> getItems(){return this.items;}

    /**
     * Returns a boolean indicating if the bag has a particular object
     * @param object Dream object to be checked for
     * @return Returns a boolean
     */
    public boolean hasItem(DreamObject object){
        return this.items.contains(object);
    }

    /**
     *
     * @return Returns a boolean indicating if the bag is empty
     */
    public boolean isEmpty(){
        return this.items.size() == 0;
    }

    /**
     * Overridden toString method
     * @return Returns a string representation of the contents of the bag
     */
    @Override
    public String toString(){

        if(isEmpty()){
            return "-";
        }

        StringBuilder string = new StringBuilder("");

        int i = 0;
        for (DreamObject item : getItems()){
            string.append(item.getSymbol());

            if(i < this.items.size() - 1){
                string.append(",");
            }

            i++;
        }

        return string.toString();
    }
}
