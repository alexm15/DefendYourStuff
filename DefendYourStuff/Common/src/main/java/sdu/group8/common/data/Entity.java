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
    private CollisionType collisionType;
    private float width;
    private float height;
    private Position pos;

    public Entity(CollisionType collisionType, float width, float height, float x, float y) {
        this.ID = UUID.randomUUID();
        this.collisionType = collisionType;
        this.width = width;
        this.height = height;
        this.pos = new Position(x, y);
    }

    public UUID getID() {
        return ID;
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
