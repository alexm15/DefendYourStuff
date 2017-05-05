/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import sdu.group8.commonabilitytypes.MeleeAbility;
import sdu.group8.commonabilitytypes.PositioningAbility;
import sdu.group8.commonabilitytypes.RangedAbility;
import sdu.group8.commonabilitytypes.SummoningAbility;
import java.util.Collection;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.weapon.Weapon;
import sdu.group8.commonability.services.AbilitySPI;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = AbilitySPI.class)}
)
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityCatalog abilityCatalog = AbilityCatalog.getInstance();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity ability : world.getEntities(RangedAbility.class)) {
            Ability ab = (Ability) ability;
            ab.updateExpiration(gameData.getDelta());
            if (ab.getExpiration() <= 0) {
                world.removeEntity(ability);
            } else if (!ab.isEntityOnGround(ab, gameData)) {
                
                float horizontalVelocity = 0;
                float verticalVelocity = 0;
                horizontalVelocity = ab.getMoveSpeed() * gameData.getDelta();
                if (ab.getWeight() != 0) {
                    verticalVelocity = ab.getPosition().getY() - (ab.getWeight() * gameData.getGRAVITY());
                }
                if (ab.getDirection().isIsLeft()) {
                    ability.setX(ability.getX() - horizontalVelocity);
                } else if (ab.getDirection().isIsRight()) {
                    ability.setX(ability.getX() + horizontalVelocity);
                }
                ability.setY(ability.getY() - verticalVelocity * gameData.getDelta());
            }
        }
    }

    @Override
    public Ability useAbility(Entity caller, float aimX, float aimY, AbilityData abilityData) {
        Ability ability;
        Ability ab = abilityCatalog.getAbility(abilityData);
        if (ab instanceof RangedAbility) {
            ability = new RangedAbility(ab);
        } else if (ab instanceof MeleeAbility) {
            ability = new MeleeAbility(ab);
        } else if (ab instanceof PositioningAbility) {
            ability = new PositioningAbility(ab);
        } else if (ab instanceof SummoningAbility) {
            ability = new SummoningAbility(ab);
        } else {
            ability = new Ability(ab);
        }
        float x;
        float y;
        if (abilityData.isAimable()) {
            x = aimX;
            y = aimY; 
        } else {
            x = caller.getX();
            y = caller.getY();
        }
        
        ability.setDirection(new Direction(caller.getDirection()));
        ability.setPosition(new Position(x, y));
        ability.setOwner(caller);
        return ability;
    }

    @Override
    public List<AbilityData> getAbilities() {
        return abilityCatalog.getAbilityKeyList();
    }

    @Override
    public List<AbilityData> getRangedAbilities() {
        return abilityCatalog.getAbilities(RangedAbility.class);
    }

    @Override
    public List<AbilityData> getMeleeAbilities() {
        return abilityCatalog.getAbilities(MeleeAbility.class);
    }

    @Override
    public List<AbilityData> getPositioningAbilities() {
        return abilityCatalog.getAbilities(PositioningAbility.class);
    }

    @Override
    public List<AbilityData> getSummoningAbilities() {
        return abilityCatalog.getAbilities(SummoningAbility.class);
    }

    @Override
    public Ability useAbility(Entity caller, float aimX, float aimY, AbilityData abilityData, Weapon weapon) {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
