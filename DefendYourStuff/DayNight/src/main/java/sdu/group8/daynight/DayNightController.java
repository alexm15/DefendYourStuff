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

public class DayNightController
        implements IGameProcessingService {

    private Lookup lookup = Lookup.getDefault();
    private float countdown = 0;
    private final float BIG_ENEMY_COST = 10; //enemy value
    private final float MEDIUM_ENEMY_COST = 5; //enemy value

    private float enemyCapacityInWorld = 0; //should be incressed over time to add difficulty.

    @Override
    public void process(GameData gameData, World world) {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);

        enemyCapacityInWorld += gameData.getDelta(); // to incresse the capacity of enemies in world.  

        if (countdown <= 0) {
            greedyEnemySpawner(enemyCapacityInWorld, enemyProvider, world, gameData);
            countdown = resetTimer();
        }
        runTimer(gameData);
    }

    private float resetTimer() {
        final float COUNTDOWNTIME = 10;
        return COUNTDOWNTIME;
    }

    private void runTimer(GameData gameData) {
        countdown -= gameData.getDelta();
    }

    private void greedyEnemySpawner(float enemyCap, IEnemyService spawner, World world, GameData gameData) {
        while (enemyCap >= MEDIUM_ENEMY_COST) {
            if (enemyCap >= MEDIUM_ENEMY_COST + BIG_ENEMY_COST) {
                spawnBigAndMediumEnemy(spawner, world, gameData);
                enemyCap -= MEDIUM_ENEMY_COST + BIG_ENEMY_COST;
            }
            else if (enemyCap >= BIG_ENEMY_COST) {
                spawnBigEnemy(spawner, world, gameData);
                enemyCap -= BIG_ENEMY_COST;
            }
            else {
                spawnMediumEnemy(spawner, world, gameData);
                enemyCap -= MEDIUM_ENEMY_COST;
            }
        }
        System.out.println();
        System.out.println();
    }

    private void spawnBigAndMediumEnemy(IEnemyService spawner, World world, GameData gameData) {
        spawnBigEnemy(spawner, world, gameData);
        spawnMediumEnemy(spawner, world, gameData);
    }

    private void spawnBigEnemy(IEnemyService spawner, World world, GameData gameData) {

        spawner.createBigEnemy(world, gameData, new Position(randomIntRange(1600, 3200), gameData.getGroundHeight()));
        spawner.createBigEnemy(world, gameData, new Position(randomIntRange(-600, -2000), gameData.getGroundHeight()));
    }

    private void spawnMediumEnemy(IEnemyService spawner, World world, GameData gameData) {
        spawner.createMediumEnemy(world, gameData, new Position(randomIntRange(1600, 3200), gameData.getGroundHeight()));
        spawner.createMediumEnemy(world, gameData, new Position(randomIntRange(-600, -2000), gameData.getGroundHeight()));
    }

    private float randomIntRange(int min, int max) {
        Random random = new Random();
        if (min < 0 || max < 0) {
            return (random.nextInt(Math.abs(max - min))+ Math.abs(min) + 1) * -1;
        }
        return random.nextInt(max - min)+ min + 1;
    }
}
