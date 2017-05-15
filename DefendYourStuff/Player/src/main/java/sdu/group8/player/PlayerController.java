/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;

import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonplayer.IPlayerService;
import sdu.group8.commonweapon.services.IWeaponService;

/**
 *
 * @author joach
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IPlayerService.class)}
)
public class PlayerController
        implements IGameProcessingService, IGamePluginService, IPlayerService {

    private Lookup lookup = Lookup.getDefault();
    private float verticalVelocity;
    private float horizontalVelocity;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            if (player.getCurrentHealth() <= 0) {
            //TODO: remove player from world. Set isGameOver in gameData.
                System.out.println("Game Over");
                world.removeEntity(player);
            }
            //Handle gravity for player
            if (!player.isEntityOnGround(player, gameData)) {
                //player.setPosition(player.getX(), player.getY() + verticalVelocity * gameData.getDelta());
                verticalVelocity -= gameData.getGRAVITY() * player.getWeight();
            } else {
                player.setEntityOnGround(player, gameData);
                verticalVelocity = 0;
            }
            horizontalVelocity = 0;

            handleMouseInput(gameData, player);

            handleKeyboardInput(gameData, world, player);
            Position position = new Position(player.getX() + horizontalVelocity, player.getY() + verticalVelocity * gameData.getDelta());
            player.setPosition(position);
            gameData.setPlayerPosition(position);
        }
        
    }

    private void handleKeyboardInput(GameData gameData, World world, Player player) {
        if (gameData.getKeys().isKeyDown(gameData.getKeys().NUM_1)) {
            IWeaponService weaponProvider = Lookup.getDefault().lookup(IWeaponService.class);
            player.setWeapon(weaponProvider.createMelee());
        }
        
        if (gameData.getKeys().isKeyDown(gameData.getKeys().NUM_2)) {
            IWeaponService weaponProvider = Lookup.getDefault().lookup(IWeaponService.class);
            player.setWeapon(weaponProvider.createRanged());
        }
        
        if (gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            horizontalVelocity += player.getMoveSpeed() * gameData.getDelta();
            player.setDirection(false);
            player.getImage().setReversed(true);
        }

        if (gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            horizontalVelocity -= player.getMoveSpeed() * gameData.getDelta();
            player.setDirection(true);
            player.getImage().setReversed(false);
        }

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().W)) {
            if (player.isEntityOnGround(player, gameData)) {
                player.setEntityOnGround(player, gameData); //Set player to ground level if player is under ground
                verticalVelocity += player.getVerticalForce();
            }
        }

        if (gameData.getKeys().isKeyPressed(gameData.getKeys().SPACE)) {
            AbilitySPI abilityProvicer = Lookup.getDefault().lookup(AbilitySPI.class);
            float aimX = 0;
            float aimY = 0;
            try {
                aimX = player.getAimPoint().getX();
                aimY = player.getAimPoint().getY();
            } catch (NullPointerException e) {
                System.out.println("Mouse not in screen");
                e.printStackTrace();
            }
            world.addEntity(abilityProvicer.useAbility(player, aimX, aimY, player.getWeapon().getAbilityOne()));
        }

    }

    private void handleMouseInput(GameData gameData, Player player) {
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
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        Position position = new Position(x, y); //TODO: Should be startposition.

        Player player = new Player(position);
        gameData.setPlayerGold(0);
        IWeaponService weaponProvider = Lookup.getDefault().lookup(IWeaponService.class);
        player.setWeapon(weaponProvider.createRanged());
        world.addEntity(player);

        gameData.setPlayerPosition(position);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            world.removeEntity(player);
        }
        
    }

    @Override
    public float getPlayerMoveSpeed(World world) {
        Player player = null;
        for (Entity entity : world.getEntities(Player.class)) {
            player = (Player) entity;
        }
        return player.getMoveSpeed();
    }

    @Override
    public HealthSystem getHealthSystem(World world) {
        Player player = null;
        for (Entity entity : world.getEntities(Player.class)) {
            player = (Player) entity;
        }
        return player.getHealthSystem();
    }

}
