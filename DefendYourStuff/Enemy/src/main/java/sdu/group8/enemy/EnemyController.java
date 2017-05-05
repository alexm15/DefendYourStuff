/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.IEnemyService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class EnemyController implements IGameProcessingService, IGamePluginService, IEnemyService{

    private Map<UUID, Character> enemies = new ConcurrentHashMap<>();

    @Override
    public void process(GameData gameData, World world) {

        float basePosX = (world.getChunkMiddle().getDimension().getWidth() / 2) * gameData.getTILE_SIZE();

        for (Character enemy : enemies.values()) {
            float horizontalPos = enemy.getX();

            if (enemy.getX() < basePosX) {
                horizontalPos += enemy.getMoveSpeed() * gameData.getDelta();
            } else {
                horizontalPos -= enemy.getMoveSpeed() * gameData.getDelta();
            }
            enemy.setX(horizontalPos);
        }
        deathProcess(gameData, world);
    }

    @Override
    public void start(GameData gameData, World world) {
        createMediumEnemy(world, gameData, new Position(-1600, gameData.getTILE_SIZE()));
        createMediumEnemy(world, gameData, new Position(1600, gameData.getTILE_SIZE()));
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    public void deathProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Character.class)) {
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
        MediumEnemy enemy;
        float health = 100;
        float moveSpeed = 200;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height, width / 2); //TODO: Should match the sprites size.
        float x = 0;
        float y = gameData.getTILE_SIZE();

        String imageURL = "Enemy/dickbutt.gif";
        enemy = new MediumEnemy(moveSpeed, weight, health, imageURL, dimension, position, CollisionType.BOX);
        gameData.setPlayerGold(0);
        world.addEntity(enemy);
        enemies.put(enemy.getID(), enemy);
    }

    @Override
    public void removeAllEnemies(World world) {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
