package Commands;

import AbstractClasses.BaseCommand;
import AbstractClasses.DreamObject;
import AbstractClasses.Food;
import GameClasses.DreamWorld;

public class EatCommand extends BaseCommand {
    /**
     * Constructor for the eat command
     *
     * @param world Dream world
     */
    public EatCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "eat";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{"food"};
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
        return "Eats the food that you have in your bag";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage() {
        return ">eat <food>";
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

        for (DreamObject object : getWorld().getPlayer().getBag().getItems()){

            if (object.getName().equalsIgnoreCase(args[0])){

                if (object instanceof Food && object.isEdible()){

                    int hpRevived = ((Food) object).reviveHP();
                    getWorld().getPlayer().addHealth(hpRevived);
                    getWorld().getPlayer().getBag().removeItem(object);
                    System.out.println("You ate a(n) "+object.getName()+" and it gave you "+hpRevived+" HP");
                    return;
                }

                System.out.println("You cannot eat this item");
                return;
            }
        }

        System.out.println("You don't have a(n) "+args[0]+" in your bag");

    }
}
