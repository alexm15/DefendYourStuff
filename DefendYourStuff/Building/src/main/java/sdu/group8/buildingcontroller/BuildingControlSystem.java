/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.buildingcontroller;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.buildingentities.Castle;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.commonbuilding.services.Buildable;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class),
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = Buildable.class)}
)
public class BuildingControlSystem implements IGamePluginService, IGameProcessingService, Buildable
{

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

    @Override
    public void process(GameData gameData, World world) {
        
        
    }

    @Override
    public void createCastleBuilding(World world, Position position) {
        Dimension castleDimension = new Dimension(200, 100, 0);
        float health = 100;
        int upgradeLvl = 1;
        Ability[] abilities = new Ability[1]; 
        
        Building castle = 
                new Castle("Building/castle.png", castleDimension, position, 
                        CollisionType.BOX, BuildingType.DEFENCE, 
                        true, upgradeLvl, health, abilities);
        
        world.addEntity(castle);
    }

    @Override
    public void createTowerBuilding(World world, Position position) {
        
    }

    @Override
    public void createWallBuilding(World world, Position position) {
    }

    @Override
    public void createFarmBuilding(World world, Position position) {
    }

    @Override
    public void createBlacksmithBuilding(World world, Position position) {
    }

    @Override
    public void createWellBuilding(World world, Position position) {
    }

    @Override
    public void createRubbleBuilding(World world, Position position) {
    }

}
