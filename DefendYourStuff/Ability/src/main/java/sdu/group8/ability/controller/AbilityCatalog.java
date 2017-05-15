/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        if (instance == null) {
            instance = new AbilityCatalog();
        }
        return instance;
    }

    private AbilityCatalog() {

    }

    private Collection<AbilityData> getAbilityKeys() {
        return abilities.keySet();
    }
    
    public List<AbilityData> getAbilityKeyList() {
        List<AbilityData> r = new ArrayList<>();
        for (AbilityData abilityData : getAbilityKeys()) {
            r.add(abilityData);
        }
        return r;
    }

    public List<AbilityData> getAbilities(Class... abilityTypes) {
        List<AbilityData> r = new ArrayList<>();
        for (AbilityData key : getAbilityKeys()) {
            for (Class abilityType : abilityTypes) {
                //if (abilityType.equals(getAbility(key).getClass())) {
                if (abilityType.equals(key.getType())) {
                    r.add(key);
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
