package GameClasses;

import AbstractClasses.DreamObject;
import Enums.GameState;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to read the contents of the configuration file
 */
public class ConfigReader {

    private String playerName, playerItemsString;
    private Set<DreamObject> playerItems = new HashSet<>();
    private int playerHealth, playerSouls, maxFood, maxMonsters, monsterHealth, bossHealth, soulsWon, soulsLost, defaultHealth;
    private int spawnTime;
    private boolean hasSaved;
    private DreamWorld world;

    private final String[] defaultContent = {
            "player-name: -",
            "player-health: 20",
            "player-souls: 0",
            "has-saved-data: false",
            "max-food: 40",
            "max-monsters: 40",
            "monster-health: 15",
            "boss-health: 100",
            "player-items: -",
            "souls-per-win: 10",
            "souls-per-loss: 5",
            "default-health: 20",
            "spawn-every-mins: 3"
    };


    /**
     * Constructor for the config reader
     * @param world Instance of the dream world
     */
    public ConfigReader(DreamWorld world){

        this.world = world;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("config.txt")));

            this.playerName = reader.readLine().split(" ")[1];
            this.playerHealth = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.playerSouls = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.hasSaved = Boolean.parseBoolean(reader.readLine().split(" ")[1]);
            this.maxFood = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.maxMonsters = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.monsterHealth = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.bossHealth = Integer.parseInt(reader.readLine().split(" ")[1]);

            this.playerItemsString = reader.readLine().split(" ")[1];

            if(!this.playerItemsString.equalsIgnoreCase("-")){

                for(String string : this.playerItemsString.split(",")){

                    DreamObject object = this.world.createObject(string.charAt(0), null);
                    this.playerItems.add(object);
                }
            }

            this.soulsWon = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.soulsLost = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.defaultHealth = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.spawnTime = Integer.parseInt(reader.readLine().split(" ")[1]);

            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Returns a string containing the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @return Returns an integer indicating how often to spawn entities
     */
    public int getSpawnTime(){return this.spawnTime;}

    /**
     *
     * @return Returns an integer with the amount of player health
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    /**
     *
     * @return Returns an integer with the amount of player souls
     */
    public int getPlayerSouls() {
        return playerSouls;
    }

    /**
     *
     * @return Returns an integer indicating the default health of the player
     */
    public int getDefaultHealth(){return this.defaultHealth;}

    /**
     *
     * @return Returns a set of items (dream objects) that the player had/has in their bag
     */
    public Set<DreamObject> getPlayerItems(){return this.playerItems;}

    /**
     *
     * @return Returns a boolean indicating if the player had any saved data
     */
    public boolean hasSaved() {
        return hasSaved;
    }

    /**
     *
     * @return Returns an integer indicating the amount of souls a player can win after defeating a monster
     */
    public int getSoulsWon(){return this.soulsWon;}

    /**
     *
     * @return Returns an integer indicating the amount of souls a player can lose if defeated by a monster
     */
    public int getSoulsLost(){return this.soulsLost;}

    /**
     * Resets the game to the default configurations and overrides all files and data
     */
    public void resetToDefault(){
        try{
            //false overwrites
            FileWriter writer = new FileWriter("config.txt", false);

            for(String string:this.defaultContent){
                writer.write(string+"\n");
            }

            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        this.world.stopSpawnTask();
        this.world.setState(GameState.OFFLINE);
    }

    /**
     * Saves the current state of the game and config
     */
    public void saveConfig(){
        try{

            String [] strings = {
                    String.format("player-name: %s", this.world.getPlayer().getName()),
                    String.format("player-health: %d", this.world.getPlayer().getHealth()),
                    String.format("player-souls: %d", this.world.getPlayer().getSouls()),
                    "has-saved-data: true",
                    "max-food: 40",
                    "max-monsters: 40",
                    "monster-health: 15",
                    "boss-health: 100",
                    String.format("player-items: %s", this.world.getPlayer().getBag().toString()),
                    "souls-per-win: 10",
                    "souls-per-loss: 5",
                    "default-health: 20",
                    "spawn-every-mins: 3"
            };

            FileWriter writer = new FileWriter("config.txt", false);
            FileWriter worldWriter = new FileWriter("saved_world.txt", false);

            for(String string: strings){
                writer.write(string+"\n");
            }

            worldWriter.write(this.world.toString());

            worldWriter.close();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Returns an integer with the maximum amount of food allowed in the game
     */
    public int getMaxFood() {
        return maxFood;
    }

    /**
     *
     * @return Returns an integer with the maximum amount of monsters allowed in the game
     */
    public int getMaxMonsters() {
        return maxMonsters;
    }

    /**
     *
     * @return Returns an integer indicating the default health of any monster
     */
    public int getDefaultMonsterHealth(){return this.monsterHealth;}

    /**
     *
     * @return Returns an integer indicating the default health of the boss
     */
    public int getDefaultBossHealth(){return this.bossHealth;}
}
