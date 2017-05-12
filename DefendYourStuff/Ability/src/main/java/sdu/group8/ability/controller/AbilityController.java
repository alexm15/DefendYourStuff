/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.ability.types.AbilityType;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonabilitydata.abilities.*;
import sdu.group8.ability.spellbook.*;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = AbilitySPI.class)}
)
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityDataCatalog abilityDataCatalog = AbilityDataCatalog.getInstance();

    @Override
    public void process(GameData gameData, World world) {
//        for (Entity ability : world.getEntities(RangedAbility.class)) {
//            Ability ab = (Ability) ability;
//            ab.updateExpiration(gameData.getDelta());
//            if (ab.getExpiration() <= 0) {
//                world.removeEntity(ability);
//            } else if (!ab.isEntityOnGround(ab, gameData)) {
//
//                float horizontalVelocity = 0;
//                float verticalVelocity = 0;
//                horizontalVelocity = ab.getMoveSpeed() * gameData.getDelta();
//                if (ab.getWeight() != 0) {
//                    verticalVelocity = ab.getPosition().getY() - (ab.getWeight() * gameData.getGRAVITY());
//                }
//                if (ab.getDirection().isIsLeft()) {
//                    ability.setX(ability.getX() - horizontalVelocity);
//                } else if (ab.getDirection().isIsRight()) {
//                    ability.setX(ability.getX() + horizontalVelocity);
//                }
//                ability.setY(ability.getY() - verticalVelocity * gameData.getDelta());
//            }
//        }
//        for (Entity ability : world.getEntities(MeleeAbility.class)) {
//            Ability ab = (Ability) ability;
//            ab.updateExpiration(gameData.getDelta());
//            if (ab.getExpiration() <= 0) {
//                world.removeEntity(ability);
//            }
//
//        }


    }

    @Override
    public void useAbility(Entity owner, AbilityData abilityData, World world) {
        createAbility(owner, abilityData, 0, 0, world);
    }

    @Override
    public void useAbility(Entity owner, AbilityData abilityData, float aimX, float aimY, World world) {
        createAbility(owner, abilityData, aimX, aimY, world);
    }

    private void createAbility(Entity owner, AbilityData abilityData, float aimX, float aimY, World world) {

        Ability ab = null;

        if (abilityData instanceof FireballData) {
            ab = new Fireball(owner, owner.getX(), owner.getY(), owner.getDirection().isLeft());
        }
        else if (abilityData instanceof SlashData) {
            ab = new Slash(owner, owner.getX(), owner.getY(), owner.getDirection().isLeft());
        }
        
        if(ab != null) {
            world.addEntity(ab);
        }

    }

    @Override
    public List<AbilityData> getAbilities() {
        return abilityDataCatalog.getAllAbilities();
    }

    @Override
    public List<AbilityData> getRangedAbilities() {
        return abilityDataCatalog.getAbilitiesForType(AbilityType.RANGED);
    }

    @Override
    public List<AbilityData> getMeleeAbilities() {
        return abilityDataCatalog.getAbilitiesForType(AbilityType.MELEE);
    }

    @Override
    public List<AbilityData> getPositioningAbilities() {
        return abilityDataCatalog.getAbilitiesForType(AbilityType.POSITIONING);
    }

    @Override
    public List<AbilityData> getSummoningAbilities() {
        return abilityDataCatalog.getAbilitiesForType(AbilityType.SUMMONING);
    }

}
