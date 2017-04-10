/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.entity.BlockTypes;

public abstract class Chunk {

    private int gridWidth;
    private BlockTypes[][] bgMatrix;

    public Chunk() {
    }

    public BlockTypes[][] getBgMatrix() {
        return this.bgMatrix;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setBgMatrix(BlockTypes[][] bgMatrix) {
        this.bgMatrix = bgMatrix;
    }

    public int getGridWidth() {
        return gridWidth;
    }

}
