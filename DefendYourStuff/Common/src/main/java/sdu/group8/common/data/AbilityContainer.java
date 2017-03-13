/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class AbilityContainer {
    private ArrayList<Ability> abilites;
    
    public AbilityContainer(Ability... ab) {
        abilites = new ArrayList<Ability>();
        for(Ability ability : ab) {
            abilites.add(ability);
        }
    }

    public ArrayList<Ability> getAbilites() {
        return abilites;
    }
    
}
