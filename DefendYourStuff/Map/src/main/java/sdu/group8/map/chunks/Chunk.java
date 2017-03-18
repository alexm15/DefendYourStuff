/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.chunks;

import java.util.Arrays;
import static sdu.group8.map.chunks.BlockTypes.*;

/**
 *
 * @author Alexander
 */
public class Chunk {
    
    

    int row = 7;
    int column = 7;
    /**
     * CastleBlock
     */
    BlockTypes[][] cB = new BlockTypes[][] {
        {CASTLE, AIR, AIR},
        {AIR,    AIR, AIR},      
    };
    /**
     * WellBlock
     */
    BlockTypes[][] wB = new BlockTypes[][] {
        {WELL},    
    };
    /**
     * WallTowerBlock
     */
    BlockTypes[][] wtB = new BlockTypes[][] {
        {WALLTOWER},
        {AIR},      
    };
    /**
     * FarmBlock
     */
    BlockTypes[][] fB = new BlockTypes[][] {
        {FARM, AIR},
        {AIR,    AIR},      
    };
    /**
     * BlacksmithBlock
     */
    BlockTypes[][] bB = new BlockTypes[][] {
        {FARM, AIR},
        {AIR,    AIR},      
    };
    
    public Chunk() {
        
    }
    
    public void loadBuilding() {
        for (int i = 0; i < castleChunck.length; i++) {
            for (int j = 0; j < castleChunck[i].length; j++) {
                if (castleChunck[i][j].equals(CASTLE)) {
                    //castleChunck[i][j]= do something
                }
                if(castleChunck[i][j].equals(BLACKSMITH)){
                    //load blacksmith
                }
                // add more if statements
            }     
        }
    }
    
    BlockTypes[][] grid = new BlockTypes[row][column];
    BlockTypes[][] castleChunck = new BlockTypes[][] {
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            CASTLE,         AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,         EARTH,          EARTH,          EARTH,          EARTH,          EARTH,          EARTH},
        
    };
    
    public BlockTypes[][] getCastleChunk() {
        return castleChunck;
    }
    
    BlockTypes[][] rightBaseChunck = new BlockTypes[][] {
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {FARM,          AIR,            AIR,            AIR,            AIR,            WALLTOWER,      AIR},
        {AIR,           AIR,            AIR,            WELL,           AIR,            AIR,            AIR},
        {EARTH,         EARTH,          EARTH,          EARTH,          EARTH,          EARTH,          EARTH},
        
    };
    BlockTypes[][] leftBaseChunck = new BlockTypes[][] {
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           AIR,            AIR,            AIR,            AIR,            AIR,            AIR},
        {AIR,           WALLTOWER,      AIR,            AIR,            AIR,            BLACKSMITH,     AIR},
        {AIR,           AIR,            AIR,            WELL,           AIR,            AIR,            AIR},
        {EARTH,         EARTH,          EARTH,          EARTH,          EARTH,          EARTH,          EARTH},
        
    };
    
    

    public void createCastleChunk() {

        insertCastleBlock();
        insertEarthBlocks();
        fillAirBlocks();
    }

    public void insertEarthBlocks() {
        for (int i = 0; i < column; i++) {
            grid[row - 1][i] = BlockTypes.EARTH;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    

    public void insertCastleBlock() {
        for (int i = 2; i < 5; i++) {
            grid[row - 3][i] = BlockTypes.CASTLE;
        }
        for (int i = 2; i < 5; i++) {
            grid[row - 2][i] = BlockTypes.CASTLE;
        }
    }

    public void print2DArray() {
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();

        }
    }

    @Override
    public String toString() {
        return null;
    }

    private void fillAirBlocks() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = BlockTypes.AIR;
                }
            }
        }
    }

}
