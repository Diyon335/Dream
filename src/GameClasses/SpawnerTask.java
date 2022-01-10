package GameClasses;

import java.util.TimerTask;

/**
 * A timer task to be executed. It spawns food and monsters in the world
 */
public class SpawnerTask extends TimerTask {

    private DreamWorld world;

    /**
     * Constructor for the spawner task
     * @param world Dream world instance
     */
    public SpawnerTask(DreamWorld world){
        this.world = world;
    }

    /**
     * Runs the spawner task
     */
    @Override
    public void run() {

        this.world.spawnEntities();
    }
}
