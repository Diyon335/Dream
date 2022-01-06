package Commands;

import AbstractClasses.BaseCommand;
import AbstractClasses.DreamObject;
import Enums.Direction;
import GameClasses.DreamLocation;
import GameClasses.DreamWorld;
import GameClasses.Utils;

/**
 * Class for the take command
 */
public class TakeCommand extends BaseCommand {

    /**
     * Constructor for the Take command
     * @param world Dream World instance
     */
    public TakeCommand(DreamWorld world) {
        super(world);
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    @Override
    public String getCommand() {
        return "take";
    }

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    @Override
    public String[] getArguments() {
        return new String[]{"object"};
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
        return "Take an object that is in front of you and place it into your bag";
    }

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    @Override
    public String getUsage(){
        return ">take <object>";
    }

    /**
     * Executes the command
     * @param args An array of strings as arguments
     */
    @Override
    public void execute(String[] args) {

        if (args.length != 1){
            System.out.println("Invalid use of the command. Try >help for a list of commands");
            return;
        }

        Direction direction = getWorld().getPlayer().getFacing();

        DreamLocation playerLocation = getWorld().getPlayer().getDreamLocation();

        DreamObject object = getWorld().getObjectAt(playerLocation.getRow() + direction.getRowChange(),
                                                    playerLocation.getCol() + direction.getColChange());

        if(!object.getName().equalsIgnoreCase(args[0])){
            System.out.println("There is no "+args[0]+" in front of you");
            return;
        }

        if (!object.isPickable()){
            System.out.println("You cannot pickup this object");
            return;
        }

        System.out.println("You picked up a(n) "+object.getName());
        getWorld().getPlayer().getBag().addItem(object);
        getWorld().replaceWithSpace(object);

    }
}
