public enum Directions {
    NORTH (-1,0),
    EAST (0,1),
    SOUTH (1,0),
    WEST (0,-1);

    private int rowChange,colChange;

    Directions(int rowChange, int colChange){
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    public int getRowChange() {
        return this.rowChange;
    }

    public int getColChange() {
        return this.colChange;
    }
}
