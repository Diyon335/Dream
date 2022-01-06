package Commands;

import AbstractClasses.BaseCommand;
import AbstractClasses.DreamObject;
import Enums.Direction;
import GameClasses.DreamLocation;
import GameClasses.DreamWorld;
import Objects.Space;

public class InfoCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public InfoCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "info";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{};
    }

    /**
     * Gets the number of required arguments for the command
     *
     * @return Returns an integer for the required arguments
     */
    @Override
    public int getRequiredArgs() {
        return 0;
    }

    /**
     * Gets a description of the command
     *
     * @return Returns a string describing the command
     */
    @Override
    public String getDescription() {
        return "Shows you information about the object in front of you";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">info";
    }

    /**
     * Executes the command
     *
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (args.length > 0){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        Direction direction = getWorld().getPlayer().getFacing();

        DreamLocation playerLocation = getWorld().getPlayer().getDreamLocation();

        DreamObject object = getWorld().getObjectAt(playerLocation.getRow() + direction.getRowChange(),
                playerLocation.getCol() + direction.getColChange());

        if (object instanceof Space){
            System.out.println("There is nothing in front of you");
            return;
        }

        System.out.println(object.getName()+" - "+object.getDescription());

    }
}
