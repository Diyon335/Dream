package Exceptions;

/**
 * Class for the bad file format exception
 */
public class BadFileFormatException extends Exception {

    private String errorMessage;
    private int row;
    private int column;

    /**
     * @param errorMessage - gives an error message
     * @param row - row at which it occurred
     * @param column - column at which it occurred
     * This is the constructor for this class
     */
    public BadFileFormatException (String errorMessage, int row, int column){
        this.errorMessage = errorMessage;
        this.row = row;
        this.column = column;
    }

    /**
     * @return returns a string with the exception message
     */
    @Override
    public String toString() {
        return errorMessage+" at row: "+row+" and column: "+column;
    }
}