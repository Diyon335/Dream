package Commands;

import AbstractClasses.BaseCommand;
import AbstractClasses.DreamObject;
import Enums.Direction;
import GameClasses.DreamLocation;
import GameClasses.DreamWorld;
import GameClasses.Utils;

public class TradeSoulsCommand extends BaseCommand {
    /**
     * Constructor for Base Command
     *
     * @param world Dream world
     */
    public TradeSoulsCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "trade";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{"souls"};
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
        return "Allows you to trade souls for HP";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">trade <souls_amount>";
    }

    /**
     * Executes the command
     *
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (args.length != 1){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        if (Utils.notANumber(args[0])){
            System.out.println("Please enter a number");
            return;
        }

        int souls = Integer.parseInt(args[0]);

        if (getWorld().getPlayer().getSouls() < souls){
            System.out.println("You do not have enough souls to trade");
            return;
        }

        getWorld().getPlayer().deductSouls(souls);
        getWorld().getPlayer().addHealth(souls);
        System.out.println("Successfully traded "+souls+" souls");

    }
}
