/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commoncharacter;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.MovingEntity;

/**
 *
 * @author Martin
 */
public class Character extends MovingEntity{
    private HealthSystem health;

    public Character(float health, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, Ability... ab) {
        super(imageURL, dimension, pos, collisionType, ab);
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

    @Override
    public void collision(Entity otherEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
