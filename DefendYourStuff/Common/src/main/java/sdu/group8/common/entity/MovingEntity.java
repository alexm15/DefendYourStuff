package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;

/**
 * Represents all entities in the game with movable properties.
 * @author Group 8
 */
public abstract class MovingEntity extends Entity {

    protected float dx;
    protected float dy;
    protected float moveSpeed;
    protected float weight;
    
    /**
     * Creates a moving entity in the game. Is usually a subtype
     * @param moveSpeed the entity's moveSpeed
     * @param weight the entity's weight
     * @param imageURL the entity's image
     * @param dimension the entity's size
     * @param direction the entity's direction
     * @param pos the entity's position
     * @param collisionType the collisionType of the entity
     */
    public MovingEntity(float moveSpeed, float weight, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType) {
        super(imageURL, dimension, direction, pos, collisionType);
        if (moveSpeed < 0) {
            throw new IllegalArgumentException("moveSpeed cannot be negative");
        }
        this.moveSpeed = moveSpeed;
        this.weight = weight;
        this.dx = moveSpeed;
        this.dy = 0;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    /**
     * Changes the vector direction of the entity for how fast the 
     * entity is moving in a given direction.  
     * @param dx the new vector for x position
     * @param dy the new vector for y position
     */
    public void setVectorDirection(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Changes the moveSpeed, cannot be negative.
     * @param moveSpeed the new moveSpeed
     */
    public void setMoveSpeed(float moveSpeed) {
        if (moveSpeed < 0) {
            throw new IllegalArgumentException("moveSpeed cannot be negative");
        }
        this.moveSpeed = moveSpeed;        
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    
}
