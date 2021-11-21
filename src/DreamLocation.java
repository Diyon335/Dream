public class DreamLocation {

    private int row;
    private int col;

    /**
     * Constructor for the Location class
     * @param row Integer for the row
     * @param col Integer for the column
     */
    public DreamLocation(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Changes the row and column by a specified amount
     * @param rowChange Integer for row increase/decrease
     * @param colChange Integer for column increase/decrease
     */
    public void changeLocation(int rowChange, int colChange){
        this.row+=rowChange;
        this.col+=colChange;
    }

    /**
     *
     * @return Returns an array of integers with row and column, in that order
     */
    public int[] getLocation(){
        return new int[]{this.row,this.col};
    }

    /**
     * Returns a boolean if a specified location is the same as this location
     * @param dreamLocation Location to compare to
     * @return Returns a boolean
     */
    public boolean isSameAs(DreamLocation dreamLocation){
        return this.row== dreamLocation.getLocation()[0] && this.col== dreamLocation.getLocation()[1];
    }
}
