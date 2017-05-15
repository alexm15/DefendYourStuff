package sdu.group8.ability.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.commonability.data.AbilityKey;

public class AbilityCatalog {

    private Map<AbilityKey, Ability> abilities = new ConcurrentHashMap<>();
    private Map<AbilityKey, AbilityData> abilityDataMap = new ConcurrentHashMap<>();

    private static AbilityCatalog instance = null;

    public static AbilityCatalog getInstance() {
        if (instance == null) {
            instance = new AbilityCatalog();
        }
        return instance;
    }

    private AbilityCatalog() {

    }

    private Collection<AbilityKey> getCatalogKeys() {
        return abilities.keySet();
    }
    
    private AbilityData getAbilityData(AbilityKey key) {
        return abilityDataMap.get(key);
    }

    public List<AbilityData> getAllAbilityData() {
        List<AbilityData> r = new ArrayList<>();
        for (AbilityKey abilityKey : getCatalogKeys()) {
            r.add(new AbilityData(getAbilityData(abilityKey), abilityKey));
        }
        return r;
    }

    public <A extends Ability> List<AbilityData> getAllAbilityDataForType(Class<A> type) {
        List<AbilityData> r = new ArrayList<>();

        for (Map.Entry<AbilityKey, Ability> entry : abilities.entrySet()) {
            if (type.isInstance(entry.getValue())) {
                r.add(new AbilityData(getAbilityData(entry.getKey()), entry.getKey()));
            }
        }
        return r;
    }

    public Ability getAbilityForType(AbilityData type) {
        return abilities.get(type.getKey());
    }

    public void addAbility(AbilityData abilityData, Ability ability) {
        abilities.put(abilityData.getKey(), ability);
        abilityDataMap.put(abilityData.getKey(), abilityData);
    }

}
