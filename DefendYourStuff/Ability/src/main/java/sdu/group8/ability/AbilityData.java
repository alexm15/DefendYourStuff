/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.common.ability.Ability;

/**
 *
 * @author joach
 */
public class AbilityData {
    
    private Map<String, Ability> abilities = new ConcurrentHashMap<>();
    
    public void addEntity(Ability ability) {
        abilities.put(ability.getName(), ability);
    }
    
    public Collection<Ability> getAbilities() {
        return abilities.values();
    }
    
    public <A extends Ability> Collection<Ability> getAbilities(Class<A>... abilityTypes) {
        Collection<Ability> r = new ArrayList<>();
        for (Ability ability : getAbilities()) {
            for (Class<A> abilityType : abilityTypes) {
                if (abilityType.equals(ability.getClass())) {
                    r.add(ability);
                }
            }
        }

        return r;
    }
    
    public void addAbility(Ability ability) {
        abilities.put(ability.getName(), ability);
    }
    
    public Ability getAbility(String name) {
        return abilities.get(name);
    }
    
    public Ability getAbility(Ability ability) {
        return abilities.get(ability.getName());
    }
    
    
}
