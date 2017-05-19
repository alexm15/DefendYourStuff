
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
import sdu.group8.ability.types.*;

@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = AbilitySPI.class)}
)
public class AbilityController implements IGameProcessingService, AbilitySPI {

    private AbilityCatalog abilityCatalog = AbilityCatalog.getInstance();

    @Override
    public void process(GameData gameData, World world) {

        for (Ability ability : world.getCastedEntities(Ability.class)) {
            ability.updateExpiration(gameData.getDelta());
            if (ability.getExpiration() <= 0) {
                world.removeEntity(ability);
            } else if (!ability.isEntityOnGround(ability, gameData)) {
                float posX = ability.getX();
                float posY = ability.getY();
                float verticalVel = ability.getVerticalVelocity();
                float weight = ability.getWeight();
                float deltaTime = gameData.getDelta();
                float dx = ability.getDx();
                float dy = ability.getDy();

                verticalVel -= weight * gameData.getGRAVITY();
                posY += ((dy - verticalVel) * deltaTime);

                if (ability.getDirection().isLeft()) {
                    posX -= (dx * deltaTime);
                } else {
                    posX += (dx * deltaTime);
                }

                ability.setX(posX);
                ability.setY(posY);
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
        float x = owner.getX();
        float y = owner.getY();
        boolean directionLeft = owner.getDirection().isLeft();

        Ability ab = abilityCatalog.getAbilityForType(abilityData).getNewInstance(owner, x, y, directionLeft);

        float offset = (owner.getWidth() / 2) + (ab.getWidth() / 2);
        if (directionLeft) {
            x -= offset;
        } else {
            x += offset;
        }

        ab.setX(x);

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
        return abilityCatalog.getAllAbilityDataForType(MeleeAbility.class);
    }

    @Override
    public List<AbilityData> getPositioningAbilities() {
        return abilityCatalog.getAllAbilityDataForType(PositioningAbility.class);
    }

    @Override
    public List<AbilityData> getSummoningAbilities() {
        return abilityCatalog.getAllAbilityDataForType(SummoningAbility.class);
    }

}
