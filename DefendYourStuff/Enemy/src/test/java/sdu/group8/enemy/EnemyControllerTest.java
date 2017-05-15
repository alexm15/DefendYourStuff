/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import sdu.group8.commonability.data.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;

/**
 *
 * @author Sebastian
 */
public class EnemyControllerTest {

    public EnemyControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class EnemyController.
     */
    @Test
    @Ignore
    public void testProcess() {
        System.out.println("process");
        GameData gameData = null;
        World world = null;
        EnemyController instance = new EnemyController();
        instance.process(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class EnemyController.
     */
    @Test
    @Ignore
    public void testStart() {
        System.out.println("start");
        GameData gameData = null;
        World world = null;
        EnemyController instance = new EnemyController();
        instance.start(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class EnemyController.
     */
    @Test
    @Ignore
    public void testStop() {
        System.out.println("stop");
        GameData gameData = null;
        World world = null;
        EnemyController instance = new EnemyController();
        instance.stop(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of deathProcess method, of class EnemyController.
//     */
//    @Test
//    @Ignore
//    public void testDeathProcess() {
//        System.out.println("deathProcess");
//        GameData gameData = new GameData();
//        World world = new World();
//        Dimension dim = new Dimension(1, 1, 0);
//        Position pos = new Position(0, 0);
//        Ability[] ab = new Ability[1];
//        System.out.println(gameData.getPlayerGold());
//        //world.addEntity(new MediumEnemy(0, 0, 10, "", dim, pos, CollisionType.CIRCLE, ab));  // Not correct method
//        EnemyController enemyCon = new EnemyController();
//        for (Entity e : world.getEntities()) {
//            if (e.getClass().equals(MediumEnemy.class)) {
//                MediumEnemy medEnemy = (MediumEnemy) e;
//                assertEquals(10, medEnemy.getCurrentHealth(), 0);
//                medEnemy.reduceHealth(1000);
//                assertEquals(0, medEnemy.getCurrentHealth(), 0);
//                enemyCon.deathProcess(gameData, world);
//                assertEquals(100, gameData.getPlayerGold());
//                System.out.println(gameData.getPlayerGold());
//            }
//        }
//    }

    /**
     * Test of createMediumEnemy method, of class EnemyController.
     */
    @Test
    @Ignore
    public void testCreateMediumEnemy() {
        System.out.println("createMediumEnemy");
        World world = null;
        GameData gameData = null;
        Position position = null;
        EnemyController instance = new EnemyController();
        instance.createMediumEnemy(world, gameData, position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllEnemies method, of class EnemyController.
     */
    @Test
    @Ignore
    public void testRemoveAllEnemies() {
        System.out.println("removeAllEnemies");
        World world = null;
        EnemyController instance = new EnemyController();
        instance.removeAllEnemies(world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
