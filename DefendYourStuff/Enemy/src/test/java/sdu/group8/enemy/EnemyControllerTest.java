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
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;

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
    public void testStop() {
        System.out.println("stop");
        GameData gameData = null;
        World world = null;
        EnemyController instance = new EnemyController();
        instance.stop(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deathProcess method, of class EnemyController.
     */
    @Test
    public void testDeathProcess() {
        System.out.println("deathProcess");
        GameData gameData = null;
        World world = null;
        EnemyController instance = new EnemyController();
        instance.deathProcess(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMediumEnemy method, of class EnemyController.
     */
    @Test
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
    public void testRemoveAllEnemies() {
        System.out.println("removeAllEnemies");
        World world = null;
        EnemyController instance = new EnemyController();
        instance.removeAllEnemies(world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
