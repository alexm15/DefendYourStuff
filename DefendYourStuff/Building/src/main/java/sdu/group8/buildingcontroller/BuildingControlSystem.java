/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingcontroller;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.buildingentities.Castle;
import sdu.group8.buildingentities.Rubble;
import sdu.group8.buildingentities.Tower;
import sdu.group8.buildingentities.Wall;
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
import sdu.group8.commonbuilding.services.IDefBuilding;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = Buildable.class)}
)
public class BuildingControlSystem
        implements IGamePluginService, IGameProcessingService, Buildable {

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

    @Override
    public void process(GameData gameData, World world) {
       
        castleProcess(gameData, world);
        wallProcess(gameData, world);
        towerProcess(gameData, world);
        portalProcess(gameData, world);
        shopProcess(gameData, world);

    }

    @Override
    public void createCastleBuilding(World world, Position position) {
        Dimension castleDimension = new Dimension(200, 100, 0);
        float health = 100;
        int upgradeLvl = 1;
        Ability[] abilities = new Ability[1];

        Building castle
                = new Castle("Building/castle.png", castleDimension, position,
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
        Dimension rubbleDimension = new Dimension(35, 20, 0);
        //TODO: Determine stats for rubble building
        float health = 100;
        int upgradeLvl = 1;
        Ability[] abilities = new Ability[0];

        Building rubble
                = new Rubble("Building/rubble.png", rubbleDimension, position,
                        CollisionType.BOX, BuildingType.DEFENCE,
                        true, upgradeLvl, health, abilities);

        world.addEntity(rubble);
    }

    private void castleProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Castle.class)) {
            Castle castle = (Castle) entity;
            if (castle.getHealth() == 0) {
                //TODO: set gamestate to game over
                System.out.println("Game Over");
                createDestroyedCastleBuilding(world, castle.getPosition());
                world.removeEntity(castle);
            }
            //TODO: Add castle operations
        }

    }

    private void shopProcess(GameData gameData, World world) {
    }

    @Override
    public void createDestroyedCastleBuilding(World world, Position position) {
        Dimension castleDimension = new Dimension(200, 100, 0);
        float health = 1000000;
        int upgradeLvl = 1;
        Ability[] abilities = new Ability[1];

        Building destroyedCastle
                = new Castle("Building/DestroyedCastle.png", castleDimension, position,
                        CollisionType.BOX, BuildingType.DEFENCE,
                        true, upgradeLvl, health, abilities);

        world.addEntity(destroyedCastle);
    }

    private void wallProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Wall.class)) {
            Wall wall = (Wall) entity;
            if (wall.getHealth() == 0) {
                createRubbleBuilding(world, wall.getPosition());
                world.removeEntity(wall);
            }
        }
    }

    private void towerProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Tower.class)) {
            Tower tower = (Tower) entity;
            if (tower.getHealth() == 0) {
                createRubbleBuilding(world, tower.getPosition());
                world.removeEntity(tower);
            }
        }
    }

    private void portalProcess(GameData gameData, World world) {
//        for (Entity entity : world.getEntities(Portal.class)) {
//            Portal portal = (Portal) entity;
//        }
    }

}
