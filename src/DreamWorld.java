import java.io.*;
import java.util.*;

/**
 * The class that manages the objects in the dream world
 */
public class DreamWorld {

    private DreamObject[][] world;

    private int rows, cols;

    private Player player;
    private String playerName;

    private GameState state = GameState.INGAME;

    private ArrayList<BaseCommand> commands = new ArrayList<>();
    private HashMap<String,BaseCommand> commandHandler = new HashMap<>();

    private Set<String> directions = new HashSet<>(Arrays.asList("north","east","south","west","n","e","s","w"));
    private Set<DreamObject> inanimateObjects = new HashSet<>();
    private Set<DreamObject> monsters = new HashSet<>();

    private ConfigReader config;
    /**
     * Constructor for the dream world
     * @param rows The amount of rows in the matrix
     * @param cols The amount of columns in the matrix
     */
    public DreamWorld(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.world = new DreamObject[rows][cols];
        this.config = new ConfigReader(this);

        registerCommands();
    }

    public ConfigReader getConfig(){
        return this.config;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public int getPlayerHealth(){
        return this.player.getHealth();
    }

    public int getPlayerSouls(){
        return this.player.getSouls();
    }

    /**
     * Initialises the world
     */
    public void init(){}

    /**
     * Play the game
     */
    public void play(String userInput){

        String[] input = userInput.split(" ");

        String command = input[0];

        if(isACommand(command)){

            String[] args = Arrays.copyOfRange(input, 1, input.length);

            if(getCommand(command).getArguments().length == args.length){

                getCommand(command).execute(args);
                //TODO REMOVE WHEN DONE
                System.out.println(toString());
                return;
            }

            System.out.println("Too few arguments. Try using >help for a list of commands");
            return;
        }

        System.out.println("Invalid command. Try using >help for a list of commands");
    }

    /**
     * Register all commands
     */
    private void registerCommands(){
        this.commands.add(new TakeCommand(this));
        this.commands.add(new TravelCommand(this));
        this.commands.add(new SaveCommand(this));

        for (BaseCommand command : this.commands){
            this.commandHandler.put(command.getCommand(),command);
        }
    }

    private BaseCommand getCommand(String command){
        return this.commandHandler.get(command);
    }

    /**
     * Returns a boolean if the entered move can be performed with the amount of specified steps
     * @param object Dream Object that needs to be moved
     * @param direction A string indicating the direction
     * @param steps An integer indicating number of steps
     * @return Returns a boolean
     */
    private boolean isValidMove(DreamObject object, Directions direction, int steps){
        int[] tempLoc = object.getObjectLocation();

        switch (direction){
            case NORTH -> tempLoc[0]-=steps;
            case EAST -> tempLoc[1]+=steps;
            case SOUTH -> tempLoc[0]+=steps;
            case WEST -> tempLoc[1]-=steps;
        }

        //This line basically checks if the location is inside the 2x2 matrix
        return (tempLoc[0]<=this.rows && tempLoc[0]>=0) && (tempLoc[1]<=this.cols && tempLoc[1]>=0);
    }

    public void movePlayer(Player player, Directions direction, int steps){

        if(isValidMove(player,direction,steps)){

            System.out.println("You travel "+direction+". A second goes by for every step you take...");

            //TODO Handle if walks into a stationary object
            //TODO Handle if meets a monster
            for (int i = 0; i < steps; i++){
                int[] tempLocation = player.getObjectLocation();

                switch (direction){
                    case NORTH -> {
                        if (getObjectAt(tempLocation[0] - 1, tempLocation[1]) instanceof Space){
                            this.world[tempLocation[0]][tempLocation[1]] = new Space(player.getDreamLocation());
                            this.world[tempLocation[0]-1][tempLocation[1]] = player;
                            player.changeDreamLocation(-1,0);
                        }
                    }
                    case EAST -> {
                        if (getObjectAt(tempLocation[0], tempLocation[1] + 1) instanceof Space){
                            this.world[tempLocation[0]][tempLocation[1]] = new Space(player.getDreamLocation());
                            this.world[tempLocation[0]][tempLocation[1] + 1] = player;
                            player.changeDreamLocation(0,1);
                        }
                    }
                    case SOUTH -> {
                        if (getObjectAt(tempLocation[0] + 1, tempLocation[1]) instanceof Space){
                            this.world[tempLocation[0]][tempLocation[1]] = new Space(player.getDreamLocation());
                            this.world[tempLocation[0] + 1][tempLocation[1]] = player;
                            player.changeDreamLocation(1,0);
                        }
                    }
                    case WEST -> {
                        if (getObjectAt(tempLocation[0], tempLocation[1] - 1) instanceof Space){
                            this.world[tempLocation[0]][tempLocation[1]] = new Space(player.getDreamLocation());
                            this.world[tempLocation[0]][tempLocation[1] - 1] = player;
                            player.changeDreamLocation(0,-1);
                        }
                    }
                }

                Utils.sleepThread(1000);
                //TODO REMOVE WHEN DONE
                System.out.println();
            }
            return;
        }

        System.out.println("Your insufferable lucid state eludes you from going over the edge of the world. Think again.");
    }

    //TODO IMPLEMENT GET OBJECT ONE STEP IN PARTICULAR DIRECTION
    //TODO IMPLEMENT A REPLACEMENT OF PLAYER AND SPACE

    /**
     * Indicates whether the player is in game or not
     * @return Game state
     */
    public GameState getGameState(){
        return this.state;
    }

    private boolean isACommand(String string){
        for (BaseCommand command : this.commands){
            if(command.getCommand().equalsIgnoreCase(string)){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a dream object from a symbol and gives it a name
     * @param symbol The symbol from the text file that corresponds to the object
     * @param location Requires a dream location
     * @return Returns a Dream Object
     */
    private DreamObject createObject(char symbol, DreamLocation location){

        switch (symbol){
            case 'p': return new Player(this.playerName, location, 0, 0);
            case '.': return new Space(location);
            default: return null;
        }
    }

    public void parseFile(String fileName) throws BadFileFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        int row = 0;

        String line = reader.readLine();

        while(line!=null){

            if (row >= this.rows){
                throw new BadFileFormatException("Too many rows!", row, -1);
            }

            if (line.length() != this.cols){
                throw new BadFileFormatException("Incorrect column length", row, -1);
            }

            for (int col = 0; col < line.length(); col++){

                this.world[row][col] = createObject( line.charAt(col) , new DreamLocation(row, col));

                if(getObjectAt(row,col)==null){
                    throw new BadFileFormatException("Invalid character: "+line.charAt(col) , row, col);
                }

                if(this.world[row][col] instanceof Player){
                    this.player = (Player) this.world[row][col];
                    this.player.setHealth(getConfig().getPlayerHealth());
                    this.player.setSouls(getConfig().getPlayerSouls());
                }

            }

            line = reader.readLine();
            row++;
        }

        if (row != this.rows){
            throw new BadFileFormatException("Incorrect row amount",-1,-1);
        }

        reader.close();
    }

    public DreamObject getObjectAt(int row, int col){
        return this.world[row][col];
    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        for (int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.cols; col++){
                str.append(this.world[row][col].getSymbol());
            }
            str.append("\n");
        }

        return str.toString();
    }


}
