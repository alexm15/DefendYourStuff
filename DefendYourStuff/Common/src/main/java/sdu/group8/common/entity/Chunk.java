/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.entity.BlockTypes;

public abstract class Chunk {

    private int gridWidth;
    private BlockTypes[][] chunkMatrix;

    public Chunk(int gridWidth, BlockTypes[][] chunkMatrix) {
        this.gridWidth = gridWidth;
        this.chunkMatrix = chunkMatrix;
    }

    public BlockTypes[][] getChunkMatrix() {
        return chunkMatrix;
    }

    public int getGridWidth() {
        return gridWidth;
    }

}
