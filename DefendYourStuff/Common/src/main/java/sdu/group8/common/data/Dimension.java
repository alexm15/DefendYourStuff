/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import sdu.group8.common.enums.CollisionType;

/**
 *
 * @author Martin
 */
public class Dimension {
    private CollisionType collisionType;
    private float width;
    private float height;

    public Dimension(CollisionType collisionType, float width, float height) {
        this.collisionType = collisionType;
        this.width = width;
        this.height = height;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
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
    
}
