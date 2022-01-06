package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;

/**
 * Class for the save command
 */
public class SaveCommand extends BaseCommand {

    /**
     * Constructor for the save command
     * @param world Dream world instance
     */
    public SaveCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "save";
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
        return "Saves the state of the game";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">save";
    }

    /**
     * Executes the command
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if(args.length > 0){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        getWorld().getConfig().saveConfig();
        System.out.println("Your game was saved!");
    }
}
