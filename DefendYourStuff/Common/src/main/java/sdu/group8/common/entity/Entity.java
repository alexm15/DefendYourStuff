/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.collision.CollisionContainer;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.*;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;

/**
 *
 * @author Martin
 */
public abstract class Entity {

    private UUID ID;
    private Dimension dimension;
    private Position pos;
    private CollisionContainer collisionContainer;
    private AbilityContainer abilities;

    public Entity(Dimension dimension, Position pos, CollisionContainer collisionContainer, Ability... ab) {
        this.ID = UUID.randomUUID();
        this.dimension = dimension;
        this.pos = pos;
        this.collisionContainer = collisionContainer;
        this.abilities = new AbilityContainer(ab);
    }

    /**
     * Is used to finde out if the entity is on the ground OR under the ground.
     *
     * @param entity the entity that will be checked.
     * @return true if the enity is on the ground OR under the ground, else
     * false.
     */
    public boolean isEntityOnGround(Entity entity, GameData gameData) {
        if (entity.getPosition().getY() <= gameData.getGROUND_HEIGHT() + entity.getHeight() / 2) {
            return true;
        }
        return false;
    }

    /**
     * Sets the player to ground level.
     *
     * @param player
     */
    public void setEntityOnGround(Entity entity, GameData gameData) {
        entity.setPosition(entity.getPosition().getX(), (gameData.getGROUND_HEIGHT() + entity.getHeight() / 2));

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

    public void setDimension(float width, float height) {
        this.dimension.setDimension(width, width);
    }

    public void setDimension(Dimension dim) {
        this.dimension = dim;
    }

    public Position getPosition() {
        return pos;
    }

    public ArrayList<EntityType> getCollidableTypes() {
        return this.collisionContainer.getCollidableTypes();
    }

    public ArrayList<Ability> getAbilities() {
        return abilities.getAbilites();
    }

}
