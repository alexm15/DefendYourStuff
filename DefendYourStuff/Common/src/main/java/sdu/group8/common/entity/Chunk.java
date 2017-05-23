
package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Image;
import sdu.group8.common.data.World;

public abstract class Chunk {

    private Dimension dimension;
    private Tile[][] bgMatrix;
    private float positionOffset;
    private Image firstBackgroundImage;
    private Image secondBackgroundImage;
    private float backgroundScrollScale = 50;
    protected final int TILE_SIZE = 100;

    public Chunk(Image firstBackgroundImage, Image secondBackgroundImage, float positionOffset) {
        this.positionOffset = positionOffset;
        this.firstBackgroundImage = firstBackgroundImage;
        this.secondBackgroundImage = secondBackgroundImage;
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

    public Image getFirstBackgroundImage() {
        return this.firstBackgroundImage;
    }

    public Image getSecondBackgroundImage() {
        return this.secondBackgroundImage;
    }
    
    public float getBackgroundScrollScale() {
        return this.backgroundScrollScale;
    }
}
