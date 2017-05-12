/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.controller;

import sdu.group8.commonabilitydata.abilities.FireballData;
import sdu.group8.commonabilitydata.abilities.SlashData;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.ability.spellbook.*;

@ServiceProviders(value = {
    @ServiceProvider(service = IPreStartPluginService.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class AbilityPlugin implements IGamePluginService, IPreStartPluginService {

    private AbilityCatalog abilityCatalog;

    public AbilityPlugin() {
        abilityCatalog = AbilityCatalog.getInstance();
    }

    @Override
    public void preStart(GameData gameData) {
        abilityCatalog.addAbility(new FireballData(), new Fireball());
        //abilityCatalog.addAbility(new ArrowData(), new Arrow());
        abilityCatalog.addAbility(new SlashData(), new Slash());
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
