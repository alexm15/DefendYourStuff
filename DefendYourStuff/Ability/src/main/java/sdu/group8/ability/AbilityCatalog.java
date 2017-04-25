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
import sdu.group8.common.ability.AbilityData;

/**
 *
 * @author joach
 */
public class AbilityCatalog {
    
    private Map<AbilityData, Ability> abilities = new ConcurrentHashMap<>();
    
    private static AbilityCatalog instance = null;
   
    public static AbilityCatalog getInstance() {
      if(instance == null) {
         instance = new AbilityCatalog();
      }
      return instance;
    }
    
    private AbilityCatalog() {
        
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
    
    public void addAbility(AbilityData abilityData, Ability ability) {
        abilities.put(abilityData, ability);
    }
    
    public Ability getAbility(AbilityData abilityData) {
        return abilities.get(abilityData);
    }   
    
}
