/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.daynight;

import java.util.Timer;
import java.util.TimerTask;
import org.openide.util.Lookup;
import sdu.group8.commonenemy.IEnemyService;
import org.openide.util.lookup.ServiceProvider;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGameProcessingService;

/**
 *
 * @author Mads
 */

@ServiceProvider(service = IGameProcessingService.class)

public class DayNightController implements IGameProcessingService {

    private Lookup lookup = Lookup.getDefault();
    private boolean spawnEnemy = false;
    @Override
    public void process(GameData gameData, World world) {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);

        if (spawnEnemy) {
            enemyProvider.createMediumEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
            enemyProvider.createMediumEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
            spawnEnemy = false;
        }

        timer();
    }

    private void timer() {
        Timer dayNight = new Timer();
        dayNight.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnEnemy = true;
            }
        }, 120);
    }

}
