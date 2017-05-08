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
    
    public AbilityContainer(AbilityData... ab) {
        abilites = new ArrayList<AbilityData>();
        for(AbilityData ability : ab) {
            abilites.add(ability);
        }
    }

    public ArrayList<AbilityData> getAbilites() {
        return abilites;
    }
    
    
    public void addAbility(AbilityData ab) {
        abilites.add(ab);
    }
    
}
