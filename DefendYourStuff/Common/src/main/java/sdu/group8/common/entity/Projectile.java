/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.collision.CollisionContainer;

/**
 *
 * @author Martin
 */
public class Projectile extends MovingEntity{
    private float weight;
    private float angle;
    private float radius;
    private boolean isHit = false;

    public Projectile(float weight, float angle, float radius, Dimension dimension, Position pos, CollisionContainer collision, Ability... ab) {
        super(dimension, pos, collision, ab);
        this.weight = weight;
        this.angle = angle;
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    
    
}
