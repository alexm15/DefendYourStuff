
package sdu.group8.ability.controller;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonabilitydata.abilities.*;
import sdu.group8.ability.spellbook.*;

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
        abilityDataCatalog.addAbility(new FireballData(), new Fireball());
        abilityDataCatalog.addAbility(new SlashData(), new Fireball());
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        
    }

}