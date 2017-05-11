/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

/**
 *
 * @author Martin
 */
public class Dimension {
    private float width;
    private float height;
    private float radius;

    /**
     * Creates a dimension for an entity, must be same as sprite's size.
     * @param width must be >= 0
     * @param height must be >= 0
     * @param radius used for circle collision if needed.
     */
    public Dimension(float width, float height, float radius) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions width and height"
                    + " must be >= 0");
        }
    }

    /**
     * Creates a dimension based on an existing entity's dimension.
     * @param dimension the existing entity's dimenstion.
     */
    public Dimension(Dimension dimension) {
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
        this.radius = dimension.getRadius();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
    /**
     * Changes width and height of a dimension.
     * @param width new width
     * @param height new height
     */
    public void setDimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
