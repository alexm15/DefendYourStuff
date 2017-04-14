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
import static org.junit.Assert.*;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;
import sdu.group8.map.chunks.CastleChunk;

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

    @Before
    public void setUp() {
        gameData = new GameData();
        world = new World();
        instance = new MapController();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
        instance.start(gameData, world);
        
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
        System.out.println("start");
        
        // TODO review the generated test code and remove the default call to fail.
        int firstIndexValueOfWindowsXMiddle = gameData.getWindowsxMiddle().get(0);
        assertEquals(100, firstIndexValueOfWindowsXMiddle);
        
        int firstIndexValueOfWindowsXLeft = gameData.getWindowsxLeft().get(0);
        assertEquals(-100, firstIndexValueOfWindowsXLeft);
        
        int firstIndexValueOfWindowsXRight = gameData.getWindowsxRight().get(0);
        assertEquals(900, firstIndexValueOfWindowsXRight);
    }

    

    /**
     * Test of stop method, of class MapController.
     */
    
    public void testStop() {
        System.out.println("stop");
        GameData gameData = null;
        World world = null;
        MapController instance = new MapController();
        instance.stop(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class MapController.
     */
    
    public void testProcess() {
        System.out.println("process");
        GameData gameData = null;
        World world = null;
        MapController instance = new MapController();
        instance.process(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addChunkRightGameList method, of class MapController.
     */
    @Test
    public void testAddChunkRightGameList() {
        System.out.println("addChunkRightGameList");
        Chunk chunkToAdd = new CastleChunk(ChunkTypes.CASTLE_CHUNK);
        
        instance.addChunkRightGameList(gameData, chunkToAdd);
        int expectedSizeOfRightChunkList = 2;
        int expectedSizeOfRightWindowsXList = 16;
        assertEquals(expectedSizeOfRightChunkList, gameData.getChunksRight().size());
        assertEquals(expectedSizeOfRightWindowsXList, gameData.getWindowsxRight().size());
    }

    /**
     * Test of addChunkToLeftGameList method, of class MapController.
     */
    @Test
    public void testAddChunkToLeftGameList() {
        System.out.println("addChunkToLeftGameList");
        Chunk chunkToAdd = new CastleChunk(ChunkTypes.CASTLE_CHUNK);
        
        instance.addChunkToLeftGameList(gameData, chunkToAdd);
        int expectedSizeOfLeftChunkList = 2;
        int expectedSizeOfLeftWindowsXList = 16;
        assertEquals(expectedSizeOfLeftChunkList, gameData.getChunksLeft().size());
        assertEquals(expectedSizeOfLeftWindowsXList, gameData.getWindowsxLeft().size());
    }
    
}
