/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.chunks;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class ChunkTest {
    
    private Chunk theChunk;
    
    public ChunkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        theChunk = new Chunk();
    }
    
    @After
    public void tearDown() {
        theChunk = null;
    }

   

    /**
     * Test of print2DArray method, of class Chunk.
     */
    @Test
    public void testPrint2DArray() {
        System.out.println("print2DArray");
        
        theChunk.createCastleChunk();
        theChunk.print2DArray();
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testMakeCastleChunk() {
        System.out.println("print2DArray");
        
        theChunk.createCastleChunk();
        theChunk.print2DArray();
        
        checkThatEarthIsButtomOfArray();
        checkCastleInMiddle();
        checkThatAllElseIsAir();
    }

    private void checkThatEarthIsButtomOfArray() {
        for (int i = 0; i < theChunk.column; i++) {
            assertEquals(BlockTypes.EARTH, theChunk.grid[theChunk.row-1][i]);
        }
        
        // TODO review the generated test code and remove the default call to fail.
    }
    

    private void checkCastleInMiddle() {
        for (int i = 2; i < 5; i++) {
            assertEquals(BlockTypes.CASTLE, theChunk.grid[theChunk.row-3][i]);
        }
        for (int i = 2; i < 5; i++) {
            assertEquals(BlockTypes.CASTLE, theChunk.grid[theChunk.row-2][i]);
        }
    }

    private void checkThatAllElseIsAir() {
        for (int i = 0; i < theChunk.row; i++) {
            for (int j = 0; j < theChunk.column; j++) {
                if ((theChunk.grid[i][j].equals(BlockTypes.CASTLE)) ||
                        (theChunk.grid[i][j].equals(BlockTypes.EARTH))) {
                    assertTrue(theChunk.grid[i][j] == BlockTypes.AIR);
                }
                
            }
            
        }
    }
    
    
    
    
    
}
