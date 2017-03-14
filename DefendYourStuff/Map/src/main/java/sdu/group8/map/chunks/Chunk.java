/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.chunks;

import java.util.Arrays;

/**
 *
 * @author Alexander
 */
public class Chunk {

    int row = 7;
    int column = 7;
    BlockTypes[][] grid = new BlockTypes[row][column];

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
