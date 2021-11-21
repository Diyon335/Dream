/**
 * The interface for commands
 */
public abstract class BaseCommand {

    DreamWorld world;

    public BaseCommand(DreamWorld world){
        this.world = world;
    }

    protected DreamWorld getWorld(){
        return this.world;
    }

    /**
     * Gets the name of the command
     *
     * @return Returns the name of the command
     */
    public abstract String getCommand();

    /**
     * Gets the required arguments for the command
     *
     * @return Returns an array of the command's arguments
     */
    public abstract String[] getArguments();

    /**
     * Gets the number of required arguments for the command
     *
     * @return Returns an integer for the required arguments
     */
    public abstract int getRequiredArgs();

    /**
     * Gets a description of the command
     *
     * @return Returns a string describing the command
     */
    public abstract String getDescription();

    /**
     * Gets the usage of the command
     *
     * @return Returns a string describing the usage of the command
     */
    public abstract String getUsage();

    public abstract void execute(String[] args);
}
