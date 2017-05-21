
package sdu.group8.commonability.data;

import java.util.ArrayList;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;


public class AbilityContainer {

    private ArrayList<AbilityData> abilites;

    public AbilityContainer(AbilityData... ab) {
        abilites = new ArrayList<AbilityData>();
        for (AbilityData ability : ab) {
            abilites.add(ability);
        }
    }

    public ArrayList<AbilityData> getAbilites() {
        return abilites;
    }

    public void addAbility(AbilityData ab) {
        abilites.add(ab);
    }

    /**
     * Calls the useAbility method in AbilityData
     *
     * @param owner The owner of the abilitycontainer
     * @param abilityIndex The index in the abilitycontainer, to get
     * the actual AbilityData used.
     */
    public void useAbility(Entity owner, int abilityIndex, World world) {
        abilites.get(abilityIndex).useAbility(owner, world);
    }
    
    public void useAbility(Entity owner, int abilityIndex, float aimX, float aimY, World world) {
        abilites.get(abilityIndex).useAbility(owner, aimX, aimY, world);
    }

    public AbilityData getAbility(int abilityContainerIndex) {
        return this.abilites.get(abilityContainerIndex);
    }
    
    /**
     * Calls reduceCooldown on all the AbilityData in this AbilityContainer.
     * @param deltaTime 
     */
    public void updateCooldown(float deltaTime) {
        for(AbilityData ab : abilites) {
            ab.reduceCooldown(deltaTime);
        }
    }
}
