public class Space extends DreamObject{


    public Space(DreamLocation location){
        super(location);
    }

    /**
     * @return Returns a string containing the name of the object
     */
    @Override
    public String getName() {
        return "Space";
    }

    /**
     * @return Returns a string with a description of the object
     */
    @Override
    public String getDescription() {
        return "Space can be moved into";
    }

    /**
     * @return Returns a string containing the symbol that represents the object
     */
    @Override
    public char getSymbol() {
        return '.';
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
        return true;
    }

}
