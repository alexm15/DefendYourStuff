/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;

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
            if (!ability.isEntityOnGround(ability, gameData)) {
                Ability ab = (Ability) ability;
                float horizontalVelocity = 0;
                float verticalVelocity = 0;
                horizontalVelocity = ab.getMoveSpeed() * gameData.getDelta();
                if (ab.getWeight() != 0) {
                    verticalVelocity = ab.getPosition().getY() - (ab.getWeight() * gameData.getGRAVITY());
                }
                if(ab.getPosition().isDirectionLeft()) {
                    ability.setX(ability.getX() - horizontalVelocity);
                } else if(ab.getPosition().isDirectionRight()) {
                    ability.setX(ability.getX() + horizontalVelocity);
                }
                ability.setY(ability.getY() - verticalVelocity * gameData.getDelta());
                
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
    public Ability useAbility(Entity caller, AbilityData abilityData) {
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
        float x = caller.getX();
        float y = caller.getY();
        System.out.println("Y pos: " + ability.getY());
        ability.setPosition(new Position(x, y, caller.getPosition().isDirectionLeft(), caller.getPosition().isDirectionRight()));
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

}
