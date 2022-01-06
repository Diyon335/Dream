package GameClasses;

import Enums.Direction;

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

    public static boolean notANumber(String string){

        try{
            int temp = Integer.parseInt(string);
        } catch (NumberFormatException e){
            return true;
        }

        return false;
    }

    public static int parseNumb(String string){
        return Integer.parseInt(string);
    }

    public static void sleepThread(long mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printDialogue(String[] strings){
        for(String string : strings){
            System.out.println(string);
        }
    }

    public static DreamLocation getNextLocation(DreamLocation currentLocation, Direction direction){
        return new DreamLocation(currentLocation.getRow() + direction.getRowChange(), currentLocation.getCol() + direction.getColChange());
    }
}
