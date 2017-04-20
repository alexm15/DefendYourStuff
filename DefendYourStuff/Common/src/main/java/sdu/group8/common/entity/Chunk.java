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
    private float positionOffset;
    protected final int TILE_SIZE = 100;

    public Chunk(float positionOffset) {
        this.positionOffset = positionOffset;
    }

    public Tile[][] getTileMatrix() {
        return this.bgMatrix;
    }

    public void setTileMatrix(Tile[][] bgMatrix) {
        this.bgMatrix = bgMatrix;
        this.dimension = new Dimension(bgMatrix.length * TILE_SIZE, bgMatrix[0].length * TILE_SIZE, 0);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public float getPositionOffset() {
        return positionOffset;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public void setPositionOffset(float positionOffset) {
        this.positionOffset = positionOffset;
    }

    public abstract void createEntities(World world);

    public abstract String getFirstBackgroundImageURL();

    public abstract String getSecondBackgroundImageURL();

}
