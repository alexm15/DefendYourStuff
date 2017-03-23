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
    
    Player player;
    private float vertivalForce;
    private GameData gd;
    
    @Override
    public void process(GameData gameData, World world) {
        this.gd = gameData;
        if (player.getHealth() == 0) {
            //TODO: remove player from world. Set isGameOver in gameData.           
        }
        //Handle gravity for player
        if (!isPlayerOnGround(player)) {
            player.setPosition(player.getX(), player.getY()+vertivalForce*gameData.getDelta());
            vertivalForce -= gameData.getGRAVITY();
        } else {
            vertivalForce = 0;
        }
        //Handle input  
        player.setAimPoint(gameData.getCursorPosition());
        
        //TODO: change E to mouse rigth click and maybe left click
        if(gameData.getKeys().isKeyPressed(gameData.getKeys().E)) {
            //TODO: handle mouse click
        }
        
        
        if(gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            player.setPosition(player.getX()+(player.getMoveSpeed()*gameData.getDelta()), player.getY());
        } else if(gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            player.setPosition(player.getX()-(player.getMoveSpeed()*gameData.getDelta()), player.getY());
        }
        if(gameData.getKeys().isKeyPressed(gameData.getKeys().W)) {
            if(isPlayerOnGround(player)) {
                vertivalForce += player.getVerticalVelocity();
                player.setPosition(player.getX(), player.getY()+vertivalForce*gameData.getDelta());
            }
        }
    }
    
    private boolean isPlayerOnGround (Player player) {
        if(player.getPosition().getY() <= gd.getGROUND_HEIGHT() + player.getHeight()/2) {
            player.setPosition(player.getPosition().getX(), (50+player.getHeight()/2));
            return true;
        }
        return false;
    }

    @Override
    public void start(GameData gameData, World world) {
        float health = 100;
        float moveSpeed = 100;
        float weight = 10;
        float width = 50;
        float height = 50;
        Dimension dimension = new Dimension(width, height); //Should match the sprites size
        float x = gameData.getDisplayWidth()/2;
        float y = gameData.getDisplayHeight()/2;
        Position position = new Position(x,y); //Should be startposition
        CollisionContainer collision = new CollisionContainer(EntityType.PLAYER, EntityType.ALLY);
        float AOE = 0;
        float minDamage = 0;
        float maxDamage = 0;
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        Ability ability = new Ability(position, AOE, damageRange); //Should be a predifined ability
        player = new Player(moveSpeed, weight, health, dimension, position, collision, ability);
        gameData.setPlayerGold(0); //TODO Move gold to playerGold
        world.addCharacter(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeCharacter(player);
    }
}
