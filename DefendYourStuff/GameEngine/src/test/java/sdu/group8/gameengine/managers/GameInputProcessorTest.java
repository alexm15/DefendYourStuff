/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.gameengine.managers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.group8.common.data.GameData;
import sdu.group8.gameengine.managers.GameInputProcessor;

/**
 *
 * @author Martin
 */
public class GameInputProcessorTest {

    private final GameData gameData;
    private GameInputProcessor inputProcessor;

    public GameInputProcessorTest() {
        gameData = new GameData();
        inputProcessor = new GameInputProcessor(gameData);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gameData.getKeys().update();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of mouseMoved method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testMouseMoved() {
        System.out.println("mouseMoved");
        //TODO: check for mouse movement
    }

    /**
     * Test of touchDown method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testTouchDown() {
        System.out.println("touchDown");
        //TODO: check for mouse click down
    }

    /**
     * Test of touchUp method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testTouchUp() {
        System.out.println("touchUp");
        //TODO: check for mouse click up

    }

    /**
     * Test of keyDown method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testKeyDown() {
        System.out.println("keyDown");
        int k = 19;
        boolean expResult = inputProcessor.keyDown(k);
        boolean result = gameData.getKeys().isKeyDown(k);
        System.out.println(expResult + " " + result);
        assertEquals(expResult, result);

    }

    /**
     * Test of keyUp method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testKeyUp() {
        System.out.println("keyUp");
        int k = 19;
        boolean expResult1 = inputProcessor.keyDown(k);
        boolean result1 = gameData.getKeys().isKeyDown(k);
        boolean expResult2 = inputProcessor.keyUp(k);
        boolean result2 = gameData.getKeys().isKeyDown(k);
        assertEquals(expResult1, result1);
        assertNotEquals(expResult2, result2);
    }

    /**
     * Test of keyUp method, of class GameInputProcessor.
     */
    @org.junit.Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        int k = 19;
        boolean expResult1 = false;
        boolean result1 = gameData.getKeys().isKeyPressed(k);
        boolean expResult2 = inputProcessor.keyDown(k);
        boolean result2 = gameData.getKeys().isKeyPressed(k);
        gameData.getKeys().update();
        inputProcessor.keyDown(k);
        boolean result3 = gameData.getKeys().isKeyPressed(k);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(false, result3);
    }
}
