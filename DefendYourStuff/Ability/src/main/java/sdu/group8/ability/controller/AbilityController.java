/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.ability.types.RangedAbility;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = AbilitySPI.class)}
)
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityCatalog abilityCatalog = AbilityCatalog.getInstance();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity ability : world.getEntities()) {
            if (ability instanceof Ability) {
                Ability ab = (Ability) ability;
                ab.updateExpiration(gameData.getDelta());
                if (ab.getExpiration() <= 0) {
                    world.removeEntity(ability);
                } else if (!ab.isEntityOnGround(ability, gameData)) {
                    float posX = ab.getX();
                    float posY = ab.getY();
                    float verticalVel = ab.getVerticalVelocity();
                    float weight = ab.getWeight();
                    float deltaTime = gameData.getDelta();
                    float dx = ab.getDx();
                    float dy = ab.getDy();

                    verticalVel -= weight * gameData.getGRAVITY();
                    posY += ((dy - verticalVel) * deltaTime);

                    if (ab.getDirection().isLeft()) {
                        posX -= (dx * deltaTime);
                    } else {
                        posX += (dx * deltaTime);
                    }

                    ab.setX(posX);
                    ab.setY(posY);
                }
            }
        }
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
        Ability ab = abilityCatalog.getAbilityForType(abilityData).getNewInstance(owner, owner.getX(), owner.getY(), owner.getDirection().isLeft());
        world.addEntity(ab);

    }

    @Override
    public List<AbilityData> getAbilities() {
        return abilityCatalog.getAllAbilityData();
    }

    @Override
    public List<AbilityData> getRangedAbilities() {
        return abilityCatalog.getAllAbilityDataForType(RangedAbility.class);
    }

    @Override
    public List<AbilityData> getMeleeAbilities() {
        return abilityCatalog.getAllAbilityDataForType(RangedAbility.class);
    }

    @Override
    public List<AbilityData> getPositioningAbilities() {
        return abilityCatalog.getAllAbilityDataForType(RangedAbility.class);
    }

    @Override
    public List<AbilityData> getSummoningAbilities() {
        return abilityCatalog.getAllAbilityDataForType(RangedAbility.class);
    }

}
