package Commands;

import AbstractClasses.BaseCommand;
import GameClasses.DreamWorld;

public class HelpCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public HelpCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "help";
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
        return "Provides a list of all commands and their usages";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">help (command) - Command optional";
    }

    /**
     * Executes the command
     *
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (args.length < 1){
            System.out.println("All commands:");

            for (BaseCommand command : getWorld().getCommands()){
                System.out.println(command.getUsage()+" - "+command.getDescription());
            }

            return;
        }

        if (args.length == 1){

            String command = args[0];

            if(getWorld().isACommand(command)){

                System.out.println(getWorld().getCommand(command).getUsage()+" - "+getWorld().getCommand(command).getDescription());
                return;
            }

            System.out.println("Invalid command name. Try >help fo a list of commands");
            return;
        }

        System.out.println("Invalid use of the command. Try >help for a list of commands");

    }
}
