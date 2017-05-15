package sdu.group8.commonability.services;

import java.util.List;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.AbilityData;

public interface AbilitySPI {

    /**
     * Creates a new Ability and adds it to world
     *
     * @param caller The entity who creates the ability
     * @param aimX  The target x position
     * @param aimY  The target y position
     * @param world The instance of world from GameEngine.
     */
    void useAbility(Entity caller, AbilityData abilityData, float aimX, float aimY, World world);
    
    
    void useAbility(Entity caller, AbilityData abilityData, World world);

    /**
     * Creates a collection of Abilities
     *
     * @return a collection of Abilities
     */
    List<AbilityData> getAbilities();

    /**
     * Creates a collection of ranged Abilities
     *
     * @return a collection of ranged Abilities
     */
    List<AbilityData> getRangedAbilities();

    /**
     * Creates a collection of melee Abilities
     *
     * @return a collection of melee Abilities
     */
    List<AbilityData> getMeleeAbilities();

    /**
     * Creates a collection of positioning Abilities
     *
     * @return a collection of positioning Abilities
     */
    List<AbilityData> getPositioningAbilities();

    /**
     * Creates a collection of summoning Abilities
     *
     * @return a collection of summoning Abilities
     */
    List<AbilityData> getSummoningAbilities();
}
