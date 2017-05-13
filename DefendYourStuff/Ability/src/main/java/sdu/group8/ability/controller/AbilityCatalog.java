package sdu.group8.ability.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;

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

    private Collection<AbilityData> getCatalogKeys() {
        return abilities.keySet();
    }

    public List<AbilityData> getAllAbilityData() {
        List<AbilityData> r = new ArrayList<>();
        for (AbilityData abilityData : getCatalogKeys()) {
            r.add(abilityData);
        }
        return r;
    }

    public <A extends Ability> List<AbilityData> getAbilityDataForType(Class<A> type) {

        List<AbilityData> r = new ArrayList<>();

        for (Map.Entry<AbilityData, Ability> entry : abilities.entrySet()) {
            if (type.isInstance(entry.getValue())) {
                r.add(entry.getKey());
            }
        }
        return r;
    }

    public Ability getAbilityForType(AbilityData type) {
        return abilities.get(type);
    }

    public void addAbility(AbilityData abilityData, Ability ability) {
        abilities.put(abilityData, ability);
    }

}
