/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;

/**
 *
 * @author Martin
 */
public abstract class MovingEntity extends Entity {

    protected float dx;
    protected float dy;
    protected float moveSpeed;
    protected float weight;
    
    public MovingEntity(float moveSpeed, float weight, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType) {
        super(imageURL, dimension, direction, pos, collisionType);
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

    public void setDirection(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    
}
