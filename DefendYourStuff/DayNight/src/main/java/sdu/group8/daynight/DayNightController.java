/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.daynight;

import java.util.Arrays;
import org.openide.util.Lookup;
import sdu.group8.commonenemy.IEnemyService;
import org.openide.util.lookup.ServiceProvider;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGameProcessingService;

@ServiceProvider(service = IGameProcessingService.class)

public class DayNightController implements IGameProcessingService {

 //invariant: vi tager et par a bigEnemy og MediumEnemy indtil der ikke er plads til en big enemy så tager den medium enemy 
// koden er sand så længe at enemiesene vægt går op i hinanden 
    // for at bevise det skal vi vise vores nytte og agumentere for hvorfor det er sådan(se alexsanders billede https://www.messenger.com/t/1091498974312548 ).
    private Lookup lookup = Lookup.getDefault();
    private final float COUNTDOWNTIME = 10;
    private float countdown = COUNTDOWNTIME;
    private final float BIG_ENEMY = 10; //enemy value
    private final float MEDIUM_ENEMY = 5; //enemy value

    private float levelBase = 0; //amount of enemies that there is room for in world. should be incressed over time to add to player difficulty.

    private int amountOfEnemies = 2; //total mount of enemies we can spawn

    @Override
    public void process(GameData gameData, World world) {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);
        
        levelBase += gameData.getDelta(); // to incresse the amount of enemies that thre is  spawned over time.

        if (countdown <= 0) {
            // create an array where the amount of each enemy that should be spawned is where index 0 = big and index 1 = medium
            float[] spawnEnemies = whatToSpawn();

            System.out.println(Arrays.toString(spawnEnemies));

            //spawning
            for (int j = 0; j < spawnEnemies[0]; j++) {
                enemyProvider.createBigEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
                enemyProvider.createBigEnemy(world, gameData, new Position(-600, gameData.getTILE_SIZE()));
            }
            for (int i = 0; i < spawnEnemies[1]; i++) {
                enemyProvider.createMediumEnemy(world, gameData, new Position(-600, gameData.getTILE_SIZE()));
                enemyProvider.createMediumEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));

            }

            countdown = COUNTDOWNTIME;

        }
        timer(gameData);
    }

    private void timer(GameData gameData) {
        countdown -= gameData.getDelta();
    }

    private float[] whatToSpawn() {
        float k = 0;
        float[] enemies = new float[amountOfEnemies];

        return _whatToSpawn(k, enemies);
    }

    private float[] _whatToSpawn(float fill, float[] whatToSpawn) {
        while (fill < (int) levelBase) {
            if (fill + BIG_ENEMY <= levelBase) {
                whatToSpawn[0]++;
                fill += BIG_ENEMY;
            }
            if (fill + MEDIUM_ENEMY <= levelBase) {
                whatToSpawn[1]++;
                fill += MEDIUM_ENEMY;
            } else {
                break;
            }
            System.out.println("lvl: " + levelBase);
            System.out.println("fill: " + fill);
        }
        return whatToSpawn;
    }
}
