/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.UUID;
import sdu.group8.common.enums.CollisionType;

/**
 *
 * @author Martin
 */
public abstract class Entity {
    private UUID ID;
    private Dimension dimension;
    private Position pos;

    public Entity(Dimension dimension, Position pos) {
        this.ID = UUID.randomUUID();
        this.dimension = dimension;
        this.pos = pos;
    }

    public UUID getID() {
        return ID;
    }
    
    public CollisionType getCollisionType() {
        return this.dimension.getCollisionType();
    }

    public void setCollisionType(CollisionType collisionType) {
        this.dimension.setCollisionType(collisionType);
    }

    public float getWidth() {
        return this.dimension.getWidth();
    }

    public void setWidth(float width) {
        this.dimension.setWidth(width);
    }

    public float getHeight() {
        return this.dimension.getHeight();
    }

    public void setHeight(float height) {
        this.dimension.setHeight(height);
    }

    public float getX() {
        return pos.getX();
    }

    public float getY() {
        return pos.getY();
    }

    public void setPosition(float x, float y) {
        this.pos.setPosition(x, y);
    }
    
    
}
