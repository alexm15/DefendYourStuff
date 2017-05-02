/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;
import sdu.group8.commonabilitytypes.RangedAbility;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonability.abilities.Fireball;

@ServiceProviders(value = {
    @ServiceProvider(service = IPreStartPluginService.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class AbilityPlugin implements IGamePluginService, IPreStartPluginService {
    
   private AbilityCatalog abilityCatalog; 
   
    public AbilityPlugin () {
        abilityCatalog = AbilityCatalog.getInstance();
    }

    @Override
    public void preStart(GameData gameData) {
        fireball();
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
        Direction direction = new Direction(false);
        float AOE = 0;
        float minDamage = 10;
        float maxDamage = 20;
        EffectContainer effectContainer = new EffectContainer();
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        String imageURL = "abilities/fireball.png";
        Ability abi = new RangedAbility(moveSpeed, weight, damageRange, imageURL, dimension, direction, position, CollisionType.CIRCLE, effectContainer);
        
        abilityCatalog.addAbility(new Fireball(), abi);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
