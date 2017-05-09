/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

import java.util.ArrayList;
import sdu.group8.common.ability.Ability;

/**
 *
 * @author Martin
 */
public class AbilityContainer {
    private ArrayList<AbilityData> abilites;
    private float cooldownOne = 0.5f;
    private float cooldownTwo = 0.5f;
    private float cooldownThree = 0.5f;
    private float cooldownFour = 0.5f;
    
    public AbilityContainer(AbilityData... ab) {
        abilites = new ArrayList<AbilityData>();
        for(AbilityData ability : ab) {
            abilites.add(ability);
        }
        
    }

    public float getCooldownOne() {
        return cooldownOne;
    }

    public void setCooldownOne(float cooldownOne) {
        this.cooldownOne = cooldownOne;
    }

    public float getCooldownTwo() {
        return cooldownTwo;
    }

    public void setCooldownTwo(float cooldownTwo) {
        this.cooldownTwo = cooldownTwo;
    }

    public float getCooldownThree() {
        return cooldownThree;
    }

    public void setCooldownThree(float cooldownThree) {
        this.cooldownThree = cooldownThree;
    }

    public float getCooldownFour() {
        return cooldownFour;
    }

    public void setCooldownFour(float cooldownFour) {
        this.cooldownFour = cooldownFour;
    }
    
    public ArrayList<AbilityData> getAbilites() {
        return abilites;
    }
    
    public AbilityData getAbilityOne() {
        return abilites.get(0);
    }
    
    public AbilityData getAbilityTwo() {
        return abilites.get(1);
    }
    
    public AbilityData getAbilityThree() {
        return abilites.get(2);
    }
    
    public AbilityData getAbilityFour() {
        return abilites.get(3);
    }
    
    public void addAbility(AbilityData ab) {
        abilites.add(ab);
    }   
}
