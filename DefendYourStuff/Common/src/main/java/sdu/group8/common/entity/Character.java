/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.CollisionContainer;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Character extends MovingEntity{
    private HealthSystem health;

    public Character(float health, Dimension dimension, Position pos, CollisionContainer collision, Ability... ab) {
        super(dimension, pos, collision, ab);
        this.health = new HealthSystem(health);
    }
    
    public float getHealth() {
        return health.getHealth();
    }
    
    public void reduceHealth(float health) {
        this.health.reduceHealth(health);
    }
    
    public void increaseHealth(float health) {
        this.health.increaseHealth(health);
    }
}
