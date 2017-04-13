/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.World;

public abstract class Chunk {

    private Dimension dimension;
    private Tile[][] bgMatrix;
    private int tileOffsetX;

    public Chunk(int tileOffsetX) {
        this.tileOffsetX = tileOffsetX;
    }

    public Tile[][] getTileMatrix() {
        return this.bgMatrix;
    }

    public void setTileMatrix(Tile[][] bgMatrix) {
        this.bgMatrix = bgMatrix;
        this.dimension = new Dimension(bgMatrix.length, bgMatrix[0].length, 0);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getTileOffsetX() {
        return tileOffsetX;
    }
    
    public abstract void createEntities(World world, int tileOffsetX);
    
    public abstract String getBackgroundImageURL();

}
