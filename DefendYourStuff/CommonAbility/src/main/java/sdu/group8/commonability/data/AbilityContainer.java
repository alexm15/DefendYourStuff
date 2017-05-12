/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability.data;

import java.util.ArrayList;
import org.openide.util.Lookup;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.AbilitySPI;

/**
 *
 * @author Martin
 */
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
    public void useAbility(Entity owner, int abilityIndex) {
        abilites.get(abilityIndex).useAbility(owner);
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
