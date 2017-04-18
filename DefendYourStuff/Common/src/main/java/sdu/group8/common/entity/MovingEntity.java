/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;

/**
 *
 * @author Martin
 */
public abstract class MovingEntity extends Entity {

    private float dx;
    private float dy;
    private float moveSpeed;
    private float weight;
    
    public MovingEntity(float moveSpeed, float weight, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, Ability... ab) {
        super(imageURL, dimension, pos, collisionType, ab);
        this.moveSpeed = moveSpeed;
        this.weight = weight;
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
