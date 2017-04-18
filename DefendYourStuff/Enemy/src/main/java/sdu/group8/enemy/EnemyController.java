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
import sdu.group8.common.data.DamageRange;
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
public class EnemyController implements IGameProcessingService, IGamePluginService, IEnemyService {

    private Map<UUID, Character> enemies = new ConcurrentHashMap<>();

    @Override
    public void process(GameData gameData, World world) {

    }

    @Override
    public void start(GameData gameData, World world) {
        createMediumEnemy(world, gameData);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    @Override
    public void createMediumEnemy(World world, GameData gameData) {
        MediumEnemy enemy;
        float health = 100;
        float moveSpeed = 200;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height, width / 2); //TODO: Should match the sprites size.
        float x = 0;
        float y = gameData.getTILE_SIZE() * 1.5f;
        Position position = new Position(x, y); //TODO: Should be startposition.
        
        String imageURL = "Enemy/dickbutt.gif";
        enemy = new MediumEnemy(moveSpeed, weight, health, imageURL, dimension, position, CollisionType.BOX);
        gameData.setPlayerGold(0);
        world.addEntity(enemy);
        enemies.put(enemy.getID(), enemy);
    }

}
