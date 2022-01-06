package GameClasses;

import java.util.TimerTask;

public class SpawnerTask extends TimerTask {

    private DreamWorld world;

    public SpawnerTask(DreamWorld world){
        this.world = world;
    }

    @Override
    public void run() {

        this.world.spawnEntities();
    }
}
