/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.data.CollisionType;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.*;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public abstract class Entity {

    private UUID ID;
    private Dimension dimension;
    private Position pos;
    private CollisionContainer collisionContainer;
    private boolean isHit = false;

    public Entity(Dimension dimension, Position pos, CollisionContainer collisionContainer) {
        this.ID = UUID.randomUUID();
        this.dimension = dimension;
        this.pos = pos;
        this.collisionContainer = collisionContainer;
    }

    public UUID getID() {
        return ID;
    }

    public List<EntityType> getTypesToIgnore() {
        return collisionContainer.getTypesToIgnore();
    }

    public void addTypeToIgnore(EntityType et) {
        collisionContainer.addTypeToIgnore(et);
    }

    public EntityType getEntityType() {
        return collisionContainer.getEntityType();
    }

    public void setEntityType(EntityType entityType) {
        this.collisionContainer.setEntityType(entityType);
    }

    public CollisionType getCollisionType() {
        return this.collisionContainer.getCollisionType();
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionContainer.setCollisionType(collisionType);
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
    
    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Position getPosition() {
        return pos;
    }

    public ArrayList<EntityType> getCollidableTypes() {
        return this.collisionContainer.getCollidableTypes();
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

}
