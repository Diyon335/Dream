package GameClasses;

import AbstractClasses.DreamObject;
import Enums.GameState;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ConfigReader {

    private String playerName, playerItemsString;
    private Set<DreamObject> playerItems = new HashSet<>();
    private int playerHealth, playerSouls, maxFood, maxMonsters, monsterHealth, bossHealth, soulsWon, soulsLost, defaultHealth;
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
            "default-health: 20"
    };


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

            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerSouls() {
        return playerSouls;
    }

    public int getDefaultHealth(){return this.defaultHealth;}

    public Set<DreamObject> getPlayerItems(){return this.playerItems;}

    public boolean hasSaved() {
        return hasSaved;
    }

    public int getSoulsWon(){return this.soulsWon;}

    public int getSoulsLost(){return this.soulsLost;}

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
                    "default-health: 20"
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

    public int getMaxFood() {
        return maxFood;
    }

    public int getMaxMonsters() {
        return maxMonsters;
    }

    public int getDefaultMonsterHealth(){return this.monsterHealth;}

    public int getDefaultBossHealth(){return this.bossHealth;}
}
