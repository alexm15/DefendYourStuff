/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.weapon;

import java.util.ArrayList;
import java.util.UUID;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.ability.AbilityData;

/**
 * Represent the weapon that the player can carry in game, which works
 * as a container for 4 different ability, and also amplifies the ability's 
 * damage.
 * @author Group 8
 */
public class Weapon {
    private UUID ID;
    private AbilityContainer abilities;
    private float damageMultiplier;
    private float rangeMultiplier;

    public Weapon(float damageMultiplier, float rangeMultiplier, AbilityData... ab) {
        if (damageMultiplier < 0 || rangeMultiplier < 0) {
            throw new IllegalArgumentException("damage- or rangeMultiplier cannot "
                    + "be negative value");        
        }
        this.ID = UUID.randomUUID();
        this.abilities = new AbilityContainer(ab);
        this.damageMultiplier = damageMultiplier;
        this.rangeMultiplier = rangeMultiplier;
        
    }
    
    public String getID() {
        return ID.toString();
    }

    public ArrayList<AbilityData> getAbilities() {
        return abilities.getAbilites();
    }
    
    /**
     * Retrieves the ability in the first ability slot of the weapon. 
     * @return 
     */
    public AbilityData getAbilityOne() {
        return abilities.getAbilites().get(0);
    }
    
    /**
     * Retrieves the ability in the second ability slot of the weapon.
     * @return 
     */
    public AbilityData getAbilityTwo() {
        return abilities.getAbilites().get(1);
    }
    
    /**
     * Retrieves the ability in the third ability slot of the weapon.
     * @return 
     */
    public AbilityData getAbilityThree() {
        return abilities.getAbilites().get(2);
    }
    
    /**
     * Retrieves the ability in the fourth ability slot of the weapon.
     * @return 
     */
    public AbilityData getAbilityFour() {
        return abilities.getAbilites().get(3);
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        if (damageMultiplier < 0) {
            throw new IllegalArgumentException("damageMultiplier cannot "
                    + "be negative value");        
        }
        this.damageMultiplier = damageMultiplier;
    }

    public float getRangeMultiplier() {
        return rangeMultiplier;
    }

    public void setRangeMultiplier(float rangeMultiplier) {
        if (rangeMultiplier < 0) {
            throw new IllegalArgumentException("damageMultiplier cannot "
                    + "be negative value");        
        }
        this.rangeMultiplier = rangeMultiplier;
    }
    
    
}