package GameClasses;

import Enums.Direction;

/**
 * A class for static methods that can be utilised
 */
public class Utils {

    /**
     * Returns an enum for the direction
     * @param string A string indicating direction, input by the user
     * @return Returns a direction constant
     */
    public static Direction parseDirection(String string){
        // Returns an enum after user enters a particular direction
        switch (string.toLowerCase()) {
            case "n", "north": return Direction.NORTH;
            case "e", "east": return Direction.EAST;
            case "s", "south": return Direction.SOUTH;
            case "w", "west": return Direction.WEST;
            default: return null;
        }
    }

    /**
     * Returns a boolean indicating if input is not an integer
     * @param string String input to be parsed
     * @return Returns a boolean
     */
    public static boolean notANumber(String string){

        try{
            int temp = Integer.parseInt(string);
        } catch (NumberFormatException e){
            return true;
        }

        return false;
    }

    /**
     * Parses a string to an integer
     * @param string String input
     * @return Returns a boolean
     */
    public static int parseNumb(String string){
        return Integer.parseInt(string);
    }

    /**
     * Prints out an array of strings
     * @param strings Array of strings
     */
    public static void printDialogue(String[] strings){
        for(String string : strings){
            System.out.println(string);
        }
    }

    /**
     * Returns the next dream location in a particular direction
     * @param currentLocation Current dream location
     * @param direction Direction in which the next dream location should be returned
     * @return Returns a dream location
     */
    public static DreamLocation getNextLocation(DreamLocation currentLocation, Direction direction){
        return new DreamLocation(currentLocation.getRow() + direction.getRowChange(), currentLocation.getCol() + direction.getColChange());
    }
}
