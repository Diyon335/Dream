import java.util.Locale;

public class Utils {

    /**
     * Returns an enum for the direction
     * @param string A string indicating direction, input by the user
     * @return Returns a direction constant
     */
    public static Directions parseDirection(String string){
        //Returns an enum after user enters a particular direction
        return switch (string.toLowerCase()) {
            case "n", "north" -> Directions.NORTH;
            case "e", "east" -> Directions.EAST;
            case "s", "south" -> Directions.SOUTH;
            case "w", "west" -> Directions.WEST;
            default -> null;
        };
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
}
