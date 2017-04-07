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

    public Dimension(float width, float height, float radius) {
        this.width = width;
        this.height = height;
        this.radius = radius;
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
    
    public void setDimension(float w, float h) {
        this.width = w;
        this.height = h;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
