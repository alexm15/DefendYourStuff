/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IEnemyService.class)}
)
public class EnemyController implements IGameProcessingService, IGamePluginService, IEnemyService {

    private AI_Service aiService;

    @Override
    public void process(GameData gameData, World world) {
        aiService = Lookup.getDefault().lookup(AI_Service.class);

        for (Enemy enemy : world.getCastedEntities(Enemy.class)) {
            enemy.getAbilityContainer().updateCooldown(gameData.getDelta());
            deathProcess(enemy, gameData, world);
            enemy.setEntityOnGround(enemy, gameData);

            if (aiService != null) {
                if (enemy instanceof MediumEnemy) {
                    aiService.rangedAI(enemy, world, gameData, 250, 350);
                } else {
                    aiService.assignAttackAndDodgeEnemyAI(enemy, world, gameData);
                }

                if (enemy.getCurrentHealth() <= 0) {
                    world.removeEntity(enemy);
                }
            }
        }

    }

    @Override
    public void start(GameData gameData, World world) {}

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntities(Enemy.class);
    }

    public void deathProcess(Enemy enemy, GameData gameData, World world) {
        if (enemy.getCurrentHealth() == 0) {
            gameData.addPlayerGold(100);
            world.removeEntity(enemy);
        }

    }

    @Override
    public void createMediumEnemy(World world, GameData gameData, Position position) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        try {
            AbilityData fireballAbility = abilityProvider.getRangedAbilities().get(0);
            Enemy mediumEnemy = new MediumEnemy(position, fireballAbility);
            world.addEntity(mediumEnemy);
        } catch (Exception e) {
        }
    }

    @Override
    public void createBigEnemy(World world, GameData gameData, Position position) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        try {
            AbilityData ab = abilityProvider.getMeleeAbilities().get(0); 
            BigMeleeEnemy enemy = new BigMeleeEnemy(position, ab);
            world.addEntity(enemy);
        } catch (Exception e) {
        
        }
    }

    @Override
    public void removeAllEnemies(World world) {
        world.removeEntities(Enemy.class);
    }
}
