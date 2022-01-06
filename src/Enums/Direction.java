package Enums;

/**
 * Direction enumerations
 */
public enum Direction {
    NORTH (-1,0),
    EAST (0,1),
    SOUTH (1,0),
    WEST (0,-1);

    private int rowChange,colChange;

    /**
     * Constructor for Directions enum
     * @param rowChange Integer indicating the change in rows
     * @param colChange Integer indicating the change in columns
     */
    Direction(int rowChange, int colChange){
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     *
     * @return Returns an integer with the change in rows
     */
    public int getRowChange() {
        return this.rowChange;
    }

    /**
     *
     * @return Returns an integer with the change in columns
     */
    public int getColChange() {
        return this.colChange;
    }
}
