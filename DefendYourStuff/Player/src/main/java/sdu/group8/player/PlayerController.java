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

public class PlayerController implements IGameProcessingService, IGamePluginService {

    private Player player;
    private float verticalVelocity;

    @Override
    public void process(GameData gameData, World world) {

        if (player.getHealth() == 0) {
            //TODO: remove player from world. Set isGameOver in gameData.           
        }
        handleGravity(gameData);

        handleMouseInput(gameData);

        handleKeyboardInput(gameData);
    }

    private void handleKeyboardInput(GameData gameData) {
        if (gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            player.setPosition(player.getX() + (player.getMoveSpeed() * gameData.getDelta()), player.getY());

        } else if (gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            player.setPosition(player.getX() - (player.getMoveSpeed() * gameData.getDelta()), player.getY());
        }
        if (gameData.getKeys().isKeyPressed(gameData.getKeys().W)) {
            if (isPlayerOnGround(player, gameData)) {
                setPlayerOnGround(player, gameData); //Set player to ground level if player is under ground
                verticalVelocity += player.getVerticalForce();
                player.setPosition(player.getX(), player.getY() + verticalVelocity * gameData.getDelta());
            }
        }
    }

    private void handleGravity(GameData gameData) {
        if (!isPlayerOnGround(player, gameData)) {
            player.setPosition(player.getX(), player.getY() + verticalVelocity * gameData.getDelta());
            verticalVelocity -= gameData.getGRAVITY();
        } else {
            verticalVelocity = 0;
        }
    }

    private void handleMouseInput(GameData gameData) {
        player.setAimPoint(gameData.getCursorPosition());
        if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_LEFT)) {
            //TODO: handle mouse click.
            System.out.println("left mouse clicked, on pos: " + player.getAimPoint());

        } else if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_RIGHT)) {
            //TODO: handle mouse click.
            System.out.println("right mouse clicked, on pos: " + player.getAimPoint());

        } else if (gameData.getKeys().isKeyPressed(gameData.getKeys().MOUSE_MIDDEL)) {
            //TODO: handle mouse middel click.
            System.out.println("Middel mouse clicked, on pos: " + player.getAimPoint());
        }
    }

    /**
     * Is used to finde out if the player is on the ground OR under the ground.
     *
     * @param player the player that will be checked.
     * @return true if the player is on the ground OR under the ground, else
     * false.
     */
    private boolean isPlayerOnGround(Player player, GameData gameData) {
        if (player.getPosition().getY() <= gameData.getGROUND_HEIGHT() + player.getHeight() / 2) {
            return true;
        }
        return false;
    }

    /**
     * Sets the player to ground level.
     *
     * @param player
     */
    private void setPlayerOnGround(Player player, GameData gameData) {
        player.setPosition(player.getPosition().getX(), (gameData.getGROUND_HEIGHT() + player.getHeight() / 2));

    }

    @Override
    public void start(GameData gameData, World world) {
        float health = 100;
        float moveSpeed = 100;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height); //TODO: Should match the sprites size.
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        Position position = new Position(x, y); //TODO: Should be startposition.
        CollisionContainer collision = new CollisionContainer(EntityType.PLAYER, EntityType.ALLY);
        float AOE = 0;
        float minDamage = 0;
        float maxDamage = 0;
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        Ability ability = new Ability(position, AOE, damageRange); //TODO: Should be a predifined ability.
        player = new Player(moveSpeed, weight, health, dimension, position, collision, ability);
        gameData.setPlayerGold(0);
        world.addCharacter(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeCharacter(player);
    }
}
