package sdu.group8.daynight;

import java.util.Random;
import org.openide.util.Lookup;
import sdu.group8.commonenemy.IEnemyService;
import org.openide.util.lookup.ServiceProvider;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGameProcessingService;

@ServiceProvider(service = IGameProcessingService.class)

public class DayNightController implements IGameProcessingService {

    private Lookup lookup = Lookup.getDefault();
    private float countdown = 0;
    
    /** Enemy Cost value must be BIG_ENEMY_COST % MEDIUM_ENEMY_COST = 0 
     for greedy algorithm to work correctly */
    private final float BIG_ENEMY_COST = 10;
    
    /** Enemy Cost value must be BIG_ENEMY_COST % MEDIUM_ENEMY_COST = 0 
     for greedy algorithm to work correctly */
    private final float MEDIUM_ENEMY_COST = 5;

    /** The total capacity of enemies there can be in the game world. 
     * is increased over time, for a more difficult game.
     */
    private float enemyCapacityInWorld = 0; 

    @Override
    public void process(GameData gameData, World world) {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);

        enemyCapacityInWorld += gameData.getDelta(); // to increase the capacity of enemies in world.  

        if (countdown <= 0) {
            greedyEnemySpawner(enemyCapacityInWorld, enemyProvider, world, gameData);
            countdown = resetTimer();
        }
        runTimer(gameData);
    }

    /**
     * Resets the timer for a specified value
     * @return the specified value that the timer is reset to.
     */
    private float resetTimer() {
        final float COUNTDOWNTIME = 10;
        return COUNTDOWNTIME;
    }

    /**
     * Used for updating the timer every frame of the game
     * @param gameData used for retrieving delta time.
     */
    private void runTimer(GameData gameData) {
        countdown -= gameData.getDelta();
    }

    /**
     * Greedy Algorithm for spawning enemies in the game world. The algorithm
     * allways tries to maximized the number of enemies, by the following 
     * priority, based on the current capicity of enemies possible in the game: 
     * 1. Big + Medium enemy
     * 2. Big Enemy
     * 3. Medium Enemy.
     * 
     * @param enemyCap the capacity available in the game world at the given 
     * time frame, is increased every frame.
     * @param spawner the interface responsible for creating the enemies in the 
     * game
     * @param world the world the enemies are added to.
     * @param gameData
     */
    private void greedyEnemySpawner(float enemyCap, IEnemyService spawner, World world, GameData gameData) {
        while (enemyCap >= MEDIUM_ENEMY_COST) {
            if (enemyCap >= MEDIUM_ENEMY_COST + BIG_ENEMY_COST) {
                spawnBigAndMediumEnemy(spawner, world, gameData);
                enemyCap -= MEDIUM_ENEMY_COST + BIG_ENEMY_COST;
            } else if (enemyCap >= BIG_ENEMY_COST) {
                spawnBigEnemy(spawner, world, gameData);
                enemyCap -= BIG_ENEMY_COST;
            } else {
                spawnMediumEnemy(spawner, world, gameData);
                enemyCap -= MEDIUM_ENEMY_COST;
            }
        }
    }

    private void spawnBigAndMediumEnemy(IEnemyService spawner, World world, GameData gameData) {
        spawnBigEnemy(spawner, world, gameData);
        spawnMediumEnemy(spawner, world, gameData);
    }

    private void spawnBigEnemy(IEnemyService spawner, World world, GameData gameData) {
        if (spawner != null) {
            spawner.createBigEnemy(world, gameData, new Position(randomIntRange(1600, 3200), gameData.getGroundHeight()));
            spawner.createBigEnemy(world, gameData, new Position(randomIntRange(-600, -2000), gameData.getGroundHeight()));
        }
    }

    private void spawnMediumEnemy(IEnemyService spawner, World world, GameData gameData) {
        if (spawner != null) {
            spawner.createMediumEnemy(world, gameData, new Position(randomIntRange(1600, 3200), gameData.getGroundHeight()));
            spawner.createMediumEnemy(world, gameData, new Position(randomIntRange(-600, -2000), gameData.getGroundHeight()));
        }
    }

    private float randomIntRange(int min, int max) {
        Random random = new Random();
        if (min < 0 || max < 0) {
            return (random.nextInt(Math.abs(max - min)) + Math.abs(min) + 1) * -1;
        }
        return random.nextInt(max - min) + min + 1;
    }
}
