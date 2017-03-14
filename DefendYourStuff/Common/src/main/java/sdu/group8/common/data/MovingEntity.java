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
public abstract class MovingEntity extends Entity{
    private float dx;
    private float dy;

    public MovingEntity(Dimension dimension, Position pos) {
        super(dimension, pos);
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
}
