package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;

import java.util.Scanner;

public class ResetCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public ResetCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "reset";
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
        return "Resets the entire game";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">reset";
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

        System.out.println("Are you sure you want to reset the game? Type y or n> ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")){
            System.out.println("Game was reset. Please restart");
            getWorld().getConfig().resetToDefault();
            return;
        }

        System.out.println("Cancelled reset");
    }
}
