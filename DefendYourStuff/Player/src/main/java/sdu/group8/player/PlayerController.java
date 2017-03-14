/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.entity.Character;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.MovingEntity;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import static sdu.group8.common.data.CollisionType.BOX;
import sdu.group8.common.events.Event;
import static sdu.group8.common.events.EventType.CREATE_ABILITY;
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
        Player somePlayer;
        for (MovingEntity movingEntity : world.getMovingEntities()) {
            if (movingEntity instanceof Player) {
                somePlayer = (Player) movingEntity;
            }
        }
        if (player.getHealth() == 0) {
            //TODO Make event take UUID in instead of string
            //TODO Create eventType playerDies
            Event event = new Event(player.getID().toString(), CREATE_ABILITY); 
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
        world.addMovingEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
