/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import sdu.group8.buildingentities.Castle;
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
        System.out.println("createCastleBuilding");

        Position position = new Position(0, 0);
        BuildingControlSystem instance = new BuildingControlSystem();
        instance.createCastleBuilding(world, position);
        // TODO review the generated test code and remove the default call to fail.

        for (Entity castle : world.getEntities(Castle.class)) {
            assertNotNull(castle);
            assertEquals("Building/castle.png", castle.getImage().getImageURL());
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
            assertEquals("Building/rubble.png", rubble.getImage().getImageURL());
            float xPos = 0;
            float yPos = 0;
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
            assertEquals("Game Over", entity.getImage().getImageURL());
        }

    }

    @Test
    public void testReduceHealthOfBuilding() {

        instance.createCastleBuilding(world, new Position(0, 0));

        for (Entity entity : world.getEntities(Castle.class)) {
            Building castle = (Building) entity;
            float tempHealth = castle.getHealth();
            castle.reduceHealth(50);
            assertEquals(tempHealth - 50, castle.getHealth(), 0);
        }
    }

//    @Test
//    @Ignore
//    public void testDynamicCreationOfBuilding() {
//        //Castle castle = new Castle(imageURL, dimension, pos, CollisionType.CIRLCE, BuildingType.DEFENCE, true, 0, 0, ab)
//        //Building createdCastle = instance.createBuilding(CASTLE, new Position(0, 0));
//        
//        String expectedCastleURL = "Building/castle.png";
//        assertEquals(expectedCastleURL, createdCastle.getImageURL());
//        
//        float expectedHealth = 100;
//        assertEquals(expectedHealth, createdCastle.getHealth(), 0);
//        
//        Dimension expectedDimension = new Dimension(200, 100, 0);
//        float expectedHeight = expectedDimension.getHeight();
//        float expectedWidth = expectedDimension.getWidth();
//        assertEquals(expectedHeight, createdCastle.getDimension().getHeight(), 0);
//        assertEquals(expectedWidth, createdCastle.getDimension().getWidth(), 0);
//        //ab
//        assertEquals(CollisionType.BOX, createdCastle.getCollisionType());
//        
//        assertEquals(BuildingType.DEFENCE, createdCastle.getBuildingType());
//        
//        assertEquals(true, createdCastle.isAttackable());
//        
//        assertEquals(0, createdCastle.getUpgradeLevel());
//        
//        //List<Ability> abilities = new ArrayList<>();
//        
////        abilities.add(new Ability(200, 200, new DamageRange(0, 0), "someAbility", 
////                new Dimension(0, 9, 0), new Position(0, 0), CollisionType.CIRLCE, 
////                new EffectContainer(new Effect()), ))
////        assertEquals(abilities.size(), createdCastle.getAbilities().size());
//    }
}
