/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingcontroller;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.buildingentities.*;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;
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
        Building castle = new Castle(position);
        world.addEntity(castle);
    }

    @Override
    public void createTowerBuilding(World world, Position position) {
        //Building tower = new Tower(position)
        //TODO: Simplify constructor like the one used for constructing castle
    }

    @Override
    public void createWallBuilding(World world, Position position) {
        //TODO: Simplify constructor like the one used for constructing castle
    }

    @Override
    public void createFarmBuilding(World world, Position position) {
        //TODO: Simplify constructor like the one used for constructing castle
    }

    @Override
    public void createBlacksmithBuilding(World world, Position position) {
        //TODO: Simplify constructor like the one used for constructing castle
    }

    @Override
    public void createWellBuilding(World world, Position position) {
        //TODO: Simplify constructor like the one used for constructing castle
    }

    @Override
    public void createPortalBuilding(World world, Position position) {
        Building portal = new Portal(position);
        world.addEntity(portal);
    }

    @Override
    public void createRubbleBuilding(World world, Position position) {
        Building rubble = new Rubble(position);
        world.addEntity(rubble);
    }

    private void castleProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Castle.class)) {
            Building castle = (Building) entity;
            if (castle.getHealth() <= 0) {
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
        Building destroyedCastle = new Castle(position);
        //destroyedCastle.setImageURL("Game Over");
        world.addEntity(destroyedCastle);
    }

    private void wallProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Wall.class)) {
            Building wall = (Building) entity;
            if (wall.getHealth() == 0) {
                createRubbleBuilding(world, wall.getPosition());
                world.removeEntity(wall);
            }
        }
    }

    private void towerProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Tower.class)) {
            Building tower = (Building) entity;
            if (tower.getHealth() == 0) {
                createRubbleBuilding(world, tower.getPosition());
                world.removeEntity(tower);
            }
        }
    }

    private void portalProcess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Portal.class)) {
            Building portal = (Building) entity;
            if (portal.getHealth() == 0) {
                createRubbleBuilding(world, portal.getPosition());
                world.removeEntity(portal);
            }
        }
    }

    /**
     * Idea for design with as replacement of interface, so instead the
     * interface buildable only contains this method and then the subclasses of
     * Building is moved to CommonBuilding component.
     */
//    public Building createBuilding(Class<E> building, Position position) {
//        if (building.equals(Castle.class)) {
//            Building castle = new Castle(position);
//            return castle;
//        }
//        return null;
//    }
}
