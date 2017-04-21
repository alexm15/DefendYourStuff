/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;

import abilityTypes.MeleeAbility;
import abilityTypes.PositioningAbility;
import abilityTypes.RangedAbility;
import abilityTypes.SummoningAbility;
import data.AbilityData;
import java.util.Collection;
import java.util.UUID;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.weapon.Weapon;
import sdu.group8.commonability.AbilitySPI;


@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = AbilitySPI.class)}
)


/**
 *
 * @author joach
 */
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityData abilityData; 
    
    public AbilityController() {
        this.abilityData = AbilityPlugin.getInstance().getAbilityData();
    }
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity ability : world.getEntities(RangedAbility.class)) {
            if(!ability.isEntityOnGround(ability, gameData)) {
                Ability ab = (Ability) ability;
                float horizontalVelocity = 0;
                float verticalVelocity = 0;
                horizontalVelocity = ab.getMoveSpeed() * gameData.getDelta();
                if (ab.getWeight() != 0) {
                    verticalVelocity = ab.getPosition().getY()-(ab.getWeight()*gameData.getGRAVITY());
                }
                Position position = new Position(ability.getX() + horizontalVelocity, ability.getY() - verticalVelocity * gameData.getDelta());
                ability.setPosition(position);   
            }
            
            
            
        }
    }
    
    @Override
    public Ability getAbility(Ability ability) {
        return abilityData.getAbility(ability);
    }

    @Override
    public Ability getAbility(String name) {
        //return abilityData.getAbility(name);
        return fireball();
    }
    
    private Ability fireball() {
        float moveSpeed = 300;
        String name = "fireball";
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
        Ability abi = new RangedAbility(moveSpeed, weight, damageRange, imageURL, dimension, position, CollisionType.CIRCLE, name, effectContainer);
        
        return abi;
    }
    
    
    
    

    @Override
    public Ability createAbility(Entity caller, Ability ab, GameData gameData) {
        Ability ability;
        if(ab instanceof RangedAbility) {
            ability = new RangedAbility(ab);
        } else if (ab instanceof MeleeAbility ) {
            ability = new MeleeAbility(ab);
        } else if (ab instanceof PositioningAbility) {
            ability = new PositioningAbility(ab);
        } else if (ab instanceof SummoningAbility) {
            ability = new SummoningAbility(ab);
        } else {
            ability = new Ability(ab);
        }
        float x = caller.getX();
        float y = caller.getY();
        System.out.println("Y pos: " + ability.getY());
        ability.setPosition(x, y);
        return ability;
    }

    @Override
    public Ability createPlayerAbility(Entity caller, Ability ab, Weapon weapon, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ability createAbility(Entity caller, String ab, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ability createPlayerAbility(Entity caller, String ab, Weapon weapon, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ability getAbility(World world, UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ability getAbility(World world, String name) {
        //todo
        return null;
    }

    @Override
    public <A extends Ability> Collection getAbilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <A extends Ability> Collection getRangedAbilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <A extends Ability> Collection getMeleeAbilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <A extends Ability> Collection getPositioningAbilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <A extends Ability> Collection getSummoningAbilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
