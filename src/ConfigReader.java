import java.io.*;

public class ConfigReader {

    private String playerName;
    private int playerHealth, playerSouls, maxFood, maxMonsters;
    private boolean hasSaved;
    private DreamWorld world;

    private final String[] defaultContent = {
            "player-name: -",
            "player-health: 20",
            "player-souls: 0",
            "has-saved-data: false",
            "max-food: 10",
            "max-monsters: 15"
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

    public boolean hasSaved() {
        return hasSaved;
    }

    public void resetToDefault(){
        try{
            //false overwrites
            FileWriter writer = new FileWriter(new File("config.txt"), false);

            for(String string:this.defaultContent){
                writer.write(string+"\n");
            }

            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveConfig(){
        try{
            String [] strings = {
                    String.format("player-name: %s", this.world.getPlayer().getName()),
                    String.format("player-health: %d", this.world.getPlayerHealth()),
                    String.format("player-souls: %d", this.world.getPlayerSouls()),
                    "has-saved-data: true",
                    "max-food: 10",
                    "max-monsters: 15"
            };

            FileWriter writer = new FileWriter(new File("config.txt"), false);
            FileWriter worldWriter = new FileWriter(new File("saved_world.txt"), false);

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
}
