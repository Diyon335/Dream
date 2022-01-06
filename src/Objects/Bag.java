package Objects;

import AbstractClasses.DreamObject;
import GameClasses.DreamWorld;

import java.util.HashSet;
import java.util.Set;

public class Bag {

    private Set<DreamObject> items = new HashSet<>();

    public Bag(){
    }

    public void addItem(DreamObject object){
        this.items.add(object);
    }

    public void removeItem(DreamObject object){
        this.items.remove(object);
    }

    public Set<DreamObject> getItems(){return this.items;}

    public boolean hasItem(DreamObject object){
        return this.items.contains(object);
    }

    public boolean isEmpty(){
        return this.items.size() == 0;
    }

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
