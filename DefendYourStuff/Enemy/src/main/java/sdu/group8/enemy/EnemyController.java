/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import sdu.group8.enemies.MediumEnemy;
import sdu.group8.enemies.BigMeleeEnemy;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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
import sdu.group8.commonenemy.IEnemyService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IEnemyService.class)}
)
public class EnemyController
        implements IGameProcessingService, IGamePluginService, IEnemyService {

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
                aiService.rangedAI(enemy, world, gameData, 350);
            } else {
                aiService.assignAttackAndDodgeEnemyAI(enemy, world, gameData);
            }

            if (enemy.getHealth() <= 0) {
                world.removeEntity(enemy);
            }
        }
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

    @Override
    public void createMediumEnemy(World world, GameData gameData, Position position) {
        MediumEnemy enemy;
        float health = 100;
        float moveSpeed = 100;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height, width / 2); //TODO: Should match the sprites size.
        float x = 0;
        float y = gameData.getTILE_SIZE();
        Direction direction = new Direction(true);

        String imageURL = "Enemy/EnemyBow.png";
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        AbilityData ab = abilityProvider.getRangedAbilities().get(0);

        enemy = new MediumEnemy(moveSpeed, weight, health, imageURL, dimension, direction, position, CollisionType.BOX, ab);
        world.addEntity(enemy);
    }

    @Override
    public void createBigEnemy(World world, GameData gameData, Position position) {
        BigMeleeEnemy enemy;
        float health = 100;
        float moveSpeed = 150;
        float weight = 5;
        float width = 25;
        float height = 25;
        Dimension dimension = new Dimension(width, height, width / 2); //TODO: Should match the sprites size.
        float x = 0;
        float y = gameData.getTILE_SIZE();
        Direction direction = new Direction(true);

        String imageURL = "Enemy/EnemySword.png";
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
//        AbilityData ab = abilityProvider.getMeleeAbilities().get(0); //TODO ADD abilities

        enemy = new BigMeleeEnemy(moveSpeed, weight, health, imageURL, dimension, direction, position, CollisionType.CIRCLE);
        world.addEntity(enemy);
    }

    @Override
    public void removeAllEnemies(World world) {
        world.removeEntities(MediumEnemy.class, BigMeleeEnemy.class);
    }

}
