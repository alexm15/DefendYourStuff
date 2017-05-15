package sdu.group8.test.buildings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sdu.group8.test.enemy.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openide.util.Lookup;
import sdu.group8.ability.controller.AbilityController;
import sdu.group8.ability.controller.AbilityPlugin;
import sdu.group8.buildingcontroller.BuildingControlSystem;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.Buildable;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyService;
import sdu.group8.enemy.BigMeleeEnemy;
import sdu.group8.enemy.EnemyController;
import sdu.group8.enemy.MediumEnemy;

/**
 *
 * @author joach
 */
public class BuildingTest {

    private GameData gameData;
    private World world;
    private Lookup lookup;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        lookup = Lookup.getDefault();
        gameData = new GameData();
        world = new World();
        BuildingControlSystem buildingController = new BuildingControlSystem();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
    }

    @After
    public void tearDown() throws Exception {
        lookup = null;
        gameData = null;
        world = null;
    }

    @Test
    public void testCreateRubbleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createRubbleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testCreateBlacksmithBuilding() {
        // Create methodbody first
//        Buildable buildingProvider = lookup.lookup(Buildable.class);
//
//        assertEquals(0, world.getEntities().size());
//
//        buildingProvider.createBlacksmithBuilding(world, new Position(0, 0));
//
//        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testCreateCastleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createCastleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testCreateDestroyedCastleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createDestroyedCastleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testFarmBuilding() {
        // Create methodbody first
//        Buildable buildingProvider = lookup.lookup(Buildable.class);
//
//        assertEquals(0, world.getEntities().size());
//
//        buildingProvider.createFarmBuilding(world, new Position(0, 0));
//
//        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testPortalBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createPortalBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }
    
    @Test
    public void testTowerBuilding() {
        // Create methodbody first
//        Buildable buildingProvider = lookup.lookup(Buildable.class);
//
//        assertEquals(0, world.getEntities().size());
//
//        buildingProvider.createTowerBuilding(world, new Position(0, 0));
//
//        assertEquals(1, world.getEntities().size());
    }
   
    @Test
    public void testWallBuilding() {
        // Create methodbody first
//        Buildable buildingProvider = lookup.lookup(Buildable.class);
//
//        assertEquals(0, world.getEntities().size());
//
//        buildingProvider.createWallBuilding(world, new Position(0, 0));
//
//        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testWellBuilding() {
        // Create methodbody first
//        Buildable buildingProvider = lookup.lookup(Buildable.class);
//
//        assertEquals(0, world.getEntities().size());
//
//        buildingProvider.createWellBuilding(world, new Position(0, 0));
//
//        assertEquals(1, world.getEntities().size());
    }
}
