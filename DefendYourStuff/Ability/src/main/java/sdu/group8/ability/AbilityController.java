/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;

import java.lang.reflect.Constructor;
import sdu.group8.commonabilitytypes.MeleeAbility;
import sdu.group8.commonabilitytypes.PositioningAbility;
import sdu.group8.commonabilitytypes.RangedAbility;
import sdu.group8.commonabilitytypes.SummoningAbility;
import java.util.Collection;
import java.util.UUID;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
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
import sdu.group8.commonability.services.AbilitySPI;


@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = AbilitySPI.class)}
)


/**
 *
 * @author joach
 */
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityCatalog abilityCatalog; 
    
    public AbilityController() {
        this.abilityCatalog = AbilityCatalog.getInstance();
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
//
//    @Override
//    public Ability getAbility(String name) {
//        //return abilityCatalog.getAbility(name);
//        return fireball();
//    }

    @Override
    public Ability useAbility(Entity caller, Ability ab, GameData gameData) {
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
    public Ability usePlayerAbility(Entity caller, Ability ab, Weapon weapon, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ability useAbility(Entity caller, AbilityData abilityData, GameData gameData) {
        Ability ability;
        Ability ab = abilityCatalog.getAbility(abilityData);
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
    public Ability usePlayerAbility(Entity caller, String ab, Weapon weapon, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <A extends Ability> Collection getAbilities() {
        return abilityCatalog.getAbilities();
    }

    @Override
    public <A extends Ability> Collection getRangedAbilities() {
        return abilityCatalog.getAbilities(RangedAbility.class);
    }

    @Override
    public <A extends Ability> Collection getMeleeAbilities() {
        return abilityCatalog.getAbilities(MeleeAbility.class);
    }

    @Override
    public <A extends Ability> Collection getPositioningAbilities() {
        return abilityCatalog.getAbilities(PositioningAbility.class);
    }

    @Override
    public <A extends Ability> Collection getSummoningAbilities() {
        return abilityCatalog.getAbilities(SummoningAbility.class);
    }
    
}
