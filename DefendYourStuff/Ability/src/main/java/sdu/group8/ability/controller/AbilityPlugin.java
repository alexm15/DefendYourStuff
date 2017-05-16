
package sdu.group8.ability.controller;

import sdu.group8.ability.data.SlashData;
import sdu.group8.ability.data.FireballData;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.ability.spellbook.*;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityKey;

@ServiceProviders(value = {
    @ServiceProvider(service = IPreStartPluginService.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class AbilityPlugin implements IGamePluginService, IPreStartPluginService {

    private AbilityCatalog abilityDataCatalog;

    public AbilityPlugin() {
        abilityDataCatalog = AbilityCatalog.getInstance();
    }

    @Override
    public void preStart(GameData gameData) {
        abilityDataCatalog.addAbility(new FireballData(new AbilityKey()), new Fireball());
        abilityDataCatalog.addAbility(new SlashData(new AbilityKey()), new Fireball());
    }

    @Override
    public void start(GameData gameData, World world) {
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity ability : world.getEntities(Ability.class)) {
            world.removeEntity(ability);
        }
        
    }

}
