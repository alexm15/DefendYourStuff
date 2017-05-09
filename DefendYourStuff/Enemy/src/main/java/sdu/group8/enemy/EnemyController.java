/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IEnemyService.class)}
)
public class EnemyController implements IGameProcessingService, IGamePluginService, IEnemyService{
    
    private AI_Service aiService;

    @Override
    public void process(GameData gameData, World world) {
        aiService = Lookup.getDefault().lookup(AI_Service.class);

        for (Entity enemyEntity : world.getEntities(MediumEnemy.class, BigMeleeEnemy.class)) {
            Character enemy = (Character) enemyEntity;

            if (!enemy.isEntityOnGround(enemy, gameData)) {

            } else {
                enemy.setEntityOnGround(enemy, gameData);
            }

            if (enemy instanceof MediumEnemy) {
                aiService.rangedAI(enemy, world, gameData, 250, 350);
            } else {
                aiService.assignAttackAndDodgeEnemyAI(enemy, world, gameData);
            }

            if (enemy.getHealth() <= 0) {
                world.removeEntity(enemy);
            }
        }
        deathProcess(gameData, world);
    }

    @Override
    public void start(GameData gameData, World world) {
        createMediumEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
        createMediumEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
        createBigEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
        createBigEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    public void deathProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)) {
            Character enemy = (Character) entity;
            if (enemy.getClass().equals(MediumEnemy.class)) {
                if (enemy.getHealth() == 0) {
                    gameData.addPlayerGold(100);
                    world.removeEntity(enemy);
                }
            }
        }
    }

    @Override
    public void createMediumEnemy(World world, GameData gameData, Position position) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        AbilityData ab = abilityProvider.getRangedAbilities().get(0);
        MediumEnemy mediumEnemy = new MediumEnemy(position);
        
        mediumEnemy.getAbilityContainer().addAbility(ab);
                
        world.addEntity(mediumEnemy);
    }

    @Override
    public void createBigEnemy(World world, GameData gameData, Position position) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
//        AbilityData ab = abilityProvider.getMeleeAbilities().get(0); 
        //TODO ADD abilities to BigMeleeEnemy

        BigMeleeEnemy enemy = new BigMeleeEnemy(position);
        world.addEntity(enemy);
    }

    @Override
    public void removeAllEnemies(World world) {
        world.removeEntities(MediumEnemy.class, BigMeleeEnemy.class);
    }
}
