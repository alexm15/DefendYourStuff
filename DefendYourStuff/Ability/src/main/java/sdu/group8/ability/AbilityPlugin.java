/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;
import sdu.group8.commonabilitytypes.RangedAbility;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonability.abilities.Fireball;

/**
 *
 * @author joach
 */
public class AbilityPlugin implements IGamePluginService, IPreStartPluginService {
    
   private AbilityCatalog abilityCatalog; 
   
    public AbilityPlugin () {
        abilityCatalog = AbilityCatalog.getInstance();
    }

    @Override
    public void preStart(GameData gameData) {
        Ability ab = fireball();
        abilityCatalog.addAbility(ab);
    }
    
    @Override
    public void start(GameData gameData, World world) {
        
    }
    
    private void fireball() {
        float moveSpeed = 300;
        float weight = 0;
        float width = 30;
        float height = 30;
        Dimension dimension = new Dimension(width, height, width/2); //TODO: Should match the sprites size.
        float x = 0;
        float y = 0;
        Position position = new Position(x, y); //TODO: Should be startposition.
        float AOE = 0;
        float minDamage = 10;
        float maxDamage = 20;
        EffectContainer effectContainer = new EffectContainer();
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        String imageURL = "abilities/fireball.png";
        Ability abi = new RangedAbility(moveSpeed, weight, damageRange, imageURL, dimension, position, CollisionType.CIRCLE, "whatever", effectContainer);
        
        abilityCatalog.addAbility(new Fireball(), abi);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
