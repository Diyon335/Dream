package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;
import GameClasses.Utils;
import Objects.Player;

/**
 * Class for the travel command
 */
public class TravelCommand extends BaseCommand {

    /**
     * Constructor for the travel command
     * @param world Dream world instance
     */
    public TravelCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "travel";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{"direction","steps"};
    }

    /**
     * Gets the number of required arguments for the command
     *
     * @return Returns an integer for the required arguments
     */
    @Override
    public int getRequiredArgs() {
        return getArguments().length;
    }

    /**
     * Gets a description of the command
     *
     * @return Returns a string describing the command
     */
    @Override
    public String getDescription() {
        return "Travel in a particular direction for a specified number of steps";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">travel <direction> <steps>";
    }

    /**
     * Executes the command
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (Utils.parseDirection(args[0]) == null){
            System.out.println("Invalid direction. Try north or n, east or e, etc.");
            return;
        }

        if(Utils.notANumber(args[1])){
            System.out.println("Please enter a number for the amount of steps");
            return;
        }

        Player player = getWorld().getPlayer();

        getWorld().movePlayer(player, Utils.parseDirection(args[0]), Utils.parseNumb(args[1]));
    }
}
