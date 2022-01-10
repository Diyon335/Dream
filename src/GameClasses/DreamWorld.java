package GameClasses;

import AbstractClasses.*;
import Commands.*;
import Enums.Attack;
import Enums.Direction;
import Enums.FightTurn;
import Enums.GameState;
import Exceptions.BadFileFormatException;
import Objects.*;

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

    private Set<Food> food = new HashSet<>();
    private Set<DreamMonster> monsters = new HashSet<>();

    private final List<String> directions = Arrays.asList("n","e","s","w");
    private final DreamLocation spawn;
    private final DreamLocation bossLocation;

    private ConfigReader config;

    private Timer timer;
    private SpawnerTask spawnerTask;

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
        this.timer = new Timer();

        // Bottom of the map, in the centre
        this.spawn = new DreamLocation(this.rows - 1, this.cols/2);

        // Top of the map in the centre
        this.bossLocation = new DreamLocation(0, this.cols/2);

        setPlayerName(getConfig().getPlayerName());

        registerCommands();

        startSpawnTimers();
    }

    /**
     *
     * @return Returns the instance of the config reader
     */
    public ConfigReader getConfig(){
        return this.config;
    }

    /**
     * Sets the name of the player
     * @param name String indicating name of player
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }

    /**
     * Starts the timer to spawn monsters and food
     */
    private void startSpawnTimers(){

        this.spawnerTask = new SpawnerTask(this);

        this.timer.schedule(this.spawnerTask, 300000, 600000);
    }

    /**
     * Loads the items that the player has in their bag
     */
    private void loadPlayerBag(){
        for (DreamObject object : getConfig().getPlayerItems()){
            this.player.getBag().addItem(object);
        }
    }

    /**
     * Saves the state and configuration of the game
     */
    public void saveAndQuit(){

        this.timer.cancel();
        this.config.saveConfig();

        this.state = GameState.OFFLINE;
    }

    /**
     * Sets the state of the game
     * @param gameState GameState enumeration
     */
    public void setState(GameState gameState){
        this.state = gameState;
    }

    /**
     * Stops the timer for spawning food and monsters
     */
    public void stopSpawnTask(){
        this.timer.cancel();
    }

    /**
     * Starts a fight between the player and a monster
     * @param monster Any moveable dream object
     */
    public void startFight(Moveable monster){

        FightTurn turn = FightTurn.PLAYER;

        System.out.println("\nYour opponent says: ");
        Utils.printDialogue(monster.getDialogue());

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nYou remember your attacks are: ");
        for (Attack attack : this.player.getAttacks()){
            System.out.println(attack.getName()+" - Does "+attack.getDamage()+" damage");
        }

        DreamObject loser = null;

        while (loser == null){

            if (turn == FightTurn.PLAYER){

                System.out.printf("[Monster's HP: %d]", monster.getHealth());
                System.out.printf("[HP:%d S:%d] Choose an attack >", this.player.getHealth(),this.player.getSouls());

                String attack = scanner.nextLine().trim();

                if (this.player.containsAttack(attack)) {

                    System.out.println("You used "+attack+" and inflicted "+this.player.getAttackDamage(attack)+" damage!");

                    monster.deductHealth(this.player.getAttackDamage(attack));

                    if (monster.getHealth() <= 0){
                        loser = monster;
                    }

                } else {
                    System.out.println("You don't know this attack. Are you dreaming?");
                }

                turn = FightTurn.MONSTER;
                continue;
            }

            Attack monsterAttack = monster.getRandomAttack();

            System.out.println("\nThe "+monster.getName()+" used "+monsterAttack.getName()+" and inflicted "+monsterAttack.getDamage()+" damage on you");

            this.player.deductHealth(monsterAttack.getDamage());

            if (this.player.getHealth() <= 0){
                loser = this.player;
            }

            turn = FightTurn.PLAYER;
        }

        if (loser instanceof Player){
            System.out.println("\nYou lost and were teleported back to spawn");
            System.out.println("You lost "+getConfig().getSoulsLost()+" souls");

            this.player.deductSouls(getConfig().getSoulsLost());
            teleportToSpawn();
            this.player.setHealth(this.config.getDefaultHealth());

            if (monster instanceof DreamBoss){
                monster.setHealth(getConfig().getDefaultBossHealth());
            }

            if (monster instanceof DreamMonster){
                monster.setHealth(getConfig().getDefaultMonsterHealth());
            }
        }

        if (loser instanceof DreamMonster){
            System.out.println("\nYou defeated the monster!");
            System.out.println("You received "+getConfig().getSoulsWon()+" souls");

            this.player.addSouls(getConfig().getSoulsWon());
            replaceWithSpace(monster);
        }

        if (loser instanceof DreamBoss){
            System.out.println("\nYou have defeated the final boss. Your dream is now safe and you can wake up");
            replaceWithSpace(monster);

            for (DreamMonster mon : this.monsters){
                replaceWithSpace(mon);
            }

            this.spawnerTask.cancel();
        }
    }

    /**
     * Teleports the player to the spawn point and replaces their position with space
     */
    private void teleportToSpawn(){
        replaceWithSpace(this.player);

        this.world[this.spawn.getRow()][this.spawn.getCol()] = this.player;
        this.player.setLocation(new DreamLocation(this.spawn.getRow(),this.spawn.getCol()));
    }

    /**
     * Replaces a location with space
     * @param object Dream object to be replaced
     */
    public void replaceWithSpace(DreamObject object){
        DreamLocation location = object.getDreamLocation();

        if (object instanceof DreamMonster){
            this.monsters.remove(object);
        }

        if (object instanceof Food){
            this.food.remove(object);
        }

        this.world[location.getRow()][location.getCol()] = new Space(location);
    }

    /**
     *
     * @return Returns a boolean indicating if the world has the maximum amount of allowed monsters
     */
    private boolean hasMaxMonsters(){
        return this.monsters.size() >= this.config.getMaxMonsters();
    }

    /**
     *
     * @return Returns a boolean indicating if the world has the maximum amount of allowed food
     */
    private boolean hasMaxFood(){
        return this.food.size() >= this.config.getMaxFood();
    }

    /**
     * Spawns monsters and food in the world
     */
    public void spawnEntities(){

        if(!hasMaxMonsters()){

            DreamLocation monsterLocation = findFirstSpace();

            DreamMonster monster = new DreamMonster(monsterLocation, getConfig().getDefaultMonsterHealth());

            this.world[monsterLocation.getRow()][monsterLocation.getCol()] = monster;

            this.monsters.add(monster);
        }

        if(!hasMaxFood()){
            DreamLocation foodLocation = findFirstSpace();

            // Has a 10% chance of spawning an apple
            if(new Random().nextDouble() > 0.9){

                Food apple = new DreamApple(foodLocation);

                this.world[foodLocation.getRow()][foodLocation.getCol()] = apple;

                this.food.add(apple);

            } else {

                Food cake = new DreamCake(foodLocation);

                this.world[foodLocation.getRow()][foodLocation.getCol()] = cake;

                this.food.add(cake);
            }
        }
    }

    /**
     *
     * @return Returns the first found instance of space in the world
     */
    public DreamLocation findFirstSpace(){

        int row = new Random().nextInt(this.rows);
        int col = new Random().nextInt(this.cols);

        if(this.world[row][col] instanceof Space){
            return this.world[row][col].getDreamLocation();

        }

        if (this.world[row][col].getDreamLocation().isSameAs(this.spawn)){
            findFirstSpace();
        }


        return findFirstSpace();
    }

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
                return;
            }

            System.out.println("Too few/many arguments. Try using >help for a list of commands");
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
        this.commands.add(new BagCommand(this));
        this.commands.add(new MapCommand(this));
        this.commands.add(new FightCommand(this));
        this.commands.add(new QuitCommand(this));
        this.commands.add(new InfoCommand(this));
        this.commands.add(new TradeSoulsCommand(this));
        this.commands.add(new SetFacingCommand(this));
        this.commands.add(new HelpCommand(this));
        this.commands.add(new EatCommand(this));
        this.commands.add(new ResetCommand(this));

        for (BaseCommand command : this.commands){
            this.commandHandler.put(command.getCommand(),command);
        }
    }

    /**
     *
     * @return Returns an array list of all available commands
     */
    public ArrayList<BaseCommand> getCommands(){return this.commands;}

    /**
     * Returns the base command if available
     * @param command A string with the same name as the command
     * @return Returns a command object
     */
    public BaseCommand getCommand(String command){
        return this.commandHandler.get(command);
    }

    /**
     * Returns a boolean if the entered move can be performed with the amount of specified steps
     * @param object Dream Object that needs to be moved
     * @param direction A string indicating the direction
     * @return Returns a boolean
     */
    private boolean isValidMove(DreamObject object, Direction direction){

        int[] tempLoc = object.getObjectLocation();

        switch (direction){
            case NORTH: tempLoc[0]-=1; break;
            case EAST: tempLoc[1]+=1; break;
            case SOUTH: tempLoc[0]+=1; break;
            case WEST: tempLoc[1]-=1; break;
        }

        //This line basically checks if the location is inside the 2x2 matrix
        return (tempLoc[0]<=this.rows-1 && tempLoc[0]>=0) && (tempLoc[1]<=this.cols-1 && tempLoc[1]>=0);
    }

    /**
     * Checks whether a location is a valid location, i.e. within the bounds of the world
     * @param location Dream location to be checked
     * @return Returns a boolean
     */
    private boolean isValidLocation(DreamLocation location){
        return (location.getRow()<=this.rows-1 && location.getRow()>=0) && (location.getCol()<=this.cols-1 && location.getCol()>=0);
    }

    /**
     * Moves a player in a specified direction for a specified amount of steps
     * @param player Player object
     * @param direction Direction to be moved in
     * @param steps Integer for number of steps
     */
    public void movePlayer(Player player, Direction direction, int steps){

        this.player.setFacing(direction);

        System.out.println("You travel "+direction);
        int travelled = 0;

        for (int i = 0; i < steps; i++){

            if (isValidMove(player, direction)){

                int[] tempLocation = player.getObjectLocation();
                int newRow = tempLocation[0] + direction.getRowChange();
                int newCol = tempLocation[1] + direction.getColChange();
                int changeDreamRow = direction.getRowChange();
                int changeDreamCol = direction.getColChange();

                DreamObject nextObject = getObjectAt(newRow, newCol);

                if (nextObject instanceof Space){
                    this.world[tempLocation[0]][tempLocation[1]] = new Space(player.getDreamLocation());
                    this.world[newRow][newCol] = player;
                    player.changeDreamLocation(changeDreamRow,changeDreamCol);
                }

                if (nextObject instanceof MiscObject || nextObject instanceof Food){
                    System.out.println("You sense something in front of you");
                    break;
                }

                if (nextObject instanceof DreamMonster){
                    System.out.println("You ran into a monster");

                    startFight((DreamMonster) nextObject);
                    break;
                }

                if (nextObject instanceof DreamBoss){
                    System.out.println("You ran into the boss of the nightmare");

                    startFight((DreamBoss) nextObject);
                    break;
                }

                travelled++;
                moveMonsters();

            } else {

                System.out.println("You can't travel any further");
                break;
            }
        }

        System.out.println("You travelled "+travelled+" steps.");
    }

    /**
     * Moves all monster objects in the world, except the boss
     */
    private void moveMonsters(){

        for (DreamMonster monster : this.monsters){

            Direction direction = Utils.parseDirection(this.directions.get(new Random().nextInt(this.directions.size())));

            DreamLocation newLocation = Utils.getNextLocation(monster.getDreamLocation(), direction);

            if (!isValidLocation(newLocation)){
                continue;
            }

            DreamObject nextObject = getObjectAt(newLocation);

            if (nextObject instanceof Space){

                // So that monsters don't ever go onto the spawn
                if (nextObject.getDreamLocation().isSameAs(this.spawn)){
                    continue;
                }

                this.world[monster.getDreamLocation().getRow()][monster.getDreamLocation().getCol()] = new Space(monster.getDreamLocation());
                this.world[newLocation.getRow()][newLocation.getCol()] = monster;
                monster.setLocation(newLocation);
                continue;
            }

            if(nextObject instanceof Player){
                System.out.println("A monster ran into you");

                startFight(monster);
                break;
            }
        }
    }

    /**
     * Indicates whether the player is in game or not
     * @return Game state
     */
    public GameState getGameState(){
        return this.state;
    }

    public boolean isACommand(String string){
        for (BaseCommand command : this.commands){
            if (command.getCommand().equalsIgnoreCase(string)){
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
    public DreamObject createObject(char symbol, DreamLocation location){

        switch (symbol){
            case 'p': return new Player(this.playerName, location, 0, 0);
            case 'm': return new DreamMonster(location, getConfig().getDefaultMonsterHealth());
            case '.': return new Space(location);
            case 'a': return new DreamApple(location);
            case 'c': return new DreamCake(location);
            case '*': return new DreamBoss(this.bossLocation, getConfig().getDefaultBossHealth());
            case 'w': return new Watch(location);
            case 'i': return new Picture(location);
            default: return null;
        }
    }

    /**
     * Parses the game file and creates a world of objects
     * @param fileName String indicating the name of the file
     * @throws BadFileFormatException Can throw an exception if the file is in the incorrect format
     * @throws IOException Can throw an input/output exception
     */
    public void parseFile(String fileName) throws BadFileFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        int row = 0;

        String line = reader.readLine();

        while (line!=null){

            if (row >= this.rows){
                throw new BadFileFormatException("Too many rows!", row, -1);
            }

            if (line.length() != this.cols){
                throw new BadFileFormatException("Incorrect column length", row, -1);
            }

            for (int col = 0; col < line.length(); col++){

                this.world[row][col] = createObject( line.charAt(col) , new DreamLocation(row, col));

                if (getObjectAt(row,col)==null){
                    throw new BadFileFormatException("Invalid character: "+line.charAt(col) , row, col);
                }

                if (this.world[row][col] instanceof Player){
                    this.player = (Player) this.world[row][col];
                    this.player.setHealth(getConfig().getPlayerHealth());
                    this.player.setSouls(getConfig().getPlayerSouls());
                    loadPlayerBag();
                }

                if (this.world[row][col] instanceof DreamMonster){
                    this.monsters.add((DreamMonster) this.world[row][col]);
                }

                if (this.world[row][col] instanceof Food){
                    this.food.add((Food) this.world[row][col]);
                }

            }

            line = reader.readLine();
            row++;
        }

        if (row != this.rows){
            throw new BadFileFormatException("Incorrect row amount",-1,-1);
        }

        if (this.monsters.size() > getConfig().getMaxMonsters()){
            throw new BadFileFormatException("Too many monsters", -1, -1);
        }

        if (this.food.size() > getConfig().getMaxFood()){
            throw new BadFileFormatException("Too many food items", -1, -1);
        }

        reader.close();
    }

    /**
     * Returns the object at a specified row and column
     * @param row Integer indicating row
     * @param col Integer indicating column
     * @return Returns a dream object
     */
    public DreamObject getObjectAt(int row, int col){
        return this.world[row][col];
    }

    /**
     * Returns a dream object at a specified dream location
     * @param location Dream location of the object
     * @return Returns a dream object
     */
    public DreamObject getObjectAt(DreamLocation location){
        return this.world[location.getRow()][location.getCol()];
    }

    /**
     *
     * @return Returns the player object
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Overridden toString method
     * @return Returns a string representation of the dream world
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        for (int row = 0; row < this.rows; row++){
            for (int col = 0; col < this.cols; col++){
                str.append(this.world[row][col].getSymbol());
            }
            str.append("\n");
        }

        return str.toString();
    }
}
