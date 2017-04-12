/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.map.chunks.Chunk_Base;

import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class MapControllerIT {
    
    public MapControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private GameData gameData;
    private World world;
    private MapController instance;
    private Chunk chunkBase;

    @Before
    public void setUp() {
        gameData = new GameData();
        world = new World();
        instance = new MapController();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
        instance.start(gameData, world);
        
        chunkBase = new Chunk_Base();
    }
    
    @After
    public void tearDown() {
        gameData = null;
        world = null;
        instance = null;
    }

    /**
     * Test of start method, of class MapController.
     */
    @Test
    public void testStart() {
        // TODO: Implement test
    }
    
    /**
     * Test Dimension of chunks
     */
    @Test
    public void testChunkDimension() {
        float expectedW = 12.0f;
        float expectedH = 5.0f;
        float resultW = chunkBase.getDimension().getWidth();
        float resultH = chunkBase.getDimension().getHeight();
        assertTrue(expectedH == resultH);
        assertTrue(expectedW == resultW);
    }
}
