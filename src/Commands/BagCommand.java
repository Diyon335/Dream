package Commands;

import AbstractClasses.BaseCommand;
import AbstractClasses.DreamObject;
import GameClasses.DreamWorld;

import java.util.Set;

public class BagCommand extends BaseCommand {

    public BagCommand(DreamWorld world){
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "bag";
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
        return "Opens your bag";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">bag";
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

        Set<DreamObject> objects = getWorld().getPlayer().getBag().getItems();

        System.out.println("Your bag contains:");

        for(DreamObject object : objects){
            System.out.println(object.getName()+" : "+object.getDescription());
        }

    }
}
