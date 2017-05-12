
package sdu.group8.ability.controller;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonabilitydata.abilities.*;
import sdu.group8.ability.spellbook.*;
import sdu.group8.ability.types.*;

@ServiceProviders(value = {
    @ServiceProvider(service = IPreStartPluginService.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class AbilityPlugin implements IGamePluginService, IPreStartPluginService {

    private AbilityDataCatalog abilityDataCatalog;

    public AbilityPlugin() {
        abilityDataCatalog = AbilityDataCatalog.getInstance();
    }

    @Override
    public void preStart(GameData gameData) {
        abilityDataCatalog.addAbility(new FireballData(), AbilityType.RANGED);
        abilityDataCatalog.addAbility(new SlashData(), AbilityType.MELEE);
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        
    }

}