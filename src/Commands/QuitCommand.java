package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;

public class QuitCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public QuitCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "quit";
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
        return "Saves and exits the game";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">quit";
    }

    /**
     * Executes the command
     *
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {
        if(args.length > 0){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        System.out.println("Saved data and exited game!");
        getWorld().saveAndQuit();
    }
}
