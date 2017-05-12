/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonweapon.data;

import java.util.ArrayList;
import java.util.UUID;
import sdu.group8.commonability.data.AbilityContainer;
import sdu.group8.commonability.data.AbilityData;

/**
 *
 * @author Martin
 */
public class Weapon {
    private UUID ID;
    private AbilityContainer abilities;
    private float damageMultiplier;
    private float rangeMultiplier;

    public Weapon(float damageMultiplier, float rangeMultiplier, AbilityData... ab) {
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
    
    public AbilityData getAbilityOne() {
        return abilities.getAbilites().get(0);
    }
    
    public AbilityData getAbilityTwo() {
        return abilities.getAbilites().get(1);
    }
    
    public AbilityData getAbilityThree() {
        return abilities.getAbilites().get(2);
    }
    
    public AbilityData getAbilityFour() {
        return abilities.getAbilites().get(3);
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getRangeMultiplier() {
        return rangeMultiplier;
    }

    public void setRangeMultiplier(float rangeMultiplier) {
        this.rangeMultiplier = rangeMultiplier;
    }
    
    
}