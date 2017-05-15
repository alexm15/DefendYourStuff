/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingcontroller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.group8.buildingentities.Castle;
import sdu.group8.buildingentities.DestroyedCastle;
import sdu.group8.buildingentities.Rubble;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.Effect;
import sdu.group8.commonability.data.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;

/**
 *
 * @author Alexander
 */
public class BuildingControlSystemIT {

    private World world;
    private BuildingControlSystem instance;

    public BuildingControlSystemIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        world = new World();
        instance = new BuildingControlSystem();
    }

    @After
    public void tearDown() {
        world = null;
        instance = null;
    }

    /**
     * Test of createCastleBuilding method, of class BuildingControlSystem.
     */
    @Test
    public void testCreateCastleBuilding() {
        Position position = new Position(0, 0);
        instance.createCastleBuilding(world, position);

        for (Entity castle : world.getEntities(Building.class)) {
            assertNotNull(castle);
            assertEquals("Building/castle.png", castle.getImage().getImageURL());
            float xPos = 0;
            float yPos = 0 + castle.getHeight()/2;
            assertEquals(xPos, castle.getPosition().getX(), 0);
            assertEquals(yPos, castle.getPosition().getY(), 0);
        }
    }

    /**
     * Test of createRubbleBuilding method, of class BuildingControlSystem.
     */
    @Test
    public void testCreateRubbleBuilding() {
        Position position = new Position(0, 0);
        instance.createRubbleBuilding(world, position);

        for (Entity rubble : world.getEntities(Building.class)) {
            assertNotNull(rubble);
            assertEquals("Building/rubble.png", rubble.getImage().getImageURL());
            float xPos = 0;
            float yPos = 0 + rubble.getHeight()/2;
            assertEquals(xPos, rubble.getPosition().getX(), 0);
            assertEquals(yPos, rubble.getPosition().getY(), 0);
        }
    }

    /**
     * Test of createDestroyedCastleBuilding method, of class
     * BuildingControlSystem.
     */
    @Test
    public void testCreateDestroyedCastleBuilding() {
        Position position = new Position(0, 0);

        instance.createCastleBuilding(world, position);
        for (Entity entity : world.getEntities(Building.class)) {
            Building castle = (Building) entity;
            //Building destroyed
            castle.reduceHealth(10000);
        }
        
        instance.process(new GameData(), world);
        for (Entity entity : world.getEntities(Building.class)) {
            //DestroyedCastle added
            assertEquals(entity.getClass(), DestroyedCastle.class);
        }
    }

    @Test
    public void testReduceHealthOfBuilding() {

        instance.createCastleBuilding(world, new Position(0, 0));

        for (Entity entity : world.getEntities(Building.class)) {
            Building castle = (Building) entity;
            float tempHealth = castle.getHealth();
            castle.reduceHealth(50);
            assertEquals(tempHealth - 50, castle.getHealth(), 0);
        }
    }
}
