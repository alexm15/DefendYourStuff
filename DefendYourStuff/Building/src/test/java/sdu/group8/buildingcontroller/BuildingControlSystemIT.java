/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingcontroller;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import sdu.group8.buildingentities.Castle;
import sdu.group8.buildingentities.Rubble;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;
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
        System.out.println("createCastleBuilding");
        
        Position position = new Position(0, 0);
        BuildingControlSystem instance = new BuildingControlSystem();
        instance.createCastleBuilding(world, position);
        // TODO review the generated test code and remove the default call to fail.
        
        for (Entity castle : world.getEntities(Castle.class)) {
            assertNotNull(castle);
            assertEquals("Building/castle.png", castle.getImageURL());
            float xPos = 0;
            float yPos = 0;
            assertEquals(xPos, castle.getPosition().getX(), 0);
            assertEquals(yPos, castle.getPosition().getY(), 0);
        }
        
    }





    /**
     * Test of createRubbleBuilding method, of class BuildingControlSystem.
     */
    @Test
    public void testCreateRubbleBuilding() {
        System.out.println("createRubbleBuilding");
        Position position = new Position(0, 0);
        
        instance.createRubbleBuilding(world, position);
        // TODO review the generated test code and remove the default call to fail.
        for (Entity rubble : world.getEntities(Rubble.class)) {
            assertNotNull(rubble);
            assertEquals("Building/rubble.png", rubble.getImageURL());
            float xPos = 0;
            float yPos = 0;
            assertEquals(xPos, rubble.getPosition().getX(), 0);
            assertEquals(yPos, rubble.getPosition().getY(), 0);
        }
    }

    /**
     * Test of createDestroyedCastleBuilding method, of class BuildingControlSystem.
     */
    
    @Test
    public void testCreateDestroyedCastleBuilding() {
        System.out.println("createDestroyedCastleBuilding");
        Position position = null;
        
        instance.createCastleBuilding(world, position);
        for (Entity entity : world.getEntities(Castle.class)) {
            Building castle = (Building) entity;
            //Building destroyed
            castle.reduceHealth(10000);

        }
        
        instance.process(new GameData(), world);
        for (Entity entity : world.getEntities(Castle.class)) {
            //Game Over image URL for destroyed building
            //Ensures call to createDestroyedBuilding
            assertEquals("Game Over", entity.getImageURL());
        }
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
