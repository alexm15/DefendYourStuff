/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.CollisionContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import static sdu.group8.common.data.CollisionType.BOX;
import sdu.group8.common.data.GameKeys;
import sdu.group8.common.entity.EntityType;
import sdu.group8.common.events.Event;
import static sdu.group8.common.events.EventType.PLAYER_DIES;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;


/**
 *
 * @author joach
 */
public class PlayerController implements IGameProcessingService, IGamePluginService {
    
    Player player;
    private float vertivalVelocity;
    
    
    @Override
    public void process(GameData gameData, World world) {
        if (player.getHealth() == 0) {
            //TODO Make event take UUID in instead of string
            Event event = new Event(player.getID().toString(), PLAYER_DIES); 
            gameData.addEvent(event);            
        }
        //Handle gravity for player
        if (!isPlayerOnGround(player)) {
            player.setPosition(player.getX(), player.getY()+vertivalVelocity*gameData.getDelta());
            vertivalVelocity -= gameData.getGRAVITY();
        } else {
            vertivalVelocity = 0;
        }
        //Handle input  
        player.setAimPoint(gameData.getCursorPosition());
        if(gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            player.setPosition(player.getX()+(player.getMoveSpeed()*gameData.getDelta()), player.getY());
        } else if(gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            player.setPosition(player.getX()-(player.getMoveSpeed()*gameData.getDelta()), player.getY());
        }
        if(gameData.getKeys().isKeyDown(gameData.getKeys().W)) {
            if(isPlayerOnGround(player)) {
                vertivalVelocity += (player.getJUMP_FORCE()-player.getWeight());
                player.setPosition(player.getX(), player.getY()+vertivalVelocity*gameData.getDelta());
            }
        }
    }
    
    private boolean isPlayerOnGround (Player player) {
        if(player.getPosition().getY() >= (100+player.getHeight()/2)) {
            player.setPosition(player.getPosition().getX(), (100+player.getHeight()/2));
            return true;
        }
        return false;
    }

    @Override
    public void start(GameData gameData, World world) {
        float health = 100;
        float moveSpeed = 100;
        float weight = 10;
        float width = 0;
        float height = 0;
        Dimension dimension = new Dimension(width, height); //Should match the sprites size
        float x = 0;
        float y = 0;
        Position position = new Position(x,y); //Should be startposition
        CollisionContainer collision = new CollisionContainer(BOX, EntityType.PLAYER, EntityType.ALLY);
        float AOE = 0;
        float minDamage = 0;
        float maxDamage = 0;
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        Ability ability = new Ability(position, AOE, damageRange); //Should be a predifined ability
        player = new Player(moveSpeed, weight, health, dimension, position, collision, ability);
        gameData.setPlayerGold(0); //TODO Move gold to playerGold
        world.addCharacters(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeCharacters(player);
    }
}
