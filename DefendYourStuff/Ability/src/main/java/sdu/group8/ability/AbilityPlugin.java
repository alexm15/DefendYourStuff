/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;
import abilityTypes.RangedAbility;
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

/**
 *
 * @author joach
 */
public class AbilityPlugin implements IGamePluginService {
    
   private AbilityData abilityData; 
   private static AbilityPlugin instance = null;
   
   public static AbilityPlugin getInstance() {
      if(instance == null) {
         instance = new AbilityPlugin();
      }
      return instance;
   }
    
    private AbilityPlugin () {
        abilityData = new AbilityData();
    }

    public AbilityData getAbilityData() {
        return abilityData;
    }

    @Override
    public void start(GameData gameData, World world) {
        Ability ab = fireball();
        abilityData.addAbility(ab);
    }
    
    private Ability fireball() {
        float moveSpeed = 100;
        String name = "fireball";
        float weight = 0;
        float width = 10;
        float height = 10;
        Dimension dimension = new Dimension(width, height, width/2); //TODO: Should match the sprites size.
        float x = 0;
        float y = 0;
        Position position = new Position(x, y); //TODO: Should be startposition.
        float AOE = 0;
        float minDamage = 10;
        float maxDamage = 20;
        EffectContainer effectContainer = new EffectContainer();
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        String imageURL = "Player/defaultPlayer.PNG";
        Ability abi = new RangedAbility(moveSpeed, weight, damageRange, imageURL, dimension, position, CollisionType.CIRCLE, name, effectContainer);
        
        return abi;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
