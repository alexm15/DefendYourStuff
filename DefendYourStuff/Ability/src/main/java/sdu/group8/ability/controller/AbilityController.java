/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import java.util.List;
import sdu.group8.commonability.types.MeleeAbility;
import sdu.group8.commonability.types.PositioningAbility;
import sdu.group8.commonability.types.RangedAbility;
import sdu.group8.commonability.types.SummoningAbility;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGameProcessingService;
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
        for (Entity ability : world.getEntities(MeleeAbility.class)) {
            Ability ab = (Ability) ability;
            ab.updateExpiration(gameData.getDelta());
            if (ab.getExpiration() <= 0) {
                world.removeEntity(ability);
            }

        }

    }

    @Override
    public void useAbility(Entity caller, AbilityData abilityData, World world) {
        createAbility(caller, abilityData, 0, 0, world);
    }
    
    @Override
    public void useAbility(Entity caller, AbilityData abilityData, float aimX, float aimY, World world) {
        createAbility(caller, abilityData, aimX, aimY, world);
    }

    private void createAbility(Entity caller, AbilityData abilityData, float aimX, float aimY, World world) {
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
        ability.setDirection(new Direction(caller.getDirection()));
        if (abilityData.isAimable()) {
            x = aimX;
            y = aimY;
        } else {
            if (ability.getDirection().isIsLeft()) {
                x = caller.getX() - caller.getWidth() / 2 - caller.getWidth() / 4;
            } else {
                x = caller.getX() + caller.getWidth() / 2 + caller.getWidth() / 4;
            }
            y = caller.getY();
        }

        System.out.println("Y pos: " + ability.getY());
        
        ability.setDirection(new Direction(caller.getDirection()));
        ability.setPosition(new Position(x, y));
        ability.setOwner(caller);
        
        world.addEntity(ability);
    }

    @Override
    public List<AbilityData> getAbilities() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AbilityData> getRangedAbilities() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AbilityData> getMeleeAbilities() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AbilityData> getPositioningAbilities() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AbilityData> getSummoningAbilities() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
