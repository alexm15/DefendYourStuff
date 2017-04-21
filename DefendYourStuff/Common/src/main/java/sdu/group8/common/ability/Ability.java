/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.MovingEntity;

/**
 *
 * @author Martin
 */
public class Ability extends MovingEntity {
    
    private DamageRange damageRange;
    private boolean isHit = false;
    private EffectContainer effects;
    private float angle;
    private String name;

    public Ability(float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, String name, EffectContainer effectContainer) {
        super(moveSpeed, weight, imageURL, dimension, pos, collisionType);
        this.effects = effectContainer;
        this.damageRange = damageRange;
        this.name = name;
    }
    
    public Ability(Ability ability) {
        super(ability.getMoveSpeed(), ability.getWeight(), ability.getImageURL(), ability.getDimension(), ability.getPosition(), ability.getCollisionType());
        this.effects = ability.getEffects();
        this.damageRange = ability.getDamageRange();
        this.name = ability.getName();
    }

    public String getName() {
        return name;
    }
    
    public DamageRange getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(DamageRange damageRange) {
        this.damageRange = damageRange;
    }

    public EffectContainer getEffects() {
        return effects;
    }

    public void setEffects(EffectContainer effects) {
        this.effects = effects;
    }

    public float getDamage() {
        return damageRange.getDamage();
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public void collision(Entity otherEntity) {
        
    }

}
