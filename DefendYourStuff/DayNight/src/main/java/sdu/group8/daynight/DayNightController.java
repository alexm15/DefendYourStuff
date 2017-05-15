/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.daynight;

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
    private final float COUNTDOWNTIME = 10;
    private float countdown = COUNTDOWNTIME;

    @Override
    public void process(GameData gameData, World world) {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);
        if (enemyProvider != null) {
          if (countdown <= 0) {
            enemyProvider.createMediumEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
            enemyProvider.createMediumEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
            enemyProvider.createBigEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
            enemyProvider.createBigEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
            countdown = COUNTDOWNTIME;
        }
        timer(gameData);   
        }
       
    }

    private void timer(GameData gameData) {
        countdown -= gameData.getDelta();
    }

}
