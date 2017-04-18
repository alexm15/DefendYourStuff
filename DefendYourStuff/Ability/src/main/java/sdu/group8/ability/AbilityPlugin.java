/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.collision.CollisionContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.EntityType;
import sdu.group8.common.services.IGamePluginService;

/**
 *
 * @author joach
 */
public class AbilityPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Ability Fireball() {
        float moveSpeed = 100;
        float weight = 0;
        float width = 0;
        float height = 0;
        Dimension dimension = new Dimension(width, height, width/2); //TODO: Should match the sprites size.
        float x = 0;
        float y = 0;
        Position position = new Position(x, y); //TODO: Should be startposition.
        float AOE = 0;
        float minDamage = 10;
        float maxDamage = 20;
        EffectContainer effectContainer = new EffectContainer();
        DamageRange damageRange = new DamageRange(minDamage, maxDamage);
        String imageURL = "";
        Ability abi = new Ability(moveSpeed, weight, damageRange, imageURL, dimension, position, CollisionType.CIRLCE, effectContainer);
        
        return abi;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
