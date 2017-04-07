/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.collision.CollisionContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.EntityType;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;

/**
 *
 * @author joach
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)}
)

public class PlayerController
        implements IGameProcessingService, IGamePluginService {

    private Player player;
    private float verticalVelocity;
    private float horizontalVelocity;

    @Override
    public void process(GameData gameData, World world) {

        if (player.getHealth() == 0) {
            //TODO: remove player from world. Set isGameOver in gameData.           
        }
        //Handle gravity for player
        if (!player.isEntityOnGround(player, gameData)) {
            //player.setPosition(player.getX(), player.getY() + verticalVelocity * gameData.getDelta());
            verticalVelocity -= gameData.getGRAVITY();
        }
        else {
            verticalVelocity = 0;
        }
        horizontalVelocity = 0;

        handleMouseInput(gameData);

        handleKeyboardInput(gameData);

        player.setPosition(player.getX() + horizontalVelocity, player.getY() + verticalVelocity * gameData.getDelta());

    }

    private void handleKeyboardInput(GameData gameData) {
        if (gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            horizontalVelocity += player.getMoveSpeed() * gameData.getDelta();
        }

        if (gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            horizontalVelocity -= player.getMoveSpeed() * gameData.getDelta();
        }

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().W)) {
            if (player.isEntityOnGround(player, gameData)) {
                player.setEntityOnGround(player, gameData); //Set player to ground level if player is under ground
                verticalVelocity += player.getVerticalForce();
            }
        }
    }

    private void handleMouseInput(GameData gameData) {
        player.setAimPoint(gameData.getCursorPosition());

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_LEFT)) {
            //TODO: handle mouse click.
            System.out.println("left mouse clicked, on pos: " + player.getAimPoint());
        }

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_RIGHT)) {
            //TODO: handle mouse click.
            System.out.println("right mouse clicked, on pos: " + player.getAimPoint());
        }

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_MIDDEL)) {
            //TODO: handle mouse middel click.
            System.out.println("Middel mouse clicked, on pos: " + player.getAimPoint());
        }
    }

    @Override
    public void start(GameData gameData, World world) {
        float health = 100;
        float moveSpeed = 100;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height, width/2); //TODO: Should match the sprites size.
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        Position position = new Position(x, y); //TODO: Should be startposition.
        float AOE = 0;
        float minDamage = 0;
        float maxDamage = 0;
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        player = new Player(moveSpeed, weight, health, dimension, position, CollisionType.BOX);
        gameData.setPlayerGold(0);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
