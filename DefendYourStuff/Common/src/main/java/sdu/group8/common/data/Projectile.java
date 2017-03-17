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
public class Projectile extends MovingEntity{
    private float weight;
    private float angle;

    public Projectile(float weight, float angle, Dimension dimension, Position pos, CollisionContainer collision) {
        super(dimension, pos, collision);
        this.weight = weight;
        this.angle = angle;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
    
    
}
