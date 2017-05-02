/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
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
    private Entity owner;
    private float expiration = 1;
    
    public Ability(float expiration, float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer) {
        super(moveSpeed, weight, imageURL, dimension, direction, pos, collisionType);
        this.effects = effectContainer;
        this.damageRange = damageRange;
        this.expiration = expiration;
    }
    
    public Ability(Ability ability) {
        super(ability.getMoveSpeed(), ability.getWeight(), ability.getImage().getImageURL(), ability.getDimension(), ability.getDirection(), ability.getPosition(), ability.getCollisionType());
        this.effects = ability.getEffects();
        this.damageRange = ability.getDamageRange();
        this.expiration = ability.getExpiration();
    }

    public float getExpiration() {
        return expiration;
    }

    public void updateExpiration(float deltaTime) {
        this.expiration -= deltaTime;
    }
    
    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
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
