/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commoncharacter;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.MovingEntity;

/**
 *
 * @author Martin
 */
public abstract class Character extends MovingEntity{
    
    protected HealthSystem health;
    protected AbilityContainer abilities;
    protected float reactionTime;
    protected float reactionTimer;

    

    public Character(float moveSpeed, float weight, float health, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, AbilityData... ab) {
        super(moveSpeed, weight, imageURL, dimension, direction, pos, collisionType);
        this.health = new HealthSystem(health);
        this.abilities = new AbilityContainer(ab);
    }

    public AbilityContainer getAbilityContainer() {
        return abilities;
    }
    
    protected HealthSystem getHealth() {
        return this.health;
    }
    
    public float getCurrentHealth() {
        return this.health.getHealth();
    }
    
    public void reduceHealth(float health) {
        this.health.reduceHealth(health);
    }
    
    public void increaseHealth(float health) {
        this.health.increaseHealth(health);
    }

    public float getReactionTimer() {
        return reactionTimer;
    }

    public void reduceReactiontime(float i) {
        this.reactionTimer = -i;
    }

    public void resetReactiontime() {
        this.reactionTimer = reactionTime;
    }
}
