import java.util.HashSet;
import java.util.Set;

public class Bag {

    private final int capacity = 20;
    private int inBag;
    private Set<DreamObject> items = new HashSet<>();

    public Bag(){
    }

    public void addItem(DreamObject object){
        this.items.add(object);
        this.inBag-=1;
    }

    public void takeItem(DreamObject object){
        this.items.remove(object);
        this.inBag+=1;
    }

    public boolean hasItem(DreamObject object){
        return this.items.contains(object);
    }

    public boolean isFull(){
        return this.inBag >= this.capacity;
    }
}
