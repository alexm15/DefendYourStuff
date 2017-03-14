/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.MovingEntity;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import static sdu.group8.common.data.CollisionType.BOX;
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
    
    @Override
    public void process(GameData gameData, World world) {
        if (player.getHealth() == 0) {
            //TODO Make event take UUID in instead of string
            Event event = new Event(player.getID().toString(), PLAYER_DIES); 
            gameData.addEvent(event);            
        }
        
    }

    @Override
    public void start(GameData gameData, World world) {
        float health = 100;
        float width = 0;
        float height = 0;
        Dimension dimension = new Dimension(BOX, width, height); //Should match the sprites size
        float x = 0;
        float y = 0;
        Position position = new Position(x,y); //Should be startposition
        float AOE = 0;
        float minDamage = 0;
        float maxDamage = 0;
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        Ability ability = new Ability(position, AOE, damageRange); //Should be a predifined ability
        player = new Player(health, dimension , position , ability);
        gameData.setPlayerGold(0); //TODO Move gold to playerGold
        world.addMovingEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMovingEntity(player);
    }
    
    
    
}
