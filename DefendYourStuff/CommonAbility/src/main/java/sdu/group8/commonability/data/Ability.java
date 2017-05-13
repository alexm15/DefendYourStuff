/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability.data;

import sdu.group8.commonability.services.IAbilityAction;
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
public abstract class Ability extends MovingEntity {

    protected DamageRange damageRange;
    protected boolean isHit = false;
    protected EffectContainer effects;
    protected float radians;
    protected Entity owner;
    protected float expiration = 1;
    protected float verticalVelocity;
    protected boolean aimable;

    public Ability(float expiration, float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer, Entity owner, boolean aimable) {
        super(moveSpeed, weight, imageURL, dimension, direction, pos, collisionType);
        this.effects = effectContainer;
        this.damageRange = damageRange;
        this.expiration = expiration;
        this.owner = owner;
        this.radians = 0;
        this.verticalVelocity = 0;
        this.aimable = aimable;
    }

    public abstract Ability getNewInstance(Entity owner, float x, float y, boolean directionLeft);
    
    public boolean isAimable() {
        return aimable;
    }

    public void setAimable(boolean aimable) {
        this.aimable = aimable;
    }

    public float getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(float verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
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

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    /**
     * If the otherEntity implements IAbilityAction, call that action with a reference to this ability.
     * @param otherEntity The other entity that this ability has collided with.
     */
    @Override
    public void collision(Entity otherEntity) {
        if (otherEntity instanceof IAbilityAction) {
            ((IAbilityAction) otherEntity).abilityAction(this);
        }
    }

}
