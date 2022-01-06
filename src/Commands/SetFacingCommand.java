package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;
import GameClasses.Utils;

public class SetFacingCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public SetFacingCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "face";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{"direction"};
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
        return "Changes the direction you are facing";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">face <direction>";
    }

    /**
     * Executes the command
     *
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (args.length != getRequiredArgs()){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        if (Utils.parseDirection(args[0]) == null){
            System.out.println("Invalid direction. Try north or n, east or e, etc.");
            return;
        }

        getWorld().getPlayer().setFacing(Utils.parseDirection(args[0]));
        System.out.println("You are now facing "+Utils.parseDirection(args[0]).toString());
    }
}
