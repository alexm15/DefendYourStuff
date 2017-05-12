package sdu.group8.ability.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.ability.types.AbilityType;
import sdu.group8.commonability.data.AbilityData;

public class AbilityDataCatalog {

    private Map<AbilityData, AbilityType> abilities = new ConcurrentHashMap<>();

    private static AbilityDataCatalog instance = null;

    public static AbilityDataCatalog getInstance() {
        if (instance == null) {
            instance = new AbilityDataCatalog();
        }
        return instance;
    }

    private AbilityDataCatalog() {

    }

    private Collection<AbilityData> getAbilityKeys() {
        return abilities.keySet();
    }

    public List<AbilityData> getAllAbilities() {
        List<AbilityData> r = new ArrayList<>();
        for (AbilityData abilityData : getAbilityKeys()) {
            r.add(abilityData);
        }
        return r;
    }

    public List<AbilityData> getAbilitiesForType(AbilityType type) {
        
        List<AbilityData> r = new ArrayList<>();
        
        for(Map.Entry<AbilityData, AbilityType> entry : abilities.entrySet()) {
            if(entry.getValue().equals(type)) {
                r.add(entry.getKey());
            }
        }
        return r;
    }

    public void addAbility(AbilityData abilityData, AbilityType type) {
        abilities.put(abilityData, type);
    }

}
