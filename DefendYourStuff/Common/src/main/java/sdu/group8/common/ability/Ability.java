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
 * The ability entity that other entities in the game have and can 
 * use.
 * @author Group 8
 */
public class Ability extends MovingEntity {

    protected DamageRange damageRange;
    protected boolean isHit = false;
    protected EffectContainer effects;
    protected float angle;
    protected Entity owner;
    protected float expiration = 1;

    /**
     * Creates an ability entity in the game expirationTime cannot be negative.
     * @param expiration when the ability is removed, cannot be negative value;
     * @param moveSpeed the speed that the ability is travelling at in the game
     * @param weight determines if the ability is affected by game gravity. 0 = 
     * fly's straight, above 0 = flys in an arc.
     * @param damageRange the specified damageRange for the ability
     * @param imageURL the image for the ability.
     * @param dimension the size of the ability. Must be the same as 
     * image size.
     * @param direction the direction that the ability is travelling 
     * @param pos the position of the ability
     * @param collisionType 
     * @param effectContainer contains which effects the ability can affect
     * other entities with.
     */
    public Ability(float expiration, float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer) {
        super(moveSpeed, weight, imageURL, dimension, direction, pos, collisionType);
        if (expiration < 0) {
            throw new IllegalArgumentException("Expiration time cannot be negative");
        }
        this.effects = effectContainer;
        this.damageRange = damageRange;
        this.expiration = expiration;
        
    }

    /**
     * Creates an ability based on another ability in game.
     * @param ability an existing ability
     */
    public Ability(Ability ability) {
        super(ability.getMoveSpeed(), ability.getWeight(), ability.getImage().getImageURL(), ability.getDimension(), ability.getDirection(), ability.getPosition(), ability.getCollisionType());
        this.effects = ability.getEffects();
        this.damageRange = ability.getDamageRange();
        this.expiration = ability.getExpiration();
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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
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
